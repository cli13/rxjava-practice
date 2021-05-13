package com.example.rxjava

class PostResponse : ArrayList<PostResponseItem>()

data class PostResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)