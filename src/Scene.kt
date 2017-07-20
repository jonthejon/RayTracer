import java.awt.Color
import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(0.0, 100.0, 0.0), 50.0)
    val sky: Sky = Sky()
    var objects = ArrayList<Objects>()

    init {
        objects.add(sky)
        objects.add(sphere)
    }

    fun computeColor(ray: Vector, origin: Vector): Int {
        var color: Int = 0
        for (obj in objects) {
            while (obj.checkColision(ray, origin)) {
                color = obj.getColor()
                if (color != 0) break
            }
        }
        /*while (sphere.checkColision(ray, origin)) {
            color = sphere.color.rgb
            if (color != 0) break
        }*/
        return color
    }

/*    fun checkSphere(ray: Vector): Int {
        val vecCamera: Vector = Vector(0.0,0.0,0.0)
        val vecSphereCenter: Vector = Vector(sphere.center.x, sphere.center.y, sphere.center.z)
//        println("sphere: " + vecSphereCenter.x  + " " + vecSphereCenter.y + " " + vecSphereCenter.z)
        val a: Double = Math.pow(ray * (vecCamera - vecSphereCenter),2.0)
        val b: Double = Math.pow((vecCamera - vecSphereCenter).getLength(),2.0)
        val c: Double = Math.pow(sphere.r,2.0)
        if ((a - b + c) >= 0) {
            return sphere.color.rgb
        } else {
            return 0
        }
    }*/
}