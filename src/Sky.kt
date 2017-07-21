import java.awt.Color

class Sky : Objects() {

    val color: Color = Color(97,215,250)

    override var material: Material = Material(1.0)

    override fun checkColision(ray: Vector): Boolean {
        val testVec = ray + ray
        return (testVec.direction.z > ray.direction.z)
    }

    override fun getColor(): Int {
        return this.color.rgb
    }

    override fun isLightSource(): Boolean {
        return true
    }
}