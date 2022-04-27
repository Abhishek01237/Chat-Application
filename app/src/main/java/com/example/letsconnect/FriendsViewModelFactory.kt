package com.example.letsconnect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FriendsViewModelFactory(private val repository: FriendsRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FriendsViewModel(repository) as T
    }
}