package com.app.opendeeplink.ui.comsable

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.app.opendeeplink.R
import com.app.opendeeplink.viewmodel.DeeplinkViewmodel

@Composable
fun DeeplinkAppScreen(viewmodel: DeeplinkViewmodel) {

    val activity = (LocalContext.current as Activity)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            DeeplinkInputField {
                openDeeplinkIntent(activity, it, viewmodel)
            }

            DeeplinkHistory(
                modifier = Modifier.padding(8.dp),
                viewmodel = viewmodel,
                copyLink = {
                    Toast.makeText(activity, "Copy link $it", Toast.LENGTH_SHORT).show()
                },
                deleteLink = {
                    viewmodel.deleteDeeplink(it)
                },
                openLink = {
                    openDeeplinkIntent(activity, it, viewmodel)
                }
            )
        }
    }
}


/**
 * @return true if the deeplink has been caught by an app, false otherwise.
 */
private fun openDeeplinkIntent(
    activity: Activity,
    deeplink: String,
    viewmodel: DeeplinkViewmodel
): Boolean {
    val i = Intent(Intent.ACTION_VIEW)
    i.setData(Uri.parse(deeplink))
    try {
        activity.startActivity(i)
        viewmodel.addDeeplink(deeplink)
        return true
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(activity, activity.getString(R.string.unable_to_open_deeplink, deeplink), Toast.LENGTH_SHORT).show()
    }
    return false
}