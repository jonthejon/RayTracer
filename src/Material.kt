data class Material (val albedo: Point, var lambertian: Float, val refl: Float) {
    constructor() : this(Point(1f,1f,1f), 1f, 0f)
}