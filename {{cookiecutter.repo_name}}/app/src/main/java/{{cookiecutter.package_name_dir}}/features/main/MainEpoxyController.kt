package com.example.app.features.main

import at.allaboutapps.sharedepoxymodels.empty
import com.airbnb.epoxy.EpoxyController
import com.example.app.R

class MainEpoxyController : EpoxyController() {
    override fun buildModels() {
        empty {
            id(hashCode())
            icon(R.drawable.ic_launcher_foreground)
        }
    }
}
