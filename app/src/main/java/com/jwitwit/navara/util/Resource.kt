package com.jwitwit.navara.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Insert<T>(data: T): Resource<T>(data)
    class Error<T>(message: String?, data: T? = null): Resource<T>(data, message)
    class Clear<T> : Resource<T>()
}