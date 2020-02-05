package com.kinematik.themoviedb.domain.common

data class DataResult<out T>(val status: Status = Status.SUCCESS, val data: T? = null, val error: Throwable? = null) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): DataResult<T> {
            return DataResult(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(throwable: Throwable?,  data: T? = null): DataResult<T> {
            return DataResult(
                Status.ERROR,
                data,
                throwable
            )
        }

        fun <T> loading(data: T? = null): DataResult<T> {
            return DataResult(
                Status.LOADING,
                data,
                null
            )
        }
    }
}