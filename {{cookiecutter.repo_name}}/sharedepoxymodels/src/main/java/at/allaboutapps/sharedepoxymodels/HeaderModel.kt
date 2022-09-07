package at.allaboutapps.sharedepoxymodels

import android.view.View
import android.widget.TextView
import at.allaboutapps.sharedepoxymodels.databinding.HeaderModelBinding
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass
abstract class HeaderModel : EpoxyModelWithHolder<HeaderModel.Holder>() {
    override fun getDefaultLayout() = R.layout.header_model

    @EpoxyAttribute lateinit var title: String

    override fun bind(holder: Holder) {
        holder.tvHeader.text = title
    }

    inner class Holder : EpoxyHolder() {
        lateinit var tvHeader: TextView

        override fun bindView(itemView: View) {
            val binding = HeaderModelBinding.bind(itemView)

            tvHeader = binding.tvHeader
        }
    }
}
