package com.example.composetraining.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetraining.DefaultNavigator
import com.example.composetraining.Destination
import com.example.composetraining.LoginViewModel
import com.example.composetraining.HomeViewModel
import com.example.composetraining.DetailViewModel
import com.example.composetraining.Navigator
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<Navigator> {
        DefaultNavigator(startDestination = Destination.AuthGraph)
    }

    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}