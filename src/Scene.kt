import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(-13f, 80f, 0f), 10f, Material(Point(0.96f, 0.30f,0.20f), 1f, 0f))
    val sphere2: Sphere = Sphere(Point(0f, 69f, -3f), 7f, Material(Point(0.9f, 0.8f,0.10f), 1f, 0f))
    val floor: Floor = Floor(Point(0f, 0f, -10f), Vector(Point(0f,0f,1f)), Material(Point(0.1f, 0.65f,0.75f), 1f, 0.75f))
    val objects = ArrayList<Objects>()
    val color: MyColor = MyColor(2.8f,2.8f,2.8f)

    init {
        objects.add(sphere)
        objects.add(sphere2)
        objects.add(floor)
    }

    fun trace(origin: Point, ray: Vector, recursion: Int): MyColor {
//        println("Recursion: " + recursion)
        if (recursion > 10) return color
        var hit: Hit = Hit()
        var testHit: Hit
        for (obj in objects) {
            if (!obj.checkCollision(origin, ray)) continue
            testHit = obj.getHit(ray)
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