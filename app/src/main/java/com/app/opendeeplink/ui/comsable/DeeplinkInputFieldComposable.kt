package com.app.opendeeplink.ui.comsable


import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.opendeeplink.R

/**
 * Display an [OutlinedTextField] to enter a deeplink with a button to open this deeplink
 */
@Composable
fun DeeplinkInputField(openDeeplink: (String) -> Unit) {

    var deeplink by remember { mutableStateOf("") }

    Column(
        Modifier.padding(8.dp),
    ) {
        OutlinedTextField(
            value = deeplink,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Uri),
            onValueChange = { deeplink = it },
            label = {
                Text(stringResource(R.string.deeplink))
            },
            placeholder = {
                Text(stringResource(R.string.schemeExample))
            },
            trailingIcon = {
                androidx.compose.animation.AnimatedVisibility(
                    visible = deeplink.isNotBlank(),
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut()
                ) {
                    IconButton(onClick = {
                        deeplink = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.clear)
                        )
                    }
                }
            },
        )

        Button(
            enabled = deeplink.isNotBlank(),
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.End),
            onClick = { openDeeplink(deeplink) }
        ) {
            Text(text = stringResource(R.string.open_deeplink))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeeplinkInputFieldPreview() {
    DeeplinkInputField {}
}
