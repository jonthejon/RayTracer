abstract class Objects {

    abstract val material: Material

    abstract fun checkColision(ray: Vector): Boolean
    abstract fun getColor(): Int
    abstract fun isLightSource(): Boolean
}