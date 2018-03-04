package location.kotlin.apps.vicky.kotlin.dependancy

import android.app.Application

/**
 * Created by Vicky cj on 03-01-2018.
 */
class KotlinApplication: Application(){
    companion object {
        lateinit var appComponent: AppComponent

    }

    override fun onCreate() {
         super.onCreate()
        daggerInitialization()
    }


     fun daggerInitialization() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule())
               .build()
    }




}