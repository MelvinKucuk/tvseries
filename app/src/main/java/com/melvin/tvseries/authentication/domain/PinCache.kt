package com.melvin.tvseries.authentication.domain

interface PinCache {
    fun savePin(pin: Int)
    fun getPin(): Int
}