import java.awt.Color

data class Sphere (val center: Point, val r: Double) : Objects {

    val color: Color = Color(12,171,23)

    override fun checkColision(ray: Vector, origin: Vector): Boolean {
        val vecCenter: Vector = Vector(center.x, center.y, center.z)
        val a: Double = Math.pow(ray * (origin - vecCenter),2.0)
        val b: Double = Math.pow((origin - vecCenter).getLength(),2.0)
        val c: Double = Math.pow(r,2.0)
        return ((a - b + c) >= 0)
    }

    override fun getColor(): Int {
        return this.color.rgb
    }
}