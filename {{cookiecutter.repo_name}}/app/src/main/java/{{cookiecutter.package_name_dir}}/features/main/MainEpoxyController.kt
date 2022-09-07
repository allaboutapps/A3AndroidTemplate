package com.example.app.features.main

import at.allaboutapps.sharedepoxymodels.header
import com.airbnb.epoxy.EpoxyController
import com.example.app.R

class MainEpoxyController : EpoxyController() {
    override fun buildModels() {
        header {
            id(hashCode())
            title("Titel")
        }
    }
}
