package com.example.submissioncompose.data

import com.example.submissioncompose.model.DataGitar
import com.example.submissioncompose.model.Gitar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class GitarRepository {
    private val dummyGitar = mutableListOf<Gitar>()

    init {
        if (dummyGitar.isEmpty()) {
            DataGitar.dummyGitar.forEach {
                dummyGitar.add(it)
            }
        }
    }

    fun getGitarById(gitarId: Int): Gitar {
        return dummyGitar.first {
            it.id == gitarId
        }
    }

    fun getFavoriteGitar(): Flow<List<Gitar>> {
        return flowOf(dummyGitar.filter { it.isFavorite })
    }

    fun searchGitar(query: String) = flow {
        val data = dummyGitar.filter {
            it.name.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    fun updateGitar(gitarId: Int, newState: Boolean): Flow<Boolean> {
        val index = dummyGitar.indexOfFirst { it.id == gitarId }
        val result = if (index >= 0) {
            val gitar = dummyGitar[index]
            dummyGitar[index] = gitar.copy(isFavorite = newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: GitarRepository? = null

        fun getInstance(): GitarRepository =
            instance ?: synchronized(this) {
                GitarRepository().apply {
                    instance = this
                }
            }
    }
}