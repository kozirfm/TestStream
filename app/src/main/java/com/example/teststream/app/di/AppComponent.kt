package com.example.teststream.app.di

import android.app.Application
import com.example.teststream.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        RemoteModule::class,
        InteractorModule::class,
        DataSourceModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

}