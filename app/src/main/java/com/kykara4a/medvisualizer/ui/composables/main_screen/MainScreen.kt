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
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun MainScreen() {
    val primaryColor = MaterialTheme.colorScheme.primary
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val x = -50f
    val y = -50f
    val radius = 40f
    val cylinderCoords = getCylinderCoords(x.toDouble(), y.toDouble())
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1.0f),
            border = BorderStroke(1.dp, primaryColor)

        ) {
            SurfaceView(tertiaryColor, x, y, radius)
        }
        Text(
            text = "Cartesian: ($x; $y), radius = $radius",
            modifier = Modifier.padding(
                start = 16.dp,
            ),
        )
        Text(
            text = "Cylinder: r = ${String.format("%.3f", cylinderCoords.first)}, fi = ${String.format("%.3f", cylinderCoords.second)}, z = ${String.format("%.3f", cylinderCoords.third)}",
            modifier = Modifier.padding(
                start = 16.dp,
                bottom = 16.dp,
            ),
        )
    }
}

@Preview
@Composable
fun test() {
    MainScreen()
}

fun getCylinderCoords(x: Double, y: Double): Triple<Double, Double, Double> {
    val rad = sqrt(x.pow(2.0) + y.pow(2.0))
    val deg = atan(y/x)
    val z = 0.0
    return Triple(rad, deg, z)
}