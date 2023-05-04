package com.cursosandroidant.auth

import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItemInArray
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test

class AuthHamcrestTDDTest {

    @Test
    fun login_completeFrom_existUser_returnsSuccessEvent(){
        val result = userAuthentication("ant@gmail.com", "1234")
        assertThat(AuthEvent.USER_EXIST, `is`(result))
    }

    @Test
    fun login_completeForm_notExistUser_returnsFailEvent(){
        val result = userAuthentication("eee@gmail.com", "1234")
        assertThat(AuthEvent.NOT_USER_EXIST, `is`(result) )
    }

    @Test
    fun login_emptyEmail_returnsFailEvent(){
        val result = userAuthentication("", "1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is`(result))
    }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val result = userAuthentication("ant@gmail.com", "")
        assertThat(AuthEvent.EMPTY_PASSWORD, `is`(result))
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val result = userAuthentication("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is`(result))
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val result = userAuthentication("ant@gmail", "1234")
        assertThat(AuthEvent.INVALID_EMAIL, `is`(result))
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val result = userAuthentication("ant@gmail.com", "123e")
        assertThat(AuthEvent.INVALID_PASSWORD, `is`(result))
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val result = userAuthentication("ant@gmailcom", "123e")
        assertThat(AuthEvent.INVALID_USER, `is`(result))
    }

    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val result = userAuthentication(null, "123e")
        assertThat(AuthEvent.NULL_EMAIL, `is`(result))
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
            assertThat(AuthEvent.NULL_FORM, `is`(result))
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
        assertThat(AuthEvent.LENG_PASSWORD, `is`(passRegexResult))
    }

    @Test
    fun checkNames_diferentsUsers_match(){
        //assertThat("Maria", containsString("a"))
        assertThat("Maria", both(containsString("a")).and(containsString("i")))
    }

    @Test
    fun checkData_emailPassword_noMatch(){
        val email= "ant@gmail.com"
        val password= "1234"
        assertThat(email, not(`is`(password)))
    }

    @Test
    fun newEmail_exist_returnStrings(){
        val oldEmail = "ant@gmail.com"
        val newEmail = "ric@gmail.com"
        val emails = arrayOf(oldEmail, newEmail)
        assertThat(emails, hasItemInArray(newEmail))
    }

    @Test
    fun checkdomain_arrayEmails_returnStrings(){
        val nextEmail = "ant@other.com"
        val oldEmail = "ant@gmail.com"
        val newEmail = "ric@gmail.com"
        val emails = arrayListOf(oldEmail, newEmail, nextEmail)
        assertThat(emails, everyItem(endsWith("gmail.com")))
    }
}