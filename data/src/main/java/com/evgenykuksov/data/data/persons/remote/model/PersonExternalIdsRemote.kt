package com.evgenykuksov.data.data.persons.remote.model

import com.google.gson.annotations.SerializedName

internal data class PersonExternalIdsRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("tvrage_id") val tvrageId: Long?,
    @SerializedName("facebook_id") val facebookId: String?,
    @SerializedName("instagram_id") val instagramId: String?,
    @SerializedName("twitter_id") val twitterId: String?,
)