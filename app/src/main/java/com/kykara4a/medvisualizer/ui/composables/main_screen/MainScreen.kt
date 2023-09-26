package com.kykara4a.medvisualizer.ui.composables.main_screen

import android.graphics.Bitmap
import android.graphics.ImageFormat.RGB_565
import android.opengl.GLSurfaceView
import android.view.LayoutInflater
import android.view.SurfaceView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kykara4a.medvisualizer.R
import com.kykara4a.medvisualizer.ui.composables.common.BitmapImage
import com.kykara4a.medvisualizer.ui.composables.common.SurfaceView
import com.kykara4a.medvisualizer.ui.gl.MedSurfaceView

@Composable
fun MainScreen(dicomBitmap: Bitmap?) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    Column {
        Text(
            text = "Dicom visualisation:",
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                )
            )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            border = BorderStroke(1.dp, primaryColor)

        ) {
            BitmapImage(bitmap = dicomBitmap)
        }
        Text(
            text = "Opengl visualisation:",
            modifier = Modifier.padding(
                start = 16.dp,
            ),
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            border = BorderStroke(1.dp, primaryColor)

        ) {
            SurfaceView(tertiaryColor)
        }
    }
}

@Preview
@Composable
fun test() {
    MainScreen(dicomBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.RGB_565))
}