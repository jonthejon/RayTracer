import java.awt.Color

/**
 * Created by JonathanOliveira on 18/07/17.
 */

fun main (args: Array<String>) {

    val lens = Lens(200.0, 600, 400)
    lens.shootRays()
    lens.render()

}

//fun MyColor.albedo(albedo: Point): Color {
//    return Color((this.red * albedo.x).toInt(), (this.green * albedo.y).toInt(), (this.blue * albedo.z).toInt())
//}
//
//fun MyColor.lambertian(flux: Double): Color {
////    println(flux)
//    return Color((this.red * flux).toInt(), (this.green * flux).toInt(), (this.blue * flux).toInt())
//}