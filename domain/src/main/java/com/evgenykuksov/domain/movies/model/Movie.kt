package com.evgenykuksov.domain.movies.model

data class Movie(
    val id: Long,
    val backdropPath: String,
    val genreIds: List<Int>,
    val title: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val voteCount: Int
) {
    companion object {

        val STUB = Movie(
            -1,
            "/oMsoN3RsJEjxOWcImvwTffEnkMd.jpg",
            listOf(16, 10751),
            "Величайший детектив-паук",
            "Инспектор Сан – паук и величайший детектив в мире насекомых. Его смекалке, неординарности и безупречному стилю позавидовал бы сам Эркюль Пуаро. После поимки своего заклятого врага, Красной Саранчи, герой летит в долгожданный отпуск, но прямо на роскошном авиалайнере сталкивается с самым загадочным делом в своей карьере. Теперь мастеру на все лапки предстоит распутать грандиозную паутину лжи...",
            311.387f,
            "/u0B8RoQoA0dgB3Tpc8zQ2wUW39t.jpg",
            "2023-03-02",
            7f,
            82
        )
    }
}