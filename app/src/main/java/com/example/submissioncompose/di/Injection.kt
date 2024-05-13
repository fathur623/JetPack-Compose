package com.example.submissioncompose.di

import com.example.submissioncompose.data.GitarRepository

object Injection {
    fun provideRepository(): GitarRepository {
        return GitarRepository.getInstance()
    }
}