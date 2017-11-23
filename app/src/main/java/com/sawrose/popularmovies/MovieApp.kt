package com.sawrose.popularmovies

import android.app.Activity
import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.google.firebase.crash.FirebaseCrash
import com.sawrose.popularmovies.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MovieApp: Application(),HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        if (BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
            Timber.plant(Timber.DebugTree())
        }
        else{
            Timber.plant(CrashReportingTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }

            FirebaseCrash.log(message)

            if (t != null) {
                if (priority == Log.ERROR) {
                    FirebaseCrash.report(t)
                } else if (priority == Log.WARN) {
                    FirebaseCrash.report(t)
                }
            }
        }
    }
}