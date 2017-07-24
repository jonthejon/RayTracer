import java.awt.Color

/**
 * Created by JonathanOliveira on 18/07/17.
 */

fun main (args: Array<String>) {

    var otherss = Array<Array<Pixel>>(5) {Array<Pixel>(5) {Pixel()}}

    for (pix in otherss) {
        if (pix != null)
        for (pixx in pix) {
            println(pixx)
        }
    }

}

fun Color.albedo(albedo: Point): Color {
    return Color((this.red * albedo.x).toInt(), (this.green * albedo.y).toInt(), (this.blue * albedo.z).toInt())
}

fun Color.lambertian(flux: Double): Color {
//    println(flux)
    return Color((this.red * flux).toInt(), (this.green * flux).toInt(), (this.blue * flux).toInt())
}