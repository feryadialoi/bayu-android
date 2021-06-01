package dev.feryadi.bayu

import android.app.Application
import android.os.Build
import android.os.LocaleList
import android.util.Log
import androidx.annotation.RequiresApi
import dagger.hilt.android.HiltAndroidApp
import dev.feryadi.bayu.locale.Language
import java.util.*

@HiltAndroidApp
class BayuApplication : Application() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate() {
        super.onCreate()

        setLocale();

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setLocale(language: Language = Language.IN) {
        Log.i("Application", "setLocale ${language.name}")
        val languageName = language.name.toLowerCase()
        val localeList = LocaleList(Locale(languageName))
        resources.configuration.setLocales(localeList)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }
}