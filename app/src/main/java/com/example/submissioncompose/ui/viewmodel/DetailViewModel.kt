package com.example.submissioncompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.GitarRepository
import com.example.submissioncompose.model.Gitar
import com.example.submissioncompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GitarRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Gitar>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Gitar>>
        get() = _uiState

    fun getGitarById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getGitarById(id))
    }


    fun updateGitar(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateGitar(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getGitarById(id)
            }
    }

}