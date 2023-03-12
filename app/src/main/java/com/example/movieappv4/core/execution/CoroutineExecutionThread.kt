package com.example.movieappv4.core.execution

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineExecutionThread {

    fun uiThread(): CoroutineDispatcher

    fun ioThread(): CoroutineDispatcher
}
