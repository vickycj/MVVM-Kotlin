package location.kotlin.apps.vicky.kotlin

import android.content.res.Configuration
import android.os.Build
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.RuntimeEnvironment
import org.robolectric.Robolectric
import org.robolectric.util.ActivityController


/**
 * Created by Vicky cj on 05-01-2018.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.LOLLIPOP), packageName = "location.kotlin.apps.vicky.kotlin")
class ActivityTest{


   lateinit var controller: ActivityController<MainActivity>

    @Test
    @Config(qualifiers = "+port")
    fun testOrientationChange() {
        controller = Robolectric.buildActivity(MainActivity::class.java!!)
        controller.setup()

        RuntimeEnvironment.setQualifiers("+land")


        controller.configurationChange(Configuration())

    }

}