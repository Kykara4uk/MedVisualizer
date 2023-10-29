package com.kykara4a.medvisualizer.ui.gl

import android.opengl.GLES10.glClear
import android.opengl.GLES32
import android.opengl.GLSurfaceView
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.kykara4a.medvisualizer.domain.model.Point
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.math.sqrt

class MedRenderer(
    val color: Color,
    val minX: Float,
    val maxX: Float,
    val maxY: Float,
    val centerX: Float,
    val centerY: Float,
    val radius: Float,
    ) : GLSurfaceView.Renderer {

    private lateinit var xArrow: GLLine
    private lateinit var yArrow: GLLine
    private lateinit var circle: GLPoint
    private var widthToHeightRatio = 0.5f

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        // Set the background frame color
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        val center = Point(centerX, centerY)
        val step = 0.001f
        val circleList = mutableListOf<Point>()
        val sectorGap = sqrt(radius*radius/2)
        val rightBottomGap = Point(center.x + sectorGap, center.y - sectorGap)
        val leftTopGap = Point(center.x - sectorGap, center.y + sectorGap)
        var x = leftTopGap.x
        while (x <= rightBottomGap.x) {
            val a = radius*radius
            val b = x-center.x
            val c = b*b
            val y = sqrt(a - c)
            val y1 = y + center.y
            val y2 = -y + center.y
            circleList.add(Point(x, y1))
            circleList.add(Point(x, y2))

            x+=step
        }
        var y = rightBottomGap.y
        while (y <= leftTopGap.y) {
            val a = radius*radius
            val b = y-center.y
            val c = b*b
            val x = sqrt(a - c)
            val x1 = x + center.x
            val x2 = -x + center.x
            circleList.add(Point(x1, y))
            circleList.add(Point(x2, y))

            y+=step
        }
        circle = GLPoint(color, ::coordsNormalizer, *circleList.toTypedArray())
        xArrow = GLLine(color, Point(-1000f, 0f), Point(1000f, 0f), ::coordsNormalizer)
        yArrow = GLLine(color, Point(0f, -1000f), Point(0f, 1000f), ::coordsNormalizer)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES32.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        // Redraw background color
        glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        xArrow.draw()
        yArrow.draw()
        circle.draw()
    }

    fun setCanvasSize(width: Int, height: Int) {
        widthToHeightRatio = height.toFloat() / width
    }

    fun coordsNormalizer(point: Point) : Point {
        val minY = (maxY - (maxX - minX)) * widthToHeightRatio
        val normalizedX = (point.x - minX) / (maxX - minX) * 2 - 1
        val normalizedY = (point.y - minY) / (maxY - minY) * 2 - 1
        return Point(normalizedX, normalizedY, point.z)
    }
}