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
package com.androiddevchallenge.week3.apac.ui.screen.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.androiddevchallenge.week3.apac.R
import com.androiddevchallenge.week3.apac.ui.theme.BloomTheme
import com.androiddevchallenge.week3.apac.ui.utils.addHyperlink

object Routes {
    const val TERMS_OF_USE = ""
    const val PRIVACY_POLICY = ""
}

@Composable
fun LogIn() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.log_in_title),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .paddingFromBaseline(top = 184.dp, bottom = 16.dp),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1
            )

            // The height defined for the TextFields has been ignored because
            // the OutlinedTextField requires a higher minimum height to
            // function properly.
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth(),
                // .height(56.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.log_in_label_email),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth(),
                // .height(56.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.log_in_label_password),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            ConsentText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .paddingFromBaseline(top = 24.dp, bottom = 16.dp),
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                )
            )

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.log_in_button_log_in),
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}

@Composable
fun ConsentText(
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default
) {
    val consent = stringResource(id = R.string.log_in_consent)
    val termsOfUse = stringResource(id = R.string.log_in_consent_terms_of_use)
    val privacyPolicy = stringResource(id = R.string.log_in_consent_privacy_policy)

    val text = buildAnnotatedString {
        append(consent)

        addHyperlink(
            tag = termsOfUse,
            annotation = Routes.TERMS_OF_USE,
            start = consent.indexOf(termsOfUse),
            end = consent.indexOf(termsOfUse) + termsOfUse.length
        )

        addHyperlink(
            tag = privacyPolicy,
            annotation = Routes.PRIVACY_POLICY,
            start = consent.indexOf(privacyPolicy),
            end = consent.indexOf(privacyPolicy) + privacyPolicy.length
        )
    }

    ClickableText(
        text = text,
        modifier = modifier,
        style = style,
        onClick = { offset ->
            text.getStringAnnotations(start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    when (annotation.tag) {
                        termsOfUse -> {}
                        privacyPolicy -> {}
                    }
                }
        }
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LogInLightPreview() {
    BloomTheme {
        LogIn()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun LogInDarkPreview() {
    BloomTheme(darkTheme = true) {
        LogIn()
    }
}
