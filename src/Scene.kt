import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(-13f, 80f, 0f), 10f, Material(Point(0.96f, 0.30f,0.20f), 1f, 0f))
    val sphere2: Sphere = Sphere(Point(0f, 69f, -3f), 7f, Material(Point(0.9f, 0.8f,0.10f), 1f, 0f))
//    val square: Square = Square()
//    val p0: Point = Point(-10f,80f,-10f)
//    val p1: Point = Point(10f,80f,-10f)
//    val p2: Point = Point(0f,90f,10f)
//    val triangle: Triangle = Triangle(p0, p1, p2, Material(Point(0.9f, 0.8f,0.10f), 1f, 0f))
    val floor: Floor = Floor(Point(0f, 0f, -10f), Vector(Point(0f,0f,1f)), Material(Point(0.1f, 0.65f,0.75f), 1f, 0.75f))
    val objects = ArrayList<Objects>()
    val color: MyColor = MyColor(3.5f,3.5f,3.5f)

    init {
        objects.add(sphere)
        objects.add(sphere2)
//        objects.add(square)
//        objects.add(triangle)
        objects.add(floor)
//        square.triangleArr.forEach{
//            objects.add(it)
//        }
    }

    fun trace(origin: Point, ray: Vector, recursion: Int): MyColor {
//        if (recursion > 7) {
//            println("I'm deep!")
//        }
//        println("Recursion: " + recursion)
        if (recursion > 10) return color
        var hit: Hit = Hit()
        var testHit: Hit
        for (obj in objects) {
            if (!obj.checkCollision(origin, ray)) continue
            testHit = obj.getHitObj()
            if (testHit.hitPoint.getDistance(origin) <= hit.hitPoint.getDistance(origin)) {
                hit = testHit
            }
        }
        if (hit.isHit) {
            return this.trace(hit.hitPoint, hit.ray, recursion + 1)*(hit.material.albedo)*(hit.material.lambertian)
        }
        return color
    }
}