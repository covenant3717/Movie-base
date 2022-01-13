package com.evgenykuksov.data.util

internal enum class TmdbImagePath(private val basePath: String) {
    ORIGINAL("https://image.tmdb.org/t/p/original"),
    W500("https://image.tmdb.org/t/p/w500/");

    companion object {

        fun getImagePath(imageSize: TmdbImagePath, imageName: String) = "${imageSize.basePath}$imageName"
    }
}

internal enum class YoutubeImagePath(private val value: String) {
    DEFAULT("/default.jpg"),
    MQ_DEFAULT("/mqdefault.jpg"),
    SD_DEFAULT("/sddefault.jpg");

    companion object {

        private const val BASE_PATH = "https://img.youtube.com/vi/"

        fun getImagePath(imageName: String, imageSize: YoutubeImagePath) = "$BASE_PATH$imageName${imageSize.value}"
    }
}


