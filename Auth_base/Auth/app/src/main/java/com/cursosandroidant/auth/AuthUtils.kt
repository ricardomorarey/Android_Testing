package com.cursosandroidant.auth

/****
 * Project: Auth
 * From: com.cursosandroidant.auth
 * Created by Alain Nicolás Tello on 14/12/21 at 12:05
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/

fun userAuthentication(email: String?, password: String?): AuthEvent {
    if (email == null && password == null) throw AuthException(AuthEvent.NULL_FORM)
    if (email == null) throw AuthException(AuthEvent.NULL_EMAIL)
    if (password == null) throw AuthException(AuthEvent.NULL_PASSWOR)
    if (email.isEmpty() && password.isEmpty()) return AuthEvent.EMPTY_FORM
    if (email.isEmpty()) return AuthEvent.EMPTY_EMAIL
    if (password.isEmpty()) return AuthEvent.EMPTY_PASSWORD
    if (isPassWordValid(password)) return AuthEvent.LENG_PASSWORD
    val passwordnumeric = password.toIntOrNull()
    if (!isEmailValid(email) && passwordnumeric == null ) return AuthEvent.INVALID_USER
    if (!isEmailValid(email)) return AuthEvent.INVALID_EMAIL
    if (passwordnumeric == null) return AuthEvent.INVALID_PASSWORD
    return if (email == "ant@gmail.com" && password == "1234") AuthEvent.USER_EXIST
    else AuthEvent.NOT_USER_EXIST
}

fun isEmailValid(email: String): Boolean {
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return EMAIL_REGEX.toRegex().matches(email);
}

fun isPassWordValid(password: String): Boolean {
    return password.length != 4
}