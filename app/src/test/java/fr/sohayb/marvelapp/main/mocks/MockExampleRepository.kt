package fr.sohayb.marvelapp.main.mocks

import fr.sohayb.marvelapp.main.data.MainRepository
import java.net.URL

class MockMainRepositorySucceeds: MainRepository {

    override fun downloadWebPageAtURL(url: URL): Result<String> {
        return Result.success(url.toString())
    }
}

class MockMainRepositoryFails: MainRepository {
    override fun downloadWebPageAtURL(url: URL): Result<String> {
        return Result.failure(RuntimeException("TestRepositoryImplFails"))
    }
}