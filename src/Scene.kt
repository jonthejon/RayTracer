import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Point(30.0, 300.0, 45.0), 30.0)
    val sky: Sky = Sky()
    var objects = ArrayList<Objects>()
    var color: Int = 0

    init {
        objects.add(sphere)
        objects.add(sky)
    }

//    fun trace(ray: Vector, origin: Vector): Int {
    fun trace(ray: Vector): Int {
        var counter = 0
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
    }
}