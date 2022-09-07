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
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass
abstract class EmptyModel : EpoxyModelWithHolder<EmptyModel.Holder>() {
    override fun getDefaultLayout() = R.layout.empty_model

    @EpoxyAttribute @DrawableRes var icon: Int? = null
    @EpoxyAttribute @StringRes var title: Int = R.string.general_empty_title
    @EpoxyAttribute @StringRes var subtitle: Int = R.string.general_empty_subtitle

    override fun bind(holder: Holder) {
        if (icon != null) {
            holder.icon.setImageResource(icon!!)
            holder.icon.isVisible = true
        } else {
            holder.icon.isGone = true
        }

        holder.title.setText(title)
        holder.subTitle.setText(subtitle)
    }

    inner class Holder : EpoxyHolder() {
        lateinit var icon: AppCompatImageView
        lateinit var title: TextView
        lateinit var subTitle: TextView

        override fun bindView(itemView: View) {
            val binding = EmptyModelBinding.bind(itemView)

            icon = binding.icon
            title = binding.title
            subTitle = binding.subtitle
        }
    }
}
