package com.kykara4a.medvisualizer.ui.composables.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.kykara4a.medvisualizer.ui.gl.MedSurfaceView

@Composable
fun SurfaceView(color: Color, centerX: Float, centerY: Float, radius: Float) {
    AndroidView(
        factory = { context ->
            MedSurfaceView(context).apply {
                init(color, centerX, centerY, radius)
            }
        },
    )
}