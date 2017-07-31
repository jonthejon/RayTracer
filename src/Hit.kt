import java.awt.Color

data class Hit (val isHit: Boolean, val hitPoint: Point, val ray: Vector, val material: Material) {
    constructor(): this(false, Point(1000000000f,0f,0f), Vector(Point(0f,0f,0f)), Material(Point(0f,0f,0f), 0f, 0f))
}