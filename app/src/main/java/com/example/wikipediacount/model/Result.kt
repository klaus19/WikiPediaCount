package com.example.wikipediacount.model

data class Result(
    val batchcomplete: String,
    val `continue`: Continue,
    val query: Query
)