package com.github.kornilovmikhail.mvpandroidproject.data.network.model

import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("reddit") val linkReddit: String?,
    @SerializedName("article") val linkArticle: String?,
    @SerializedName("wikipedia") val linkWikipedia: String?
)