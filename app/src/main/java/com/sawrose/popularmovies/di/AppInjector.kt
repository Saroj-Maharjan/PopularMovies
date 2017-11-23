package com.sawrose.popularmovies.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.sawrose.popularmovies.MovieApp
import com.sawrose.popularmovies.di.component.DaggerAppComponent
import com.sawrose.popularmovies.di.module.AppModule
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector


object AppInjector {

    fun init(application:MovieApp){
        DaggerAppComponent.builder()
                .application(application)
                .appModule(AppModule(application))
                .build()
                .inject(application)

        application
                .registerActivityLifecycleCallbacks(object :Application.ActivityLifecycleCallbacks{

                    override fun onActivityPaused(activity: Activity?) {
                    }

                    override fun onActivityResumed(activity: Activity?) {
                    }

                    override fun onActivityStarted(activity: Activity?) {

                    }

                    override fun onActivityDestroyed(activity: Activity?) {

                    }

                    override fun onActivitySaveInstanceState(activity: Activity?, p1: Bundle?) {

                    }

                    override fun onActivityStopped(activity: Activity?) {

                    }

                    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
                        handleActivity(activity)
                    }
                })
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector){
            AndroidInjection.inject(activity)
        }

        if (activity is FragmentActivity){
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(
                            object : FragmentManager.FragmentLifecycleCallbacks(){
                                override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                                    super.onFragmentCreated(fm, f, savedInstanceState)
                                    if (f is Injectable) {
                                        AndroidSupportInjection.inject(f)
                                    }

                                }
                            },true
                    )
        }
    }
}