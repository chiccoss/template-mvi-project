package fr.sohayb.marvelapp.main

import fr.sohayb.marvelapp.main.domain.MainAction
import fr.sohayb.marvelapp.main.domain.MainProcessor
import fr.sohayb.marvelapp.main.domain.MainResult
import fr.sohayb.marvelapp.app.MockProcessorResultCallback
import fr.sohayb.marvelapp.main.mocks.MockMainRepositoryFails
import fr.sohayb.marvelapp.main.mocks.MockMainRepositorySucceeds
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.net.URL

/**
 * Main local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainProcessorUnitTests {

    private val fakeUrl: URL = URL("https://example.com")

    @Before
    fun setUp() {
    }

    @Test
    fun action_download_webpage_success() {

        val repo = MockMainRepositorySucceeds()
        val processor = MainProcessor(repo)
        val callback =
            MockProcessorResultCallback()

        runBlocking {
            processor.process(MainAction.DownloadWebpage(fakeUrl), callback)
        }

        assertTrue(callback.results.size == 2)
        assertTrue(callback.results[0] is MainResult.DownloadingWebpage)
        val response = callback.results[1] as? MainResult.DownloadedWebpage
        assertNotNull(response)
        assertFalse(response!!.html.isNullOrEmpty())
        assertNull(response.error)
    }

    @Test
    fun action_download_webpage_fails() {

        val repo = MockMainRepositoryFails()
        val processor = MainProcessor(repo)
        val callback =
            MockProcessorResultCallback()

        runBlocking {
            processor.process(MainAction.DownloadWebpage(fakeUrl), callback)
        }

        assertTrue(callback.results.size == 2)
        assertTrue(callback.results[0] is MainResult.DownloadingWebpage)
        val response = callback.results[1] as? MainResult.DownloadedWebpage
        assertNotNull(response)
        assertNotNull(response!!.error)
        assertNull(response.html)
    }

    @Test
    fun experimental_process_business_logic() {

        val repo = MockMainRepositorySucceeds()
        val processor = MainProcessor(repo)

        val empty = ""
        var result = processor.applyBusinessRulesToWebpageHtml(empty)
        assertEquals("", result)

        val noSpace = "<DOCTYPE"
        result = processor.applyBusinessRulesToWebpageHtml(noSpace)
        assertEquals("<DOCTYPE", result)

        val withSpaces = "<DOCTYPE html gr5"
        result = processor.applyBusinessRulesToWebpageHtml(withSpaces)
        assertEquals("<DOCTYPEhtmlgr5", result)

        val long = "<DOCTYPE html gr5 LongerThanTake(20)"
        result = processor.applyBusinessRulesToWebpageHtml(long)
        assertEquals("<DOCTYPEhtmlgr5Longe", result)
    }
}

