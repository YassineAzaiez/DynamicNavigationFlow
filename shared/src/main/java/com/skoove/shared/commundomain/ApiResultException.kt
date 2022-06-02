package com.skoove.shared.commundomain


sealed class ApiResultException(throwable: Throwable?, message: String? = "") :
    RuntimeException(message) {


    class Connection(throwable: Throwable, message: String) : ApiResultException(throwable, message)

    class Unexpected(throwable: Throwable, message: String) : ApiResultException(throwable, message)

    class Timeout(throwable: Throwable, message: String) : ApiResultException(throwable, message)

    class EmptyReturn(throwable: Throwable, message: String) :
        ApiResultException(throwable, message)

    class HttpError(message: String) : ApiResultException(null, message)

    class SharedPrefError(message: String) : ApiResultException(null, message)

    class CacheError(message: String) : ApiResultException(null, message)

}