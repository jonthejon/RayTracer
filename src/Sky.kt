import java.awt.Color

class Sky : Objects {

    val color: Color = Color(97,215,250)

    override fun checkColision(ray: Vector, origin: Vector): Boolean {

        val testVec = ray + ray
        return (testVec.z > ray.z)
    }

    override fun getColor(): Int {
        return this.color.rgb
    }
}