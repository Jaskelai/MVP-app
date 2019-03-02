package com.github.kornilovmikhail.mvpandroidproject.data.network.response

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("title") val title: String,
    @SerializedName("event_date_unix") val eventDate: Long,
    @SerializedName("flight_number") val flightNumber: Int?,
    @SerializedName("details") val details: String,
    @SerializedName("links") val links: Links
)