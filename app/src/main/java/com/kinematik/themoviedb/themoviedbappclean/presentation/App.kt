package com.kinematik.themoviedb.themoviedbappclean.presentation

import android.app.Activity
import android.app.Application
import com.facebook.FacebookSdk
import com.kinematik.themoviedb.themoviedbappclean.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

        FacebookSdk.sdkInitialize(getApplicationContext())
    }

    override fun activityInjector() = dispatchingAndroidInjector
}