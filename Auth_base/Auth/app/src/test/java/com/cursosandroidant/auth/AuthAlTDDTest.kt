package com.cursosandroidant.auth

import junit.framework.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class AuthAlTDDTest {

    private var email: String? = null
    private var pass: String? = null

    @Before
    fun setup(){
        email = "ant@gmail.com"
        pass = "1234"
    }

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.USER_EXIST, result)
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.NOT_USER_EXIST, result)
    }

    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.EMPTY_EMAIL, result)
    }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.EMPTY_PASSWORD, result)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.EMPTY_FORM, result)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.INVALID_EMAIL, result)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.INVALID_PASSWORD, result)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.INVALID_USER, result)
    }

    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val result = userAuthentication(email, pass)
        assertEquals(AuthEvent.NULL_EMAIL, result)
    }

    @Test
    fun login_nullPassword_returnsException(){
        assertThrows(AuthException::class.java){
            println(userAuthentication(email, pass))
        }
    }

    @Test
    fun login_nullForm_returnsException(){
        try {
            val result = userAuthentication(email, pass)
            assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val passRegexResult = userAuthentication(email, pass)
        assertEquals(AuthEvent.LENG_PASSWORD, passRegexResult)
    }

}