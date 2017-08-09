import java.awt.Color

data class Hit (val isHit: Boolean, val hitPoint: Point, val ray: Vector, val material: Material) {
    constructor(): this(false, Point(1000000000.0,0.0,0.0), Vector(Point(0.0,0.0,0.0)), Material(Point(0.0,0.0,0.0), 0.0, 0.0))
}