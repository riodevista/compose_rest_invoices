package com.goodapps.invoices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.goodapps.invoices.navigation.NavGraph
import com.goodapps.invoices.ui.theme.InvoicesExerciseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InvoicesExerciseTheme {
                NavGraph()
            }
        }
    }
}
