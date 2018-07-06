package com.palfinger.ehmi.application

import android.app.Application
import android.arch.persistence.room.Room
import at.allaboutapps.a3template.BuildConfig
import com.evernote.android.job.Job
import com.evernote.android.job.JobManager
import com.palfinger.ehmi.BuildConfig
import com.palfinger.ehmi.MainActivity
import com.palfinger.ehmi.data.db.AppDatabase
import com.palfinger.ehmi.features.jobs.EquipmentInitializationJob
import com.palfinger.ehmi.features.jobs.PalfingerJobCreator
import com.palfinger.ehmi.features.login.LoginManager
import com.palfinger.palfingerapi.apiclient.ehmi.EhmiService
import timber.log.Timber
import javax.inject.Provider


class CustomApp : Application() {


  override fun onCreate() {
    super.onCreate()

    initLogging()
    // register the util to remove splash screen after loading
    registerActivityLifecycleCallbacks(SplashScreenHelper())
  }

  private fun initLogging() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}

