package com.app.opendeeplink

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.opendeeplink.ui.comsable.DeeplinkHistory
import com.app.opendeeplink.ui.comsable.DeeplinkInputField
import com.app.opendeeplink.ui.theme.OpenDeeplinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenDeeplinkTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {

                        DeeplinkInputField { deeplink ->
                            openDeeplinkIntent(deeplink)
                        }

                        DeeplinkHistory(
                            modifier = Modifier.padding(8.dp),
                            deeplinkList = listOf(
                                "tel://",
                                "hey",
                                "playrts://home",
                            ),
                            copyLink = {
                                Toast.makeText(this@MainActivity, "Copy link $it", Toast.LENGTH_SHORT).show()
                            },
                            deleteLink = {
                                Toast.makeText(this@MainActivity, "Delete link $it", Toast.LENGTH_SHORT).show()
                            },
                            openLink = { deeplink ->
                                openDeeplinkIntent(deeplink)
                            }
                        )
                    }
                }
            }
        }
    }


    /**
     * @return true if the deeplink has been caught by an app, false otherwise.
     */
    private fun openDeeplinkIntent(deeplink: String): Boolean {
        val i = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse(deeplink))
        try {
            startActivity(i)
            saveDeeplink(deeplink)
            return true
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.unable_to_open_deeplink, deeplink), Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private fun saveDeeplink(deeplink: String) {
        // todo
    }
}



