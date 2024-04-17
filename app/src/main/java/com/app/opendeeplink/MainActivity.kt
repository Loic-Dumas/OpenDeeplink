package com.app.opendeeplink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.opendeeplink.domain.room.DeeplinkDatabase
import com.app.opendeeplink.ui.comsable.DeeplinkAppScreen
import com.app.opendeeplink.ui.theme.OpenDeeplinkTheme
import com.app.opendeeplink.viewmodel.DeeplinkViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO make it cleaner
        val db = DeeplinkDatabase.createDataBase(this)
        val dao = db.userDao()

        setContent {
            OpenDeeplinkTheme {
                DeeplinkAppScreen(DeeplinkViewmodel(dao))
            }
        }
    }

}



