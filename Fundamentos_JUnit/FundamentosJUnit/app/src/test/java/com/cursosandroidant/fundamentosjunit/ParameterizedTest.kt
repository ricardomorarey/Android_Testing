package com.cursosandroidant.fundamentosjunit

import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/****
 * Project: Fundamentos JUnit
 * From: com.cursosandroidant.fundamentosjunit
 * Created by Alain Nicolás Tello on 14/12/21 at 11:19
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/

@RunWith(value = Parameterized::class)
class ParameterizedTest(var currentValue: Boolean, var currentUser: User) {

    @get:Rule
    val locationESRule = LocationESRule()

    companion object{
        var assertions: Assertions? = null

        @BeforeClass @JvmStatic
        fun setupCommon(){
            assertions = Assertions()
        }
        @AfterClass
        @JvmStatic
        fun tearDownCommon(){
            assertions = null
        }

        @Parameterized.Parameters @JvmStatic
        /*fun getUsersUS() = arrayOf(
            arrayOf(false, User("Pedro", 12)),
            arrayOf(true, User("Clara", 34)),
            arrayOf(true, User("Bot21", 4, false)),
            arrayOf(false, User("Alex", 18)))*/
        fun getUsersES() = arrayOf(
            arrayOf(true, User("Pedro", 19)),
            arrayOf(false, User("Clara", 14)),
            arrayOf(true, User("Bot21", 4, false)),
            arrayOf(true, User("Alex", 18)))
    }

    @Test
    fun isAdultTest() {
        //assertEquals(currentValue, assertions?.isAdult(currentUser))
        assertEquals(currentValue, locationESRule.assertions?.isAdult(currentUser))
    }
}