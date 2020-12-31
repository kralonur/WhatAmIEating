package com.example.whatamieating.repository

import com.example.whatamieating.model.domain.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

abstract class BaseRepository {
    fun <T> flowCall(apiCall: suspend () -> T): Flow<ResultWrapper<T>> = flow {
        emit(ResultWrapper.Loading)
        try {
            emit(ResultWrapper.Success(apiCall.invoke()))
        } catch (_: IOException) {
            emit(ResultWrapper.NetworkError)
        } catch (throwable: HttpException) {
            Timber.e(throwable)
            emit(ResultWrapper.ServerError(throwable.code()))
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            emit(ResultWrapper.Error)
        }
    }
}