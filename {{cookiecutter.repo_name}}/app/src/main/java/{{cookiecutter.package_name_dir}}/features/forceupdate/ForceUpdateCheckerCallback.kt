package {{ cookiecutter.package_name }}.features.forceupdate

import android.app.Activity
import android.app.Application
import android.os.Bundle
import {{ cookiecutter.package_name }}.config.usecase.ForceUpdateCheckerUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ForceUpdateCheckerCallback @Inject constructor(
    private val forceUpdateCheckerUseCase: ForceUpdateCheckerUseCase,
) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit
    override fun onActivityStarted(activity: Activity) {
        if (activity is ForceUpdateActivity) return

        val disposable = forceUpdateCheckerUseCase
            .isUpdateRequired()
            .take(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isUpdateRequired ->
                if (isUpdateRequired) {
                    activity.startActivity(ForceUpdateActivity.newIntent(activity))
                    activity.finishAffinity()
                }
            }
    }

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}
