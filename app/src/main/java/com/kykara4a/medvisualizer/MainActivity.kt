package com.kykara4a.medvisualizer

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kykara4a.medvisualizer.domain.mappers.getDicomObject
import com.kykara4a.medvisualizer.domain.model.DicomObject
import com.kykara4a.medvisualizer.ui.composables.common.BitmapImage
import com.kykara4a.medvisualizer.ui.composables.main_screen.MainScreen
import com.kykara4a.medvisualizer.ui.theme.MedVisualizerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedVisualizerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                    )
                }
            }
        }
    }

    private fun getTestDicomObject() : DicomObject {
        val inputStream = assets.open("test.dcm")
        return inputStream.getDicomObject()
    }
}