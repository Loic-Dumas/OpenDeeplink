package com.app.opendeeplink.ui.comsable


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.opendeeplink.R

/**
 * Display an [OutlinedTextField] to enter a deeplink with a button to open this deeplink
 */
@Composable
fun DeeplinkInputField() {

    var deeplink by remember { mutableStateOf("") }
    val context = LocalContext.current

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
            onClick = {
                openDeeplinkIntent(context = context, deeplink = deeplink)
            }
        ) {
            Text(text = stringResource(R.string.open_deeplink))
        }
    }
}

/**
 * @return true if the deeplink has been caught by an app, false otherwise.
 */
private fun openDeeplinkIntent(context: Context, deeplink: String): Boolean {
    val i = Intent(Intent.ACTION_VIEW)
    i.setData(Uri.parse(deeplink))
    try {
        context.startActivity(i)
        saveDeeplink(deeplink)
        return true
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, context.getString(R.string.unable_to_open_deeplink), Toast.LENGTH_SHORT).show()
    }
    return false
}

private fun saveDeeplink(deeplink: String) {
    // todo
}

@Preview(showBackground = true)
@Composable
fun DeeplinkInputFieldPreview() {
    DeeplinkInputField()
}
