package compose.project.demo.data

import compose.project.demo.Movie

class MoviesRepository(private val movieService: MovieService) {
    suspend fun fetchPopularMovies(): List<Movie>{
        return movieService.fetchPopularMovies().result.map { it.toDomainMovie() }
    }

    suspend fun fetchMovieById(id:Int):Movie{
        return movieService.fetchMovieById(id).toDomainMovie()
    }
}

//private fun RemoteMovie.toDomainMovie() = Movie(
//    id= id,
//    title= title,
//    overview = overview,
//    poster = "http://image.tmdb.org/t/p/w500$posterPath",
//    originalLanguage= originalLanguage
//)

private fun RemoteMovie.toDomainMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    poster = "https://image.tmdb.org/t/p/w500$posterPath",
    originalLanguage = originalLanguage,
    // Faltan estos campos:
    originalTitle = originalTitle,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    popularity = popularity,
    backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w500$it" }
)