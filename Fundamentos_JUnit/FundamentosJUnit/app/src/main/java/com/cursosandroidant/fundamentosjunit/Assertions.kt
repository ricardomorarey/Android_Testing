package com.cursosandroidant.fundamentosjunit

/****
 * Project: Fundamentos JUnit
 * From: com.cursosandroidant.fundamentosjunit
 * Created by Alain Nicolás Tello on 13/12/21 at 14:02
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
class Assertions {
    private val user = User("Alain", 31)
    private var location = "US"

    fun setLocation(location: String){
        this.location = location
    }

    fun getLuckyNumbers(): Array<Int>{
        return arrayOf(21, 117)
    }

    fun getName(): String{
        return user.name
    }

    fun checkHuman(user: User): Boolean{
        return user.isHuman
    }

    fun checkHuman(user: User? = null): Boolean?{
        if (user == null) return null
        return user.isHuman
    }

    fun isAdult(user: User): Boolean{
        if (!user.isHuman) return true

        return if (location == "US") user.age >= 21
        else user.age >= 18
    }
}