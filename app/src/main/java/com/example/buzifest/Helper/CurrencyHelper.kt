package com.example.buzifest.Helper

import java.text.NumberFormat
import java.util.*

public fun formatNumber(number: Int): String {
    val formatter = NumberFormat.getInstance(Locale.getDefault())
    return formatter.format(number)
}