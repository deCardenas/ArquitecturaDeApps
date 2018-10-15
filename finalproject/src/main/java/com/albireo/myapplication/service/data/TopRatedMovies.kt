package com.albireo.myapplication.service.data

data class TopRatedMovies(
        val page: Int,
        val results: List<Result>,
        val total_results: Int,
        val total_pages: Int
)