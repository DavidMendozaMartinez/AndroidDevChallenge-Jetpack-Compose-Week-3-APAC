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
package com.androiddevchallenge.week3.apac.data

import com.androiddevchallenge.week3.apac.model.Theme

/**
 * Fake repository
 */
object ThemeRepository {
    fun getThemes(): List<Theme> = listOf(
        Theme(
            id = 1,
            name = "Desert chic",
            imageUrl = "https://images.pexels.com/photos/2132227/pexels-photo-2132227.jpeg",
            imageContentDescription = "Assorted-color Flowers"
        ),
        Theme(
            id = 2,
            name = "Tiny terrariums",
            imageUrl = "https://images.pexels.com/photos/1400375/pexels-photo-1400375.jpeg",
            imageContentDescription = "Clear Glass Terrarium Jar With Mossy Plants"
        ),
        Theme(
            id = 3,
            name = "Jungle vibes",
            imageUrl = "https://images.pexels.com/photos/5699665/pexels-photo-5699665.jpeg",
            imageContentDescription = "Big green leaves of Monstera"
        ),
        Theme(
            id = 4,
            name = "Easy care",
            imageUrl = "https://images.pexels.com/photos/6208086/pexels-photo-6208086.jpeg",
            imageContentDescription = "Green plant with long leaves in pot at home"
        ),
        Theme(
            id = 5,
            name = "Statements",
            imageUrl = "https://images.pexels.com/photos/3511755/pexels-photo-3511755.jpeg",
            imageContentDescription = "Green Leaf Plant Indoors"
        )
    )
}
