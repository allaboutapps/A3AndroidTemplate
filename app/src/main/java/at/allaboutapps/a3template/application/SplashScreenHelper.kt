package at.allaboutapps.a3template.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import at.allaboutapps.a3template.R

internal class SplashScreenHelper : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // apply the actual theme
        activity.setTheme(R.style.AppTheme)
    }

    // region unused overrides
    override fun onActivityPaused(p0: Activity?) = Unit

    override fun onActivityResumed(p0: Activity?) = Unit
    override fun onActivityStarted(p0: Activity?) = Unit
    override fun onActivityDestroyed(p0: Activity?) = Unit
    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) = Unit
    override fun onActivityStopped(p0: Activity?) = Unit
    // endregion
}