package com.evgenykuksov.data.util

internal fun getOriginalImageUrl(imageName: String) = "$IMAGE_URL_ORIGINAL$imageName"

internal fun getW500ImageUrl(imageName: String) = "$IMAGE_URL_W500$imageName"

const val IMAGE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original"
const val IMAGE_URL_W500 = "https://image.tmdb.org/t/p/w500/"