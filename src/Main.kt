public fun main (args: Array<String>) {
    val xRes: Int = 750;
    val yRes: Int = 500;
    var lens: Lens? = null
    for (count in 1..1) {
        println(count)
        lens = Lens(15.0, xRes, yRes, Point(20.0,85.0,0.0), Vector(Point(0.0,1.0,0.0)), 400.0, 1, "wallsTest")
        lens.shootRays()
        lens = null
    }
    Lens.render(xRes, yRes,"wallsTest")

//    W:10, Focal:(-10,75,-5), CamVector:(0,1,1), dist:70 (CIRCLES)
//    W:10, Focal:(5,85,-5), CamVector:(1,1,0), dist:50 (SQUARE)

//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_115")
//    val lens = Lens(200f, 1600, 1200, 25, "floorR_2spheres_1600x1200_140")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_170")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_200")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_230")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_260")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_290")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_320")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_350")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_380")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_410")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_440")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_470")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_500")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_530")
//    val lens = Lens(200f, 1600, 1200, 30, "floorR_2spheres_1600x1200_560")




//    lens.render()
}