data class Composition (val initPoint: Point, val xVector: Vector, val zVector: Vector, val yVector: Vector) : Objects() {

//    val correction: Point = Point(0.0,0.0,0.0)
//
    val material = Material(Point(0.95, 0.2, 0.1), 1.0, 0.0)
//
//    val p0: Point = Point(0.0, 80.0, -10.0) + correction
//    val p1: Point = Point(10.0, 80.0, -10.0) + correction
//    val p2: Point = Point(10.0, 80.0, 0.0) + correction
//    val p3: Point = Point(0.0, 80.0, 0.0) + correction
//    val p4: Point = Point(10.0, 90.0, -10.0) + correction
//    val p5: Point = Point(10.0, 90.0, 0.0) + correction
//    val p6: Point = Point(0.0, 90.0, -10.0) + correction
//    val p7: Point = Point(0.0, 90.0, 0.0) + correction
//
//
//    val p8: Point = Point(0.0, 80.0, -10.0) + correction
//    val p9: Point = Point(10.0, 80.0, -10.0) + correction
//    val p10: Point = Point(10.0, 80.0, 0.0) + correction
//    val p11: Point = Point(0.0, 80.0, 0.0) + correction
//    val p12: Point = Point(10.0, 80.0, -10.0) + correction
//    val p13: Point = Point(10.0, 80.0, 0.0) + correction
//    val p14: Point = Point(0.0, 80.0, -10.0) + correction
//    val p15: Point = Point(0.0, 80.0, 0.0) + correction


    var origin = Point()
    var hitArr = ArrayList<Hit>()
    var finalHit = Hit()


    val triangleArr = ArrayList<Triangle>()

    init {
        val xzDot = xVector * zVector
//        println(xzDot)
        val xyDot = xVector * yVector
//        println(xyDot)
        val yzDot = yVector * zVector
//        println(yzDot)
        val testXZ = xzDot < 0.0001 && xzDot > -0.0001
        val testXY = xyDot < 0.0001 && xyDot > -0.0001
        val testYZ = yzDot < 0.0001 && yzDot > -0.0001
//        println(testXZ)
//        println(testXY)
//        println(testYZ)
        if (!testXZ || !testXY || !testYZ) throw IllegalArgumentException()
//    val tri1 = Triangle(p0,p1,p2,this.material) // ok
//    val tri2 = Triangle(p0,p2,p3,this.material) // ok
//    val tri3 = Triangle(p1,p4,p2,this.material) // ok
//    val tri4 = Triangle(p4,p5,p2,this.material) // ok
//    val tri5 = Triangle(p6,p5,p4,this.material) //
//    val tri6 = Triangle(p6,p7,p5,this.material)
//    val tri7 = Triangle(p6,p3,p7,this.material)
//    val tri8 = Triangle(p0,p3,p6,this.material)
//    val tri9 = Triangle(p3,p2,p5,this.material)
//    val tri10 = Triangle(p3,p5,p7,this.material)
//    val tri11 = Triangle(p1,p0,p4,this.material)
//    val tri12 = Triangle(p0,p6,p4,this.material)
//    triangleArr.add(tri1)
//    triangleArr.add(tri2)
//    triangleArr.add(tri3)
//    triangleArr.add(tri4)
//    triangleArr.add(tri5)
//    triangleArr.add(tri6)
//    triangleArr.add(tri7)
//    triangleArr.add(tri8)
//    triangleArr.add(tri9)
//    triangleArr.add(tri10)
//    triangleArr.add(tri11)
//    triangleArr.add(tri12)
    }

    fun createWall(point: Point, vec1: Vector, vec2:Vector, side1: Double, side2: Double): Unit {
//        defining the 4 points that will compose this createWall
        val p0: Point = Point()
        val p1: Point = Point()
        val p2: Point = Point()
        val p3: Point = Point()
//        unitizing the vectors that will guide this construction
        val uVec1 = !vec1
        val uVec2 = !vec2
//        calculating the position of the other points of this createWall
        val sp1: Point = point + (uVec1 * side1).direction
        val sp2: Point = sp1 + (uVec2 * side2).direction
        val sp3: Point = point + (uVec2 * side2).direction
//        setting the points of this createWall to the calculated points
        p0.setPoint(point) // setting p0 as the starting point of this createWall
        p1.setPoint(sp1)
        p2.setPoint(sp2)
        p3.setPoint(sp3)
//        creating the triangles that compose this createWall.
        val tri0: Triangle = Triangle(p0,p1,p2,material)
        val tri1: Triangle = Triangle(p0,p2,p3,material)
//        adding the triangles to the triangle array to they can be added to the scene
        triangleArr.add(tri0)
        triangleArr.add(tri1)
    }

    fun createCube(point: Point, vec1: Vector, vec2:Vector, vec3:Vector, side1: Double, side2: Double, side3: Double): Unit {
//        defining the guiding points of this createCube
        val p0: Point = Point()
        val p1: Point = Point()
//        unitizing the vectors that will guide this construction
        val uVec1 = !vec1
        val uVec2 = !vec2
        val uVec3 = !vec3
        val negUVec1 = uVec1 * (-1.0)
        val negUVec2 = uVec2 * (-1.0)
        val negUVec3 = uVec3 * (-1.0)
        val sp01: Point = point + (uVec1 * side1).direction
        val sp02: Point = sp01 + (uVec2 * side2).direction
        val sp1: Point = sp02 + (uVec3 * side3).direction
        p0.setPoint(point)
        p1.setPoint(sp1)

//        face 1 (facing me)
        this.createWall(p0, uVec1, uVec2, side1, side2)
//        face 2 (ground)
        this.createWall(p0, uVec3, uVec1, side3, side1)
//        face 3 (left me)
        this.createWall(p0, uVec2, uVec3, side2, side3)
//        face 4 (right me)
        this.createWall(p1, negUVec3, negUVec2, side3, side2)
//        face 5 (back)
        this.createWall(p1, negUVec2, negUVec1, side2, side1)
//        face 6 (top)
        this.createWall(p1, negUVec1, negUVec3, side1, side3)
    }

    override fun getHitObj(): Hit {
        return finalHit
    }

    override fun checkCollision(origin: Point, ray: Vector): Boolean {
        this.origin = origin
        var isHit = false
        triangleArr.forEach {
            isHit = it.checkCollision(origin, ray)
            if (isHit) {
                hitArr.add(it.getHitObj())
            }
        }
        var bestHit = Hit()
        for (hit in hitArr) {
            if (hit.hitPoint.getDistance(origin) < bestHit.hitPoint.getDistance(origin)) {
                bestHit = hit
            }
        }
        finalHit = bestHit
        hitArr.clear()
        return isHit
    }
}