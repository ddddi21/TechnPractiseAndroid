package com.example.technpractiseandroid.model

data class User(
    var userUid: String,
    var name: String,
    var email: String,
    var password: String,
    var tasks: List<Task>,
    var profilePicture: String,
    var isEmailVerified: Boolean
)