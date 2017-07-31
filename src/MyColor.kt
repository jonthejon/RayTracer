class MyColor (var r: Float, var g: Float, var b: Float) {
    constructor() : this(0f,0f,0f)

    operator fun times(albedo: Point): MyColor {
        return MyColor(this.r * albedo.x, this.g * albedo.y, this.b * albedo.z)
    }

    operator fun times(flux: Float): MyColor {
        return MyColor(this.r * flux, this.g * flux, this.b * flux)
    }
}