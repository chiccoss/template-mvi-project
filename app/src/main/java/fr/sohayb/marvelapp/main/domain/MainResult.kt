package fr.sohayb.marvelapp.main.domain

import fr.sohayb.marvelapp.base.domain.ResultType


sealed class MainResult: ResultType {



    object InitApplication: MainResult()
}