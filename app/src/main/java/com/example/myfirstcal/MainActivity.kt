package com.example.myfirstcal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myfirstcal.ui.theme.MyFirstcalTheme
import com.example.myfirstcal.ui.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstcalTheme {
                NavGraph()
            }
        }
    }
}