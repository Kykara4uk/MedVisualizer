package com.kykara4a.medvisualizer.domain.model

import android.graphics.Bitmap
import android.graphics.Color

data class DicomObject(
    val image: ByteArray,
    val imageWidth: Int,
    val imageHeight: Int,
    // Necessary fields will be added later to keep class clear
) {
    fun getBitmap(color: androidx.compose.ui.graphics.Color): Bitmap? {
        val bitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.RGB_565)
        var i = 0
        for (x in 0 until imageWidth) {
            for (y in 0 until imageHeight) {
                val pixelValue = image[i].toInt() and 0xFF
                bitmap.setPixel(x, y, grayColorIntToColorInt(pixelValue, color))
                i+=2
            }
        }
        return bitmap
    }

    private fun grayColorIntToColorInt(grayColor: Int, color: androidx.compose.ui.graphics.Color): Int {
        val r = (color.red * grayColor).toInt()
        val g = (color.green * grayColor).toInt()
        val b = (color.blue * grayColor).toInt()
        return Color.rgb(r, g, b)
    }
}