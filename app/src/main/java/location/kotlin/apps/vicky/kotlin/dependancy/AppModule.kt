package location.kotlin.apps.vicky.kotlin.dependancy

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Vicky cj on 03-01-2018.
 */
@Module
class AppModule(private val kotlinApplication: KotlinApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = kotlinApplication

}
