package at.allaboutapps.a3template.features.start

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import at.allaboutapps.a3template.R
import at.allaboutapps.a3template.base.BaseActivity
import at.allaboutapps.a3template.features.start.ui.main.MainViewModel
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        Timber.d("hallo log")
    }
}
