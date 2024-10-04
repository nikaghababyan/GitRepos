package com.task.ui.extensions

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

fun String.buildSpannable(
    spanStyle: SpanStyle,
    vararg spanText: String,
) = buildAnnotatedString {
    this@buildSpannable.split(
        regex = "(${spanText.joinToString("|") { "(?=$it)|(?<=$it)" }})".toRegex()
    ).forEach { text ->
        if (spanText.contains(text)) {
            pushStringAnnotation(text, text)
            withStyle(style = spanStyle) {
                append(text)
            }
            pop()
        } else {
            append(text)
        }
    }
}
