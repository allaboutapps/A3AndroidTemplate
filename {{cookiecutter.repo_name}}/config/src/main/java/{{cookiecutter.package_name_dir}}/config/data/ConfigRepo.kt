package {{ cookiecutter.package_name }}.config.data

import android.content.SharedPreferences
import {{ cookiecutter.package_name }}.networking.model.ConfigDto
import {{ cookiecutter.package_name }}.networking.services.ApiService
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ConfigRepo @Inject constructor(
    private val preferences: SharedPreferences,
    @Named(NAME_URL_CONFIG) private val configUrl: String,
    private val apiService: ApiService,
) {

    companion object {
        const val NAME_URL_CONFIG = "NAME_URL_CONFIG"
        const val VALUE_DEFAULT_MINIMUM_VERSION_CODE = -1
        private const val PREF_KEY_MINIMUM_VERSION_CODE = "PREF_KEY_MINIMUM_VERSION_CODE"
    }

    private val refreshRelay = PublishRelay.create<Unit>()

    private val config: Observable<Config> = Observable
        .concat(
            storedConfig(),
            refreshRelay.startWithItem(Unit).switchMapMaybe { fetchAndStoreConfig() },
        )
        .subscribeOn(Schedulers.io())
        .distinctUntilChanged()
        .replay(1)
        .autoConnect()

    fun config(): Observable<Config> = config.hide()

    fun refresh() {
        refreshRelay.accept(Unit)
    }

    private fun fetchAndStoreConfig(): Maybe<Config> = apiService
        .fetchConfig(configUrl)
        .map {
            it.toModel()
        }
        .doOnSuccess {
            storeConfig(it)
        }
        .onErrorComplete()
        .subscribeOn(Schedulers.io())

    private fun ConfigDto.toModel() = Config(minSupportedVersionCode)

    private fun storeConfig(config: Config) {
        preferences
            .edit()
            .putInt(PREF_KEY_MINIMUM_VERSION_CODE, config.minSupportedVersionCode)
            .apply()
    }

    private fun storedConfig(): Observable<Config> = Observable
        .create { emitter ->
            val minSupportedVersionCode = loadStoredConfig()

            emitter.onNext(Config(minSupportedVersionCode))

            emitter.onComplete()
        }
        .subscribeOn(Schedulers.io())

    private fun loadStoredConfig() = preferences.getInt(
        PREF_KEY_MINIMUM_VERSION_CODE,
        VALUE_DEFAULT_MINIMUM_VERSION_CODE,
    )
}
