data class Point (val x: Double, val y: Double, val z: Double) {
    operator fun plus(other: Point): Point {
        return Point(this.x + other.x, this.y + other.y, this.z + other.z)
    }

    operator fun times(num: Int): Point {
        return Point(this.x * num, this.y * num, this.z * num)
    }
}