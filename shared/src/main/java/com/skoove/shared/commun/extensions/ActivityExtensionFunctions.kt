package com.skoove.shared.commun.extensions


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass



fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (T) -> Unit) {
    liveData.observe(this) { it?.let { t -> observer(t) } }
}

/**
 * extension function that creates an instance of a view model with viewModel factory
 */
fun <VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory, model: KClass<VM>
) = ViewModelProvider(this, provider).get(model.java)

/**
 * extension function that creates an instance of a view model with viewModel factory from a fragment
 */
fun <VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory, model: KClass<VM>
) = ViewModelProvider(this, provider).get(model.java)



