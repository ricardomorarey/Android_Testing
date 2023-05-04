package com.cursosandroidant.fundamentosjunit

import org.junit.*
import org.junit.Assert.*

/****
 * Project: Fundamentos JUnit
 * From: com.cursosandroidant.fundamentosjunit
 * Created by Alain Nicolás Tello on 14/12/21 at 8:54
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 */
class AssertionsUsersTest {

    private lateinit var bot: User

    companion object{
        private lateinit var juan: User

        @BeforeClass @JvmStatic
        fun setupCommon(){
            juan = User("Juan", 18, true)
            println("BeforeClass")
        }

        @AfterClass @JvmStatic
        fun tearDownCommon(){
            juan = User()
            println("AfterClass")
        }
    }

    @Before
    fun setup(){
        bot = User("8bit", 1, false)
        println("Before")
    }

    @After
    fun tearDown(){
        bot = User()
        println("After")
    }

    @Test
    fun checkHumanTest() {
        val assertions = Assertions()
        assertFalse(assertions.checkHuman(bot))
        assertTrue(assertions.checkHuman(juan))
        println("checkHuman")
    }

    @Test
    fun checkNotNullUserTest(){
        assertNotNull(juan)
        println("checkNotNullUser")
    }

    @Test
    fun checkNotSameUsersTest(){
        assertNotSame(bot, juan)
        println("checkNotSameUsers")
    }

    @Test
    fun checkSameUsersTest(){
        val copyJuan = juan
        assertSame(copyJuan, juan)
        println("checkSameUsers")
    }
}