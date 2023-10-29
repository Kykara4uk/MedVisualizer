package com.kykara4a.medvisualizer.ui.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.util.Log
import androidx.compose.ui.graphics.Color

class MedSurfaceView(
    context: Context
) : GLSurfaceView(context) {

    private lateinit var renderer: MedRenderer

    fun init(color: Color, centerX: Float, centerY: Float, radius: Float) {
        //Set the opengl version to 3
        setEGLContextClientVersion(3)

        //Instantiate my renderer
        renderer = MedRenderer(color, -100f, 5f, 5f, centerX, centerY, radius)
        setRenderer(renderer)        //Render only when dirty
        renderMode = RENDERMODE_WHEN_DIRTY

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        renderer.setCanvasSize(measuredWidth, measuredHeight)

    }
}