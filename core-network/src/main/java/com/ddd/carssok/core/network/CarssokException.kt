package com.ddd.carssok.core.network

sealed class CarssokException : Exception() {
    object AuthException : CarssokException()
}