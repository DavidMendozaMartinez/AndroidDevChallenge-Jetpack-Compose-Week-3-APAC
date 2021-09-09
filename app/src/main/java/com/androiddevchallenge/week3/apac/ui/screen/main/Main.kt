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
package com.androiddevchallenge.week3.apac.ui.screen.main

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.androiddevchallenge.week3.apac.R
import com.androiddevchallenge.week3.apac.ui.screen.main.home.Home
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding

fun NavGraphBuilder.addMainGraph(modifier: Modifier = Modifier) {
    composable(MainSections.HOME.route) { Home(modifier) }
    composable(MainSections.FAVORITES.route) {}
    composable(MainSections.PROFILE.route) {}
    composable(MainSections.CART.route) {}
}

enum class MainSections(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.main_label_home, Icons.Filled.Home, "main/home"),
    FAVORITES(R.string.main_label_favorites, Icons.Filled.FavoriteBorder, "main/favorites"),
    PROFILE(R.string.main_label_profile, Icons.Filled.AccountCircle, "main/profile"),
    CART(R.string.main_label_cart, Icons.Filled.ShoppingCart, "main/cart")
}

@Composable
fun BloomBottomBar(navController: NavController, sections: Array<MainSections>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MainSections.HOME.route

    val routes = remember { MainSections.values().map { it.route } }
    if (currentRoute in routes) {
        BottomNavigation(
            modifier = Modifier.navigationBarsHeight(additional = 56.dp),
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 16.dp
        ) {
            sections.forEach { section ->
                BottomNavigationItem(
                    selected = section.route == currentRoute,
                    onClick = {},
                    icon = {
                        Icon(
                            imageVector = section.icon,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.navigationBarsPadding(),
                    label = {
                        Text(
                            text = stringResource(section.label),
                            style = MaterialTheme.typography.caption
                        )
                    }
                )
            }
        }
    }
}
