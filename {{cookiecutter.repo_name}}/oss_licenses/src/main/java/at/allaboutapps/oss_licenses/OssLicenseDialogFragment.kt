package at.allaboutapps.oss_licenses

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AlertDialog
import at.allaboutapps.web.webview.A3WebView

class OssLicenseDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_SETTINGS = "arg_settings"

        fun showLicenseDialog(fragmentManager: FragmentManager, settings: LicenseScreenSettings) {
            val fragment = OssLicenseDialogFragment()
            val args = Bundle()
            args.putParcelable(ARG_SETTINGS, settings)

            fragment.arguments = args

            fragmentManager.beginTransaction().add(fragment, "licenses_dialog").commit()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val settings = requireArguments().getParcelable<LicenseScreenSettings>(ARG_SETTINGS)!!

        val webView = A3WebView(requireActivity())

        webView.loadWithSettings(GlobalWebviewSettings.prepareWebviewSettings())

        return AlertDialog.Builder(requireActivity())
            .setTitle(settings.title)
            .setView(webView)
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .create()
    }
}
