package location.kotlin.apps.vicky.kotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {


     val lamb={
        i:Int,t:Double -> "value is $i $t"
     }



        assertEquals(4, tun(lamb

    ))
}

fun tun(p:(Int,Double)->String):String{
   return p(55,6.5)
}



}
