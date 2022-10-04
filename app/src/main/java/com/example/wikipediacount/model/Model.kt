package com.example.wikipediacount.model


object Model {
    data class Result(val query: Query)
    data class Query(val searchInfo:SearchInfo)
    data class SearchInfo(val totalHits:Int)
}