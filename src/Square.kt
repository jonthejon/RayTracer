import com.sun.org.apache.bcel.internal.generic.FLOAD

class Square : Objects() {

    val correction: Point = Point(10f,0f,0f)

    val material = Material(Point(0.7f, 0.8f, 0.90f), 1f, 0f)
    val p0: Point = Point(0f, 80f, -10f) + correction
    val p1: Point = Point(10f, 80f, -10f) + correction
    val p2: Point = Point(10f, 80f, 0f) + correction
    val p3: Point = Point(0f, 80f, 0f) + correction
    val p4: Point = Point(10f, 80f, -10f) + correction
    val p5: Point = Point(10f, 80f, 0f) + correction
    val p6: Point = Point(0f, 80f, -10f) + correction
    val p7: Point = Point(0f, 80f, 0f) + correction

    var origin = Point()
    var hitArr = ArrayList<Hit>()
    var finalHit = Hit()


    val triangleArr = ArrayList<Triangle>()

    init {
    val tri1 = Triangle(p0,p1,p2,this.material)
    val tri2 = Triangle(p0,p2,p3,this.material)
    val tri3 = Triangle(p1,p4,p2,this.material)
    val tri4 = Triangle(p4,p5,p2,this.material)
    val tri5 = Triangle(p6,p5,p4,this.material)
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