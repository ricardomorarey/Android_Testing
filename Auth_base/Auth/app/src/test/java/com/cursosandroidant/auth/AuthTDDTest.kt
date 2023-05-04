package com.cursosandroidant.auth

import junit.framework.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test

class AuthTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val result = userAuthentication("ant@gmail.com", "1234")
        assertEquals(AuthEvent.USER_EXIST, result)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val result = userAuthentication("eee@gmail.com", "1234")
        assertEquals(AuthEvent.NOT_USER_EXIST, result)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val result = userAuthentication("", "1234")
        assertEquals(AuthEvent.EMPTY_EMAIL, result)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val result = userAuthentication("ant@gmail.com", "")
        assertEquals(AuthEvent.EMPTY_PASSWORD, result)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val result = userAuthentication("", "")
        assertEquals(AuthEvent.EMPTY_FORM, result)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val result = userAuthentication("ant@gmail", "1234")
        assertEquals(AuthEvent.INVALID_EMAIL, result)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val result = userAuthentication("ant@gmail.com", "123e")
        assertEquals(AuthEvent.INVALID_PASSWORD, result)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val result = userAuthentication("ant@gmailcom", "123e")
        assertEquals(AuthEvent.INVALID_USER, result)
    }

    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val result = userAuthentication(null, "123e")
        assertEquals(AuthEvent.NULL_EMAIL, result)
    }

    @Test
    fun login_nullPassword_returnsException(){
        assertThrows(AuthException::class.java){
            println(userAuthentication("ant@gmail.com", null))
        }
    }

    @Test
    fun login_nullForm_returnsException(){
        try {
            val result = userAuthentication(null, null)
            assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Ignore("We dont´t have the customer´s data yet")
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val passRegexResult = userAuthentication("ant@gmail.com", "1234")
        assertEquals(AuthEvent.LENG_PASSWORD, passRegexResult)
    }

}