
data class Pixel(var color: Int = 0, var numRays: Int = 0) {
    operator fun unaryPlus() {
        this.numRays++
    }
}
//data class Pixel(var red: Int = 0, var green: Int = 0, var blue: Int = 0, var numRays: Int = 0)