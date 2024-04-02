package com.app.opendeeplink

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
                        DeeplinkInputField()

                        DeeplinkHistory(deeplinkList = listOf("tel://", "hey"),
                            copyLink = {
                                Toast.makeText(this@MainActivity, "Copy link $it", Toast.LENGTH_SHORT).show()
                            },
                            deleteLink = {
                                Toast.makeText(this@MainActivity, "Delete link $it", Toast.LENGTH_SHORT).show()
                            },
                            openLink = {
                                Toast.makeText(this@MainActivity, "Open link $it", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}



