package com.aljawad.sons.mainlibrary.states

import com.aljawad.sons.mainlibrary.models.ErrorMessage
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.net.UnknownHostException

sealed class BaseState<out T> { //where T : Any

    // to add auto complete option in when
    val <T> T.exhaustive: T
        get() = this

    class Loading<T>() : BaseState<T>()
    class LoadingDismiss<T>() : BaseState<T>()
    class NotDataFound<T>() : BaseState<T>()
    class NoInternetError<T>() : BaseState<T>()

    data class Conflict<T>(var message: String? = null) : BaseState<T>()
    data class InternalServerError<T>(var message: String? = null) : BaseState<T>()
    data class NoAuthorized<T>(var message: String? = null) : BaseState<T>()

    abstract class FeaturedState<T> : BaseState<T>()

    companion object {
        fun <T> getStateByThrowable(it: Throwable, retrofit: Retrofit? = null): BaseState<T> {
            if (it is retrofit2.HttpException) {
                val response = it.response()
                val errorBody = response?.errorBody()
                val result = Gson().fromJson(
                    response!!.errorBody()!!.charStream(),
                    ErrorMessage::class.java
                )

//                val converter: Converter<ResponseBody, BaseResponse<*>>? =
//                    retrofit?.responseBodyConverter(
//                        BaseResponse::class.java,
//                        arrayOf()
//                    )
//                val result: BaseResponse<*>? = errorBody?.let { converter?.convert(it) }

                return when (it.code()) {
                    HttpResponseCodeEnum.UN_AUTHORIZED.code -> {
                        NoAuthorized(result?.getErrorsCollections() ?: "")
                    }
                    HttpResponseCodeEnum.NO_DATA_FOUND.code -> {
                        NotDataFound() //result?.getErrorsCollections()?: ""
                    }
                    HttpResponseCodeEnum.CONFLICT_2.code -> {
                        Conflict(result?.getErrorsCollections() ?: "")
                    }
                    HttpResponseCodeEnum.FORBIDDEN.code -> {
                        NoAuthorized(result?.getErrorsCollections() ?: "")
                    }
                    HttpResponseCodeEnum.INTERNAL_SERVER_ERROR.code -> {
                        InternalServerError(result?.getErrorsCollections() ?: "")
                    }
                    else -> NoInternetError()
                }
            } else if (it is UnknownHostException) {
                return NoInternetError()
            }
//            else if (it is NetworkConnectionInterceptor.NoConnectionException) {
//                return NoInternetError()
//            }
            else {
                return InternalServerError("")
            }
        }
    }


//// to add auto complete option in when
////    val <T> T.exhaustive: T
////        get() = this
//
//fun <T> FileUploadNetworkState<T>.successOr(fallback: T): T {
//    return (this as? FileUploadNetworkState.FileUploadNetworkStateSuccessfully<T>)?.fileResponse ?: fallback
//}
//
//fun <T> FileUploadNetworkState<T>.successOnly(): T {
//    return (this as? FileUploadNetworkState.FileUploadNetworkStateSuccessfully<T>)?.fileResponse!!
//}
//
//fun <T> FileUploadNetworkState<T>.successOrNull(): T? {
//    return (this as? FileUploadNetworkState.FileUploadNetworkStateSuccessfully<T>)?.fileResponse ?: null
//}
//
//fun callBackSuccess(onSuccessCaseFunc: (successResponse: T) -> Unit) {
//    if (succeeded)
//        onSuccessCaseFunc.invoke(successOr(this.successOnly()))
//}
}