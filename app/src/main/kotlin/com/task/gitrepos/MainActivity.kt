package com.task.gitrepos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.task.navigation.GitReposNavGraph
import com.task.ui.theme.GitReposTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isSplashScreen by rememberSaveable { mutableStateOf(true) }
            GitReposTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (isSplashScreen) {
                        LaunchedEffect(Unit) {
                            delay(SPLASH_DURATION)
                            isSplashScreen = false
                        }
                        SplashScreen()
                    } else {
                        GitReposNavGraph()
                    }
                }
            }
        }
    }

    companion object {
        private const val SPLASH_DURATION = 2_000L
    }
}
