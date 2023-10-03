package {{ cookiecutter.package_name }}.screens.about.impl

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Build
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit

@Composable
fun VersionCodeText(modifier: Modifier = Modifier, fontSize: TextUnit = TextUnit.Unspecified) {
    Text(modifier = modifier, text = LocalContext.current.getDisplayVersion(), fontSize = fontSize)
}

private fun Context.getDisplayVersion(): String {
    return try {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val isDebug = ApplicationInfo.FLAG_DEBUGGABLE in packageInfo.applicationInfo.flags
        val versionPostfix = if (isDebug) " - DEBUG" else ""
        readVersion(packageInfo) + versionPostfix
    } catch (ex: Exception) {
        "???"
    }
}

private fun readVersion(packageInfo: PackageInfo): String {
    val versionCode = readVersionCode(packageInfo)
    val versionName = packageInfo.versionName
    return "$versionName ($versionCode)"
}

private operator fun Int.contains(flag: Int) = this and flag == flag
private fun readVersionCode(packageInfo: PackageInfo): Long {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        packageInfo.longVersionCode
    } else {
        @Suppress("DEPRECATION")
        packageInfo.versionCode.toLong()
    }
}
