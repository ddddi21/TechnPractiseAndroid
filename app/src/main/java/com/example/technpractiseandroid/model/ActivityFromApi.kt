package com.example.technpractiseandroid.model

import com.google.gson.annotations.SerializedName

data class ActivityFromApi (
    var activity: String,
    var accessibility: Double,
    var type: String,
    var participants: Int,
    var price: Double,
    var key: String,
    var link: String
    )