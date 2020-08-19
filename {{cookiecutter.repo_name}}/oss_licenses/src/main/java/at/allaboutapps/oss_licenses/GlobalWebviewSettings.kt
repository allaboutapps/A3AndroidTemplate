package at.allaboutapps.oss_licenses

import at.allaboutapps.web.webview.WebViewSettings

object GlobalWebviewSettings {
    /**
     * Just use the same A3WebviewSettings for OSS dialog and activity
     */
    fun prepareWebviewSettings() = WebViewSettings.loadAssetFile("licenses/index.html")
        .disableLoadingIndicator()
        .openLinksExternally()
}
