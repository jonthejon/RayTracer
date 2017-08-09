import java.util.ArrayList

class Scene {
//    val sphere: Sphere = Sphere(Point(-13.0, 80.0, 0.0), 10.0, Material(Point(0.96, 0.30,0.20), 1.0, 0.0))
//    val sphere2: Sphere = Sphere(Point(30.0, 97.0, -3.0), 7.0, Material(Point(0.9, 0.8,0.10), 1.0, 0.0))
//    val sphere2: Sphere = Sphere(Point(5.0, 85.0, 7.0), 7.0, Material(Point(0.9, 0.8,0.10), 1.0, 0.0))
//    val square: Square = Square()
//    val p0: Point = Point(-10f,80f,-10f)
//    val p1: Point = Point(10f,80f,-10f)
//    val p2: Point = Point(0f,90f,10f)
//    val triangle: Triangle = Triangle(p0, p1, p2, Material(Point(0.9f, 0.8f,0.10f), 1f, 0f))
    val cube: Composition = Composition(Point(-50.0,70.0,-10.0), Vector(Point(1.0,0.0,0.0)), Vector(Point(0.0,0.0,1.0)), Vector(Point(0.0,1.0,0.0)))
    val floor: Floor = Floor(Point(0.0, 0.0, -10.0), Vector(Point(0.0,0.0,1.0)), Material(Point(0.5, 0.5,0.5), 1.0, 0.0))
    val objects = ArrayList<Objects>()
    val color: MyColor = MyColor(2.9,2.9,2.9)

    init {
        val (p,x,z,y) = cube
        val newp = p + (!x * 99.5).direction
        val newpp = p + (!z * 45.0).direction
        cube.createCube(p,x,z,y,0.5,45.0,100.0)
        cube.createCube(p,x,z,y,100.0,45.0,0.5)
        cube.createCube(newp,x,z,y,0.5,45.0,100.0)
        cube.createCube(newpp,x,z,y,100.0,0.5,100.0)
//        objects.add(sphere)
//        objects.add(sphere2)
//        objects.add(square)
//        objects.add(triangle)
        objects.add(floor)
        cube.triangleArr.forEach{
            objects.add(it)
        }
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