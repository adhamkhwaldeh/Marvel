package com.aljawad.sons.mainlibrary.states

enum class HttpResponseCodeEnum(var code: Int) {
    NOT_ALLOWED_ADMIN(203), UN_AUTHORIZED(401),
    NO_DATA_FOUND(404), FORBIDDEN(403),
    CONFLICT(409), CONFLICT_2(422),
    INTERNAL_SERVER_ERROR(500), BAD_REQUEST(400)
}