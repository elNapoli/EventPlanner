package com.baldomeronapoli.eventplanner.mvi

import kotlinx.coroutines.flow.SharedFlow

fun <T> SharedFlow<T>.asKmmFlow(): KmmFlow<T> = KmmFlow(this)