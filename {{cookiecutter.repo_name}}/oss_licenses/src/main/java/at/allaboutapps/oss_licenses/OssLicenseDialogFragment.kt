package at.allaboutapps.oss_licenses

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import android.R
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog


class OssLicenseDialogFragment : DialogFragment() {

    companion object {
        fun showLicenseDialog(fragmentManager: FragmentManager, settings: LicenseScreenSettings) {
            val fragment = OssLicenseDialogFragment()
            val args = Bundle()
            args.putParcelable("settings", settings)

            fragment.arguments = args

            fragmentManager.beginTransaction().add(fragment, "licenses_dialog").commit()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val settings = arguments?.getParcelable<LicenseScreenSettings>("settings")

        val webView = WebView(requireActivity())
        webView.loadUrl("file:///android_asset/index.html")

        return AlertDialog.Builder(requireActivity())
                .setTitle(settings?.title)
                .setView(webView)
                .setPositiveButton(R.string.ok) { dialog, which -> dialog.dismiss() }
                .create()
    }
}