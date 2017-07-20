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
