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
package com.androiddevchallenge.week3.apac.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.androiddevchallenge.week3.apac.ui.theme.BloomTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BloomApp() {
    ProvideWindowInsets {
        BloomTheme {
            val systemUiController = rememberSystemUiController()
            val navController = rememberNavController()
            val useDarkIcons = MaterialTheme.colors.isLight

            // Update all of the system bar colors to be transparent, and use
            // dark icons if we're in light theme
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )

            Scaffold(backgroundColor = MaterialTheme.colors.background) { innerPadding ->
                NavGraph(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
    }
}
