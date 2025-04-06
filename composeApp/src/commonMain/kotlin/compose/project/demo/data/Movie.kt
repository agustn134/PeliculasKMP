package compose.project.demo

import compose.project.demo.data.RemoteMovie

// En Movie.kt (data class)
//data class Movie(
//    val id:Int,
//    val title:String,
//    val overview: String,
//    val poster: String,
//    val originalLanguage: String // Corregido de originalLanguge
//)
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val originalLanguage: String,
    val originalTitle: String,
    val releaseDate: String?,
    val voteAverage: Double,
    val popularity: Double,
    val backdropPath: String?
)


//val movies=(1..100 ).map{
//    Movie(
//        id= it,
//        title = "Movie $it",
//        overview = "",
//        poster = "http://picsum.photos/200/300?id=$it",
//        originalLanguage = " " // Corregido aquí
//
//    )
//}

private fun RemoteMovie.toDomainMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    poster = "https://image.tmdb.org/t/p/w500$posterPath",
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    popularity = popularity,
    backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" }
)
