package at.allaboutapps.sharedepoxymodels

import android.view.View
import at.allaboutapps.sharedepoxymodels.databinding.LoadingModelBinding
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.progressindicator.CircularProgressIndicator

@EpoxyModelClass
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.Holder>() {
    override fun getDefaultLayout() = R.layout.loading_model

    inner class Holder : EpoxyHolder() {
        lateinit var progressIndicator: CircularProgressIndicator

        override fun bindView(itemView: View) {
            val binding = LoadingModelBinding.bind(itemView)

            progressIndicator = binding.progressIndicator
        }
    }
}
