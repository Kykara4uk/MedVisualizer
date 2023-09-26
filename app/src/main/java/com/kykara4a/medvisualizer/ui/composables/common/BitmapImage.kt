package com.kykara4a.medvisualizer.ui.composables.common

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale

@Composable
fun BitmapImage(bitmap: Bitmap?) {
    Image(
        bitmap = bitmap?.asImageBitmap() ?: return,
        contentDescription = "some useful description",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop,
    )
}