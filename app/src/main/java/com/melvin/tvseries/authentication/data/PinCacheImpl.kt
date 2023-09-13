package com.melvin.tvseries.authentication.data

import android.content.SharedPreferences
import com.melvin.tvseries.authentication.domain.PinCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PinCacheImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : PinCache {

    override fun savePin(pin: Int) {
        sharedPreferences.edit()
            .putInt(pinKey, pin)
            .apply()
    }

    override fun getPin(): Int {
        return sharedPreferences.getInt(pinKey, 0)
    }

    companion object {
        private const val pinKey = "pin"
    }
}