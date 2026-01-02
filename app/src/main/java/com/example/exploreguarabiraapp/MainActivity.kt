package com.example.exploreguarabiraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.exploreguarabiraapp.ui.screens.AppNavHost
import com.example.exploreguarabiraapp.ui.theme.ExploreGuarabiraAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ExploreGuarabiraAppTheme {
                AppNavHost()
            }
        }
    }
}
