package com.example.letsconnect

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FriendsViewModel(private val repository: FriendsRepository): ViewModel() {
    fun insert(items: FriendsItems)= GlobalScope.launch(Dispatchers.IO) {
        repository.insert(items)
    }

    fun delete(items: FriendsItems)= GlobalScope.launch(Dispatchers.IO) {
        repository.delete(items)
    }

    fun update(items: FriendsItems)= GlobalScope.launch(Dispatchers.IO) {
        repository.update(items)
    }

    fun getAllFriendsItems()=repository.getAllItems()

}