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
package com.androiddevchallenge.week3.apac.ui.screen.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.androiddevchallenge.week3.apac.R
import com.androiddevchallenge.week3.apac.model.Plant
import com.androiddevchallenge.week3.apac.model.Theme
import com.androiddevchallenge.week3.apac.ui.theme.BloomTheme

@Composable
fun Home(
    modifier: Modifier = Modifier,
    themes: List<Theme> = emptyList(),
    plants: List<Plant> = emptyList(),
    selected: List<Plant> = emptyList(),
    onSearchAction: (String) -> Unit = {},
    onThemeClick: (Theme) -> Unit = {},
    onFilterButtonClick: () -> Unit = {},
    onPlantCheckedChange: (Plant, Boolean) -> Unit = { _, _ -> }
) {
    var query by remember { mutableStateOf("") }

    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = modifier.fillMaxSize()) {
            // The height defined for the TextFields has been ignored because
            // the OutlinedTextField requires a higher minimum height to
            // function properly.
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                // .height(56.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.home_label_search),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearchAction(query) })
            )

            Text(
                text = stringResource(id = R.string.home_title_themes),
                modifier = Modifier
                    .paddingFromBaseline(top = 32.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1
            )

            ThemeCardRow(
                modifier = Modifier.fillMaxWidth(),
                themes = themes,
                onThemeClick = onThemeClick
            )

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(32.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.home_title_plants),
                    modifier = Modifier
                        .weight(1f)
                        .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h1
                )

                // The icon cannot be aligned to the baseline of the text because
                // it has built-in margins that do not allow you to control the
                // exact boundaries of the icon itself. Therefore a padding of
                // 6 dp has been applied to the top arbitrarily to achieve the
                // closest possible result.
                IconButton(
                    onClick = onFilterButtonClick,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.FilterList,
                        contentDescription = stringResource(id = R.string.home_content_description_button_filter_plants),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            PlantList(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                plants = plants,
                selected = selected,
                onPlantCheckedChange = onPlantCheckedChange
            )
        }
    }
}

@Composable
fun ThemeCardRow(
    modifier: Modifier = Modifier,
    themes: List<Theme> = emptyList(),
    onThemeClick: (Theme) -> Unit = {}
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(themes) { theme ->
            ThemeCard(
                theme = theme,
                onClick = { onThemeClick(theme) }
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ThemeCard(theme: Theme, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    // The surface color cannot be applied directly as the
    // backgroundColor on the Card because it has a percentage of
    // transparency and an unwanted border is visible underneath.
    Card(
        modifier = modifier
            .size(136.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
        ) {
            Image(
                painter = rememberImagePainter(data = theme.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = theme.name,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
fun PlantList(
    modifier: Modifier = Modifier,
    plants: List<Plant> = emptyList(),
    selected: List<Plant> = emptyList(),
    onPlantCheckedChange: (Plant, Boolean) -> Unit = { _, _ -> }
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(plants) { plant ->
            PlantRow(
                plant = plant,
                checked = selected.contains(plant),
                onCheckedChange = { checked -> onPlantCheckedChange(plant, checked) }
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PlantRow(
    plant: Plant,
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = plant.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = plant.name,
                        modifier = Modifier.paddingFromBaseline(top = 24.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.h2
                    )

                    Text(
                        text = plant.description,
                        modifier = Modifier.paddingFromBaseline(top = 16.dp),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                }

                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    modifier = Modifier.padding(top = 16.dp),
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = MaterialTheme.colors.onSecondary
                    )
                )
            }

            Divider(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeLightPreview() {
    BloomTheme {
        Home()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeDarkPreview() {
    BloomTheme(darkTheme = true) {
        Home()
    }
}
