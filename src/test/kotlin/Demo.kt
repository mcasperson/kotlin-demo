import org.junit.Test

class Demo {

    private val name: String = "Demo Class"

    @Test
    fun runExample () {
        val result = "Local String".run {
            System.out.println(this) // prints "Local String"
            "New String"
        }
        System.out.println(result) // prints "New String"
    }

    @Test
    fun letExample () {
        val result = "Local String".let {
            System.out.println(this.name) // prints "Demo Class"
            System.out.println(it) // prints "Local String"
            "New String"
        }
        System.out.println(result) // prints "New String"
    }

    @Test
    fun letExample2 () {
        val result = "Local String".let {me ->
            System.out.println(this.name) // prints "Demo Class"
            System.out.println(me) // prints "Local String"
            "New String"
        }
        System.out.println(result) // prints "New String"
    }

    @Test
    fun applyExample() {
        val result = "Local String".apply {
            System.out.println(this) // prints "Local String"
            "New String" // return value is ignored
        }
        System.out.println(result) // prints "Local String"
    }

    @Test
    fun alsoExample() {
        val result = "Local String".also {
            System.out.println(this.name) // prints "Demo Class"
            System.out.println(it) // prints "Local String"
            "New String" // return value is ignored
        }
        System.out.println(result) // prints "Local String"
    }

    @Test
    fun alsoExample2() {
        val result = "Local String".also { me ->
            System.out.println(this.name) // prints "Demo Class"
            System.out.println(me) // prints "Local String"
            "New String" // return value is ignored
        }
        System.out.println(result) // prints "Local String"
    }
}