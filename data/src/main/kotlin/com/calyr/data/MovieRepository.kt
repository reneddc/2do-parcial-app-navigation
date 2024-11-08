package com.calyr.data

import com.calyr.domain.Movie

class MovieRepository(
    val remoteDataSource: IRemoteDataSource,
    val localDataSource: ILocalDataSource
) {

    suspend fun obtainMovies(): List<Movie> {
        // Consultar al servicio web
        val moviesRemote = remoteDataSource.fetchData()

        // Verificar el estado final del consumo de API
        when (moviesRemote) {
            is NetworkResult.Success -> {
                // Eliminamos los datos de la base de datos
                localDataSource.deleteAll()
                // Actualizar la base de datos con los datos remotos
                localDataSource.insertMovies(moviesRemote.data)
            }
            is NetworkResult.Error -> {
                // Registrar un log en Sentry
            }
        }

        // Retornar la lista de películas desde la base de datos local
        val moviesLocal = localDataSource.getList()
        return when (moviesLocal) {
            is NetworkResult.Success -> moviesLocal.data
            is NetworkResult.Error -> emptyList() // Loguear si es necesario
        }
    }

    // Nuevo método para obtener solo los datos locales, sin consulta remota
    fun getLocalMovies(): List<Movie> {
        val moviesLocal = localDataSource.getList()
        return when (moviesLocal) {
            is NetworkResult.Success -> moviesLocal.data
            is NetworkResult.Error -> emptyList() // Loguear si es necesario
        }
    }

    fun findById(id: String): Movie? {
        val movieLocal = localDataSource.findById(id)
        return when (movieLocal) {
            is NetworkResult.Success -> movieLocal.data
            is NetworkResult.Error -> null // Loguear si es necesario
        }
    }
}
