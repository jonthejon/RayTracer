import com.sun.org.apache.bcel.internal.generic.FLOAD

class Square : Objects() {

    val correction: Point = Point(0.0,0.0,0.0)

    val material = Material(Point(0.55, 0.8, 0.20), 1.0, 0.0)

    val p0: Point = Point(0.0, 80.0, -10.0) + correction
    val p1: Point = Point(10.0, 80.0, -10.0) + correction
    val p2: Point = Point(10.0, 80.0, 0.0) + correction
    val p3: Point = Point(0.0, 80.0, 0.0) + correction
    val p4: Point = Point(10.0, 90.0, -10.0) + correction
    val p5: Point = Point(10.0, 90.0, 0.0) + correction
    val p6: Point = Point(0.0, 90.0, -10.0) + correction
    val p7: Point = Point(0.0, 90.0, 0.0) + correction


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
    val tri1 = Triangle(p0,p1,p2,this.material) // ok
    val tri2 = Triangle(p0,p2,p3,this.material) // ok
    val tri3 = Triangle(p1,p4,p2,this.material) // ok
    val tri4 = Triangle(p4,p5,p2,this.material) // ok
    val tri5 = Triangle(p6,p5,p4,this.material) //
    val tri6 = Triangle(p6,p7,p5,this.material)
    val tri7 = Triangle(p6,p3,p7,this.material)
    val tri8 = Triangle(p0,p3,p6,this.material)
    val tri9 = Triangle(p3,p2,p5,this.material)
    val tri10 = Triangle(p3,p5,p7,this.material)
    val tri11 = Triangle(p1,p0,p4,this.material)
    val tri12 = Triangle(p0,p6,p4,this.material)
    triangleArr.add(tri1)
    triangleArr.add(tri2)
    triangleArr.add(tri3)
    triangleArr.add(tri4)
    triangleArr.add(tri5)
    triangleArr.add(tri6)
    triangleArr.add(tri7)
    triangleArr.add(tri8)
    triangleArr.add(tri9)
    triangleArr.add(tri10)
    triangleArr.add(tri11)
    triangleArr.add(tri12)
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