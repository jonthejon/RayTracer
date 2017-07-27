abstract class Objects {

//    abstract val material: Material
    abstract val id: Int

    abstract fun getHit(ray: Vector): Hit
    abstract fun checkColision(ray: Vector): Boolean
}