package com.cursosandroidant.auth

enum class AuthEvent {
    //sucsesfull
    USER_EXIST,
    //not sucsessfull
    NOT_USER_EXIST,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    EMPTY_FORM,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    INVALID_USER,
    NULL_EMAIL,
    NULL_PASSWOR,
    NULL_FORM,
    LENG_PASSWORD
}