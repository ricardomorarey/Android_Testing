package com.cursosandroidant.fundamentosjunit

import org.junit.*
import org.junit.Assert.*

/****
 * Project: Fundamentos JUnit
 * From: com.cursosandroidant.fundamentosjunit
 * Created by Alain Nicolás Tello on 14/12/21 at 9:42
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 */
class AssertionsUsersAdultTest {

    private lateinit var bot: User
    private lateinit var juan: User

    @get:Rule val locationESRule = LocationESRule()

    @Before
    fun setup(){
        bot = User("8bit", 1, false)
        juan = User("Juan", 18, true)
    }

    @After
    fun tearDown(){
        bot = User()
        juan = User()
    }

    @Test
    fun isAdultTest() {
        /*val assertions = Assertions()
        assertions.setLocation("ES")
        assertTrue(assertions.isAdult(juan))
        assertTrue(assertions.isAdult(bot))*/
        assertEquals(true, locationESRule.assertions?.isAdult(juan))
        assertEquals(true, locationESRule.assertions?.isAdult(bot))
    }
}