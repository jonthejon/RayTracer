import java.awt.Color
import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(30.0, 150.0, 20.0), 15.0)
//    val sky: Sky = Sky()
    val objects = ArrayList<Objects>()
    val color: Color = Color(100,100,100)
    var currId: Int = 0
//    var recursion = 0


    init {
        objects.add(sphere)
//        objects.add(sky)
    }

    fun trace(ray: Vector, recursion: Int): Color {
//        if (recursion > 1) println(recursion)
        if (recursion > 10) return color
        var hit: Hit = Hit()
        var testHit: Hit
        for (obj in objects) {
            if (obj.id == currId || !obj.checkColision(ray)) continue
            testHit = obj.getHit(ray)
//            if (!testHit.isHit) continue
//            println("SKY or BALL")
            if (testHit.hitPoint.getDistance(ray.origin) <= hit.hitPoint.getDistance(ray.origin)) {
//                if (testHit.isHit) println("BALL")
//                if (!testHit.isHit) println("SKY")
//                println("test Vec: " + testHit.hitVec.getModulo() + " | hitVec: " + hit.hitVec.getModulo())
//                println("IS BALL? " + (testHit.obj is Sphere))
//                println("valid hit!")
                hit = testHit
                currId = obj.id
            }
        }
        currId = 0
        if (hit.isHit) {
//            println("hit BALL")
            return this.trace(hit.ray, recursion + 1).albedo(hit.material.albedo).lambertian(hit.lambertian)
//            return ((hit.ray) * (hit.ray) * (hit.material.albedo) * (this.trace(ray, recursion + 1))).toInt()
        }
//        if (recursion < 1) return color
        val testVec = ray + ray
        if (testVec.direction.z > ray.direction.z) {
            return Color(255,255,102)
        } else {
            return color
        }
//        if (hit.isHit) {
//            if (recursion > 0) println(recursion)
//            println("Hit Ball")
//            return this.trace(hit.ray, recursion + 1)
//        }
//        println("Miss or SKY")
//        return hit.color
    }

/*    fun trace(ray: Vector): Int {
        for (obj in objects) {
            while (obj.checkColision(ray)) {
//            while (obj.checkColision(ray, origin)) {
                if (obj is Sky) {
                    return obj.getColor()
                }
                return obj.getColor()
            }
        }
        return color
    }*/
}