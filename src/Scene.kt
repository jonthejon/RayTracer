import java.awt.Color
import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(45.0, 250.0, 45.0), 20.0)
//    val sky: Sky = Sky()
    var objects = ArrayList<Objects>()
    var color: Color = Color(0,0,0)
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
            if (testHit.hitVec.getModulo() <= hit.hitVec.getModulo()) {
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
            return this.trace(hit.ray, recursion + 1).albedo(hit.material.albedo)
//            return ((hit.ray) * (hit.ray) * (hit.material.albedo) * (this.trace(ray, recursion + 1))).toInt()
        }
//        if (recursion < 1) return color
        val testVec = ray + ray
        if (testVec.direction.z > ray.direction.z) {
            return Color(97,215,250)
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