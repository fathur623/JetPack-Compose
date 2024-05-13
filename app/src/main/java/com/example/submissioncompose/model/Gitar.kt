package com.example.submissioncompose.model

data class Gitar(
    val id: Int,
    val name: String,
    val pabric: String,
    val image: Int,
    val description: String,
    val price: String,
    var isFavorite: Boolean = false
)