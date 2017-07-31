import java.awt.Color
import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(0f, 50f, 0f), 10.0, Material(Point(0.96f, 0.30f,0.20f), 1f, 0f))
    val objects = ArrayList<Objects>()
    val color: MyColor = MyColor(2f,2f,2f)

    init {
        objects.add(sphere)
    }

    fun trace(origin: Point, ray: Vector, recursion: Int): MyColor {
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