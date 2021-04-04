package com.evgenykuksov.recipes.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun launchOnUi(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }
}