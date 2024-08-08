package com.baldomeronapoli.eventplanner.android.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.baldomero.napoli.eventplanner.core.presentation.intent.BaseEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <EFFECT : BaseEffect> CollectEffect(
    effect: Flow<EFFECT?>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = Dispatchers.Main.immediate,
    onEffect: suspend CoroutineScope.(effect: EFFECT) -> Unit,
) {
    LaunchedEffect(effect, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            if (context == EmptyCoroutineContext) {
                effect.collect { effect ->
                    effect?.let {
                        onEffect(it)
                    }
                }
            } else {
                withContext(context) {
                    effect.collect { effect ->
                        effect?.let {
                            onEffect(it)
                        }
                    }
                }
            }
        }
    }

}