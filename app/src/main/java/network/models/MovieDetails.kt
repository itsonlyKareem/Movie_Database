package network.models

class MovieDetails(
    var budget: Int,
    var description: String,
    var imageMain: String,
    var imageBack: String,
    var releaseDate: String,
    var runTime: Int,
    var genres: MutableList<Genre>,
    var title: String,
    var originalLanguage: String
)