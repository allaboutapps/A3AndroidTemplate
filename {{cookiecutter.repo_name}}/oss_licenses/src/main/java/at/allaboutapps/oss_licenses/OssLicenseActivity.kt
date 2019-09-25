package at.allaboutapps.oss_licenses

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_oss.*


class OssLicenseActivity : AppCompatActivity(R.layout.activity_oss) {

    companion object {
        private const val ARG_SETTINGS = "arg_settings"
        fun showLicenses(context: Context, settings: LicenseScreenSettings) {
            val intent = Intent(context, OssLicenseActivity::class.java)

            intent.putExtra(ARG_SETTINGS, settings)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settings = intent.extras!!.getParcelable<LicenseScreenSettings>(ARG_SETTINGS)!!

        title = settings.title
        supportActionBar?.setDisplayHomeAsUpEnabled(settings.showUpArrow)

        web.loadWithSettings(GlobalWebviewSettings.prepareWebviewSettings())

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

