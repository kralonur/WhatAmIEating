package com.example.whatamieating.model.domain

sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    object Error : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
    data class ServerError(val code: Int = 0) : ResultWrapper<Nothing>()
}