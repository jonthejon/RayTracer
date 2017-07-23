import java.awt.Color

data class Hit (val isHit: Boolean, val hitPoint: Point, val ray: Vector, val lambertian: Double, val material: Material, val objId: Int) {
    constructor(): this(false, Point(1000000000.0,0.0,0.0), Vector(Point(0.0,0.0,0.0)), 0.0, Material(Point(0.0,0.0,0.0)), 0)
}