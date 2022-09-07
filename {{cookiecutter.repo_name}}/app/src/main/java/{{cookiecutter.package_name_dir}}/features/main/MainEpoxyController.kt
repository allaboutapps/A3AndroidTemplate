package com.example.app.features.main

import at.allaboutapps.sharedepoxymodels.loading
import com.airbnb.epoxy.EpoxyController
import com.example.app.R

class MainEpoxyController : EpoxyController() {
    override fun buildModels() {
        loading {
            id(hashCode())
        }
    }
}
