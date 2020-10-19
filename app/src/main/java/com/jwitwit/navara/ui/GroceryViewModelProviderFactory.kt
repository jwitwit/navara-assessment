package com.jwitwit.navara.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jwitwit.navara.ui.viewmodels.GroceryViewModel

class GroceryViewModelProviderFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroceryViewModel() as T
    }
}