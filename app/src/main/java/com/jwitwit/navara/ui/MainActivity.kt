package com.jwitwit.navara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jwitwit.navara.R
import com.jwitwit.navara.ui.viewmodels.GroceryViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModelProviderFactory = GroceryViewModelProviderFactory()
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(GroceryViewModel::class.java)
    }
}