data class Material (val albedo: Point, var lambertian: Double, val refl: Double) {
    constructor() : this(Point(1.0,1.0,1.0), 1.0, 0.0)
}