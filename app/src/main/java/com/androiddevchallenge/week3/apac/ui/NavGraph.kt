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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.androiddevchallenge.week3.apac.data.PlantRepository
import com.androiddevchallenge.week3.apac.data.ThemeRepository
import com.androiddevchallenge.week3.apac.ui.screen.login.LogIn
import com.androiddevchallenge.week3.apac.ui.screen.main.MainSections
import com.androiddevchallenge.week3.apac.ui.screen.main.addMainGraph
import com.androiddevchallenge.week3.apac.ui.screen.welcome.Welcome

/**
 * Destinations used in the ([BloomApp]).
 */
enum class Destinations(val route: String) {
    WELCOME("welcome"),
    LOG_IN("logIn"),
    MAIN("main")
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.WELCOME.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.WELCOME.route) {
            Welcome(
                onCreateAccountButtonClick = {},
                onLogInButtonClick = { navController.navigate(Destinations.LOG_IN.route) }
            )
        }
        composable(Destinations.LOG_IN.route) {
            LogIn(
                onHyperlinkClick = {},
                onLogInButtonClick = { _, _ -> navController.navigate(Destinations.MAIN.route) }
            )
        }
        navigation(
            route = Destinations.MAIN.route,
            startDestination = MainSections.HOME.route
        ) {
            // The lists of themes, plants and selected plants are received from here
            // for simplicity. In a context more faithful to reality, they would be
            // obtained through the ViewModel associated with the screen.
            val themes = ThemeRepository.getThemes()
            val plants = PlantRepository.getPlants()
            val selected = listOf(plants.first())

            addMainGraph(
                modifier = modifier,
                themes = themes,
                plants = plants,
                selected = selected,
                onSearchAction = {},
                onThemeClick = {},
                onFilterButtonClick = {},
                onPlantCheckedChange = { _, _ -> }
            )
        }
    }
}
