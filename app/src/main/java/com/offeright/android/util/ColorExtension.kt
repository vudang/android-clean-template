package com.offeright.android.util

import androidx.compose.ui.graphics.Color

fun Color.alpha(alpha: Float): Color {
    return this.copy(alpha = alpha)
}