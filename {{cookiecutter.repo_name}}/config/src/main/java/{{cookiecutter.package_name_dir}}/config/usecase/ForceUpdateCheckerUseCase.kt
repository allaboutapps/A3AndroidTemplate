package {{ cookiecutter.package_name }}.config.usecase

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import {{ cookiecutter.package_name }}.config.data.ConfigRepo
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ForceUpdateCheckerUseCase @Inject constructor(
    context: Context,
    private val configRepo: ConfigRepo,
) {

    private val currentVersionCode: Int = try {
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(context.packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            context.packageManager.getPackageInfo(context.packageName, 0)
        }
        packageInfo.versionCode
    } catch (e: PackageManager.NameNotFoundException) {
        ConfigRepo.VALUE_DEFAULT_MINIMUM_VERSION_CODE
    }

    fun isUpdateRequired(): Observable<Boolean> = configRepo
        .config()
        .map { config ->
            currentVersionCode < config.minSupportedVersionCode
        }
        .distinctUntilChanged()
}
