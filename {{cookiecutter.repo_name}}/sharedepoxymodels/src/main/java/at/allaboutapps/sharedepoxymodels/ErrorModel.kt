package at.allaboutapps.sharedepoxymodels

import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.ImageViewCompat
import at.allaboutapps.sharedepoxymodels.databinding.EmptyModelBinding
import at.allaboutapps.sharedepoxymodels.databinding.ErrorModelBinding
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass
abstract class ErrorModel : EpoxyModelWithHolder<ErrorModel.Holder>() {
    override fun getDefaultLayout() = R.layout.error_model

    @EpoxyAttribute @DrawableRes var icon: Int = R.drawable.ic_error
    @EpoxyAttribute @StringRes var title: Int = R.string.general_error_title
    @EpoxyAttribute @StringRes var subtitle: Int = R.string.general_error_subtitle

    override fun bind(holder: Holder) {
        holder.icon.setImageResource(icon)
        holder.title.setText(title)
        holder.subTitle.setText(subtitle)
    }

    inner class Holder : EpoxyHolder() {
        lateinit var icon: AppCompatImageView
        lateinit var title: TextView
        lateinit var subTitle: TextView

        override fun bindView(itemView: View) {
            val binding = ErrorModelBinding.bind(itemView)

            icon = binding.icon
            title = binding.title
            subTitle = binding.subtitle
        }
    }
}
