package com.evgenykuksov.data.data.actors.remote.model

import com.google.gson.annotations.SerializedName

data class ActorRemote(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("place_of_birth") val placeOfBirth: String?,
    @SerializedName("popularity") val popularity: Float?,
    @SerializedName("biography") val biography: String?,
    @SerializedName("profile_path") val profilePhoto: String?,
    @SerializedName("also_known_as") val listNames: List<String>?,
)