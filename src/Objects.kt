abstract class Objects {

    abstract fun getHit(ray: Vector): Hit
    abstract fun checkCollision(origin: Point, ray: Vector): Boolean
}