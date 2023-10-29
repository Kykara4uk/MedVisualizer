package com.kykara4a.medvisualizer.domain.model

data class Point(
    val x: Float,
    val y: Float,
    val z: Float
) {
    constructor(x: Float, y: Float) : this(x, y, 0f)
}
