package com.example.technpractiseandroid.user

data class Task (
    var ownerUid: String,
    var name:String,
    var description:String,
    var tag:String,
    var importance:String,
    var date: String,
    var time :String
    )