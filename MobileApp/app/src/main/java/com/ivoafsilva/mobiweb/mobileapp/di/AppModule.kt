package com.ivoafsilva.mobiweb.mobileapp.di

import com.ivoafsilva.mobiweb.mobileapp.ui.home.HomeViewModel
import com.ivoafsilva.mobiweb.mobileapp.util.DefaultDispatcherProvider
import com.ivoafsilva.mobiweb.mobileapp.util.DispatcherProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory<DispatcherProvider> { DefaultDispatcherProvider() }

    viewModel { HomeViewModel(mobileSdk = get(), dispatcherProvider = get()) }
}