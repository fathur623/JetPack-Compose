package com.example.submissioncompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.GitarRepository
import com.example.submissioncompose.model.Gitar
import com.example.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: GitarRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Gitar>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Gitar>>>
        get() = _uiState

    fun getFavoriteGitar() = viewModelScope.launch {
        repository.getFavoriteGitar()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateGitar(id: Int, newState: Boolean) {
        repository.updateGitar(id, newState)
        getFavoriteGitar()
    }
}