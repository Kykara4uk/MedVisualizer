package com.kykara4a.medvisualizer.ui.gl

import android.opengl.GLES32
import androidx.compose.ui.graphics.Color
import com.kykara4a.medvisualizer.domain.model.Point
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

const val COORDS_PER_VERTEX = 3

class GLTriangle(
    val color: Color,
    point1: Point,
    point2: Point,
    point3: Point,
    val coordsNormalizer: (Point) -> Point,
    ) {
    private var mProgram: Int
    private val vertexCount: Int = 9 / COORDS_PER_VERTEX
    private val vertexStride: Int = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

    private val vertexShaderCode =
        "attribute vec4 vPosition;" +
                "void main() {" +
                "  gl_Position = vPosition;" +
                "}"

    private val fragmentShaderCode =
        "precision mediump float;" +
                "uniform vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}"

    private var vertexBuffer: FloatBuffer =
        // (number of coordinate values * 4 bytes per float)
        ByteBuffer.allocateDirect(9 * 4).run {
            // use the device hardware's native byte order
            order(ByteOrder.nativeOrder())

            // create a floating point buffer from the ByteBuffer
            asFloatBuffer()
        }

    // Set color with red, green, blue and alpha (opacity) values
    val colorFloat = floatArrayOf(color.red, color.green, color.blue, 1.0f)

    init {
        setVertices(point1, point2, point3)
        val vertexShader: Int = loadShader(GLES32.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader: Int = loadShader(GLES32.GL_FRAGMENT_SHADER, fragmentShaderCode)

        // create empty OpenGL ES Program
        mProgram = GLES32.glCreateProgram().also {

            // add the vertex shader to program
            GLES32.glAttachShader(it, vertexShader)

            // add the fragment shader to program
            GLES32.glAttachShader(it, fragmentShader)

            // creates OpenGL ES program executables
            GLES32.glLinkProgram(it)
        }

    }

    fun loadShader(type: Int, shaderCode: String): Int {

        // create a vertex shader type (GLES32.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES32.GL_FRAGMENT_SHADER)
        return GLES32.glCreateShader(type).also { shader ->

            // add the source code to the shader and compile it
            GLES32.glShaderSource(shader, shaderCode)
            GLES32.glCompileShader(shader)
        }
    }

    fun draw() {
        // Add program to OpenGL ES environment
        GLES32.glUseProgram(mProgram)

        // get handle to vertex shader's vPosition member
        GLES32.glGetAttribLocation(mProgram, "vPosition").also {

            // Enable a handle to the triangle vertices
            GLES32.glEnableVertexAttribArray(it)

            // Prepare the triangle coordinate data
            GLES32.glVertexAttribPointer(
                it,
                COORDS_PER_VERTEX,
                GLES32.GL_FLOAT,
                false,
                vertexStride,
                vertexBuffer
            )

            // get handle to fragment shader's vColor member
            GLES32.glGetUniformLocation(mProgram, "vColor").also { colorHandle ->

                // Set color for drawing the triangle
                GLES32.glUniform4fv(colorHandle, 1, colorFloat, 0)
            }

            // Draw the triangle
            GLES32.glDrawArrays(GLES32.GL_TRIANGLES, 0, vertexCount)

            // Disable vertex array
            GLES32.glDisableVertexAttribArray(it)
        }
    }

    private fun setVertices(point1: Point, point2: Point, point3: Point) {
        val normalizedCoords = listOf(point1, point2, point3).map(coordsNormalizer)
        val verticesArray = floatArrayOf(
            // X, Y, Z,
            normalizedCoords[0].x, normalizedCoords[0].y, normalizedCoords[0].z,
            normalizedCoords[1].x, normalizedCoords[1].y, normalizedCoords[1].z,
            normalizedCoords[2].x, normalizedCoords[2].y, normalizedCoords[2].z,
        )
        with(vertexBuffer) {
            //clear()
            // add the coordinates to the FloatBuffer
            put(verticesArray)
            // set the buffer to read the first coordinate
            position(0)
        }
    }
}