package com.kykara4a.medvisualizer.ui.gl

import android.opengl.GLES32
import android.opengl.GLSurfaceView
import androidx.compose.ui.graphics.Color
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MedRenderer(val color: Color) : GLSurfaceView.Renderer {

    private lateinit var triangle: Triangle

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // Set the background frame color
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        triangle = Triangle(color)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES32.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        // Redraw background color
        triangle.draw()
    }

    fun doSomething(value: Float){//Do something
        GLES32.glClearColor(value, 1.0f, 1.0f, 1.0f)}
}