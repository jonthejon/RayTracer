import java.awt.Color

class Sky : Objects() {

    override val id: Int = -1
    val color: Color = Color(97,215,250)

    override var material: Material = Material(Point(0.0,0.0,0.0))

    override fun getHit(ray: Vector): Hit {
        val testVec = ray + ray
        if (testVec.direction.z > ray.direction.z) {
//            print("Hit SKY -> ")
            return Hit(false,Point(),Vector(),0.0,material, this.getColor())
        } else {
            return Hit()
        }
    }

    override fun checkColision(ray: Vector): Boolean {
        val testVec = ray + ray
        return (testVec.direction.z > ray.direction.z)
    }

    fun getColor(): Int {
        return this.color.rgb
    }
}