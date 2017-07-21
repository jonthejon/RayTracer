import java.util.ArrayList

class Scene {
    val sphere: Sphere = Sphere(Vector(30.0, 300.0, 45.0), 30.0)
    val sky: Sky = Sky()
    var objects = ArrayList<Objects>()
    var color: Int = 0
//    var color: Int = sphere.getAlbedo()

    init {
        objects.add(sphere)
        objects.add(sky)
    }

    fun trace(ray: Vector, origin: Vector): Int {
        var counter = 0
        for (obj in objects) {
            while (obj.checkColision(ray, origin)) {
                if (obj is Sky) {
                    return obj.getColor()
                }
                return obj.getColor()
/*                counter++
                println(counter)
                var reflexion: Vector = obj.getReflectVec()
                println("reflexion: " + reflexion.x  + " " + reflexion.y + " " + reflexion.z)
                var normal: Vector = obj.getNormalVec()
                println("normal: " + normal.x  + " " + normal.y + " " + normal.z)
                this.trace(reflexion, normal)
                if (counter > 5){
                    break
                }*/
            }
        }
        return color
    }
}