/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.androiddevchallenge.week3.apac.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration

fun AnnotatedString.Builder.addHyperlink(
    tag: String,
    annotation: String,
    start: Int,
    end: Int,
    style: SpanStyle = SpanStyle(textDecoration = TextDecoration.Underline)
) {
    addStringAnnotation(
        tag = tag,
        annotation = annotation,
        start = start,
        end = end
    )

    addStyle(
        style = style,
        start = start,
        end = end
    )
}
