package com.kykara4a.medvisualizer.ui.gl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import androidx.compose.ui.graphics.Color

class MedSurfaceView(
    context: Context
) : GLSurfaceView(context) {

    private lateinit var renderer: MedRenderer

    fun init(color: Color) {
        //Set the opengl version to 3
        setEGLContextClientVersion(3)

        //Instantiate my renderer
        renderer = MedRenderer(color)        //Set the renderer to my renderer
        setRenderer(renderer)        //Render only when dirty
        renderMode = RENDERMODE_WHEN_DIRTY
    }
    //Some render task
    fun doSomething(value: Float){
        renderer.doSomething(value)
    }
}