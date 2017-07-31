fun main (args: Array<String>) {

    val lens = Lens(200f, 600, 400, 200)
    lens.shootRays()
    lens.render()

}