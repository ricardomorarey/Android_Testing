package com.cursosandroidant.fundamentosjunit

import org.junit.Assert.*
import org.junit.Test
import kotlin.random.Random

/****
 * Project: Fundamentos JUnit
 * From: com.cursosandroidant.fundamentosjunit
 * Created by Alain Nicolás Tello on 13/12/21 at 14:59
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 */
class AssertionsTest{
    @Test
    fun getArrayTest(){
        val assertions = Assertions()
        val array = arrayOf(21, 117)// valor esperado
        //val array = arrayOf(21, 11)// valor esperado
        assertArrayEquals("mensaje personalizado de error en testing", array, assertions.getLuckyNumbers())
    }

    @Test
    fun getNameTest(){
        val assertions = Assertions()
        val name = "Alain"
        val otherName = "Max"
        assertEquals(name, assertions.getName())
        assertNotEquals(otherName, assertions.getName())
    }

    @Test
    fun checkHumanTest(){
        val assertions = Assertions()
        val bot = User("8bit", 1, false)
        val juan = User("Juan", 18, true)
        assertFalse(assertions.checkHuman(bot))
        assertTrue(assertions.checkHuman(juan))
    }

    @Test
    fun checkNullUserTest(){
        val user = null
        assertNull(user)
        val assertions = Assertions()
        assertNull(assertions.checkHuman(user))
    }
    @Test
    fun checkNotNullUserTest(){
        val juan = User("Juan", 18, true)
        assertNotNull(juan)
    }

    @Test
    fun checkNotSameUsersTest(){
        val bot = User("8bit", 1, false)
        val juan = User("Juan", 18, true)
        assertNotSame(bot, juan)
    }

    @Test
    fun checkSameUsersTest(){
        val bot = User("Juan", 18, true)
        val juan = User("Juan", 18, true)
        val copyJuan = juan
        assertSame(copyJuan, juan)
    }

    @Test(timeout = 1_000)
    fun getCitiesTest(){
        val cities = arrayOf("México", "Perú", "Argentina")
        Thread.sleep(Random.nextLong(200, 1_100))
        //Thread.sleep(Random.nextLong(950, 1_050))
        assertEquals(3, cities.size)
    }
}