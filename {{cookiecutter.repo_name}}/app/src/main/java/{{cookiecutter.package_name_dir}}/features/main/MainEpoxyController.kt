package com.example.app.features.main

import at.allaboutapps.sharedepoxymodels.error
import com.airbnb.epoxy.EpoxyController
import com.example.app.R

class MainEpoxyController : EpoxyController() {
    override fun buildModels() {
        error {
            id(hashCode())
        }
    }
}
