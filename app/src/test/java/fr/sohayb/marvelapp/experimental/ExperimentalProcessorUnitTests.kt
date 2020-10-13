package fr.sohayb.marvelapp.experimental

import fr.sohayb.marvelapp.main.domain.MainResult
import fr.sohayb.marvelapp.main.mocks.MockMainRepositoryFails
import fr.sohayb.marvelapp.main.mocks.MockMainRepositorySucceeds
import fr.sohayb.marvelapp.experimental.usecase.ExperimentalProcess
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.URL

class ExperimentalProcessorUnitTests {

    private val fakeUrl: URL = URL("https://example.com")

    @Before
    fun setUp() {
    }

    @Test
    fun experimental_process_success() {

        val repo = MockMainRepositorySucceeds()
        val process = ExperimentalProcess.DownloadWebpage(fakeUrl, repo)
        runBlocking {
            var result = process.next()
            Assert.assertNotNull(result)
            Assert.assertTrue(result is MainResult.DownloadingWebpage)
            result = process.next()
            Assert.assertNotNull(result)
            Assert.assertNotNull(result is MainResult.DownloadedWebpage)
            Assert.assertNotNull((result as MainResult.DownloadedWebpage).html)
            Assert.assertNull((result).error)
        }
    }

    @Test
    fun experimental_process_failure() {
        val repo = MockMainRepositoryFails()
        val process = ExperimentalProcess.DownloadWebpage(fakeUrl, repo)
        runBlocking {
            var result = process.next()
            Assert.assertNotNull(result)
            Assert.assertTrue(result is MainResult.DownloadingWebpage)
            result = process.next()
            Assert.assertNotNull(result)
            Assert.assertTrue(result is MainResult.DownloadedWebpage)
            Assert.assertNull((result as MainResult.DownloadedWebpage).html)
            Assert.assertNotNull((result).error)
        }
    }

    @Test
    fun experimental_process_business_logic() {
        val repo = MockMainRepositorySucceeds()
        val process = ExperimentalProcess.DownloadWebpage(fakeUrl, repo)

        val empty = ""
        var result = process.applyBusinessRulesToWebpageHtml(empty)
        Assert.assertEquals("", result)

        val noSpace = "<DOCTYPE"
        result = process.applyBusinessRulesToWebpageHtml(noSpace)
        Assert.assertEquals("<DOCTYPE", result)

        val withSpaces = "<DOCTYPE html gr5"
        result = process.applyBusinessRulesToWebpageHtml(withSpaces)
        Assert.assertEquals("<DOCTYPEhtmlgr5", result)

        val long = "<DOCTYPE html gr5 LongerThanTake(20)"
        result = process.applyBusinessRulesToWebpageHtml(long)
        Assert.assertEquals("<DOCTYPEhtmlgr5Longe", result)
    }

}