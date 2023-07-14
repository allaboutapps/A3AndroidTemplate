package {{ cookiecutter.package_name }}.features.forceupdate

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import {{ cookiecutter.package_name }}.R
import {{ cookiecutter.package_name }}.base.BaseActivity
import com.google.android.gms.tasks.Task
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber

class ForceUpdateActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context) = Intent(context, ForceUpdateActivity::class.java)
    }

    private val appUpdateManager by lazy {
        AppUpdateManagerFactory.create(this)
    }

    private var disposeOnDestroy = Disposable.disposed()

    private val onAppUpdateResult = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
        if (it.resultCode == RESULT_OK) {
            Timber.d("InAppUpdate flow completed successfully")
            finish()
        } else {
            Timber.d("InAppUpdate flow cancelled or failed with result code: ${it.resultCode}")
            findViewById<ViewGroup>(R.id.layoutRoot).isVisible = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val rootView = findViewById<ViewGroup>(R.id.layoutRoot)
        val buttonView = findViewById<Button>(R.id.btUpdate)

        if (savedInstanceState == null) {
            disposeOnDestroy = startInAppUpdateFlow().subscribe(
                { isStarted ->
                    if (isStarted) {
                        Timber.d("InAppUpdate flow started")
                    } else {
                        Timber.d("InAppUpdate flow not started")
                        rootView.isVisible = true
                    }
                },
                {
                    Timber.e(it, "InAppUpdate flow error")
                    rootView.isVisible = true
                },
                {
                    Timber.d("InAppUpdate flow canceled")
                    rootView.isVisible = true
                },
            )
        } else {
            rootView.isVisible = true
        }

        buttonView.setOnClickListener {
            startActivity(getPlayStoreIntent())
        }
    }

    override fun onDestroy() {
        disposeOnDestroy.dispose()
        super.onDestroy()
    }

    private fun startInAppUpdateFlow(): Maybe<Boolean> = appUpdateManager.appUpdateInfo.maybe().map { appUpdateInfo ->
        val updateOptions = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()

        val updateAvailability = appUpdateInfo.updateAvailability()

        val isUpdateAvailable = updateAvailability == UpdateAvailability.UPDATE_AVAILABLE
        val isImmediateUpdateAvailable = isUpdateAvailable && appUpdateInfo.isUpdateTypeAllowed(updateOptions)
        val isUpdateInProgress = updateAvailability == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS

        (isImmediateUpdateAvailable || isUpdateInProgress) && appUpdateManager.startUpdateFlowForResult(
            appUpdateInfo,
            onAppUpdateResult,
            updateOptions,
        )
    }

    private fun <T : Any> Task<T>.maybe(): Maybe<T> = Maybe.create { emitter ->
        this.addOnCanceledListener { emitter.onComplete() }.addOnFailureListener { emitter.tryOnError(it) }
            .addOnSuccessListener { emitter.onSuccess(it) }
    }

    private fun getPlayStoreIntent(): Intent {
        val playUri = Uri.parse("market://details?id=$packageName")
        val playIntent = Intent(Intent.ACTION_VIEW, playUri)

        return if (playIntent.resolveActivity(packageManager) != null) {
            playIntent
        } else {
            val webUri = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            Intent(Intent.ACTION_VIEW, webUri)
        }
    }
}
