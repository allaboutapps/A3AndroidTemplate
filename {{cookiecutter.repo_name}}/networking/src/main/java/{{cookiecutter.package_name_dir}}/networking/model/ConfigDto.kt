package {{ cookiecutter.package_name }}.networking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigDto(
    @Json(name = "minSupportedVersionCode") val minSupportedVersionCode: Int,
)
