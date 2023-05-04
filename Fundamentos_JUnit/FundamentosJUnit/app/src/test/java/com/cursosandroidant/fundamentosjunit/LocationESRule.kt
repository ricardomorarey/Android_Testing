package com.cursosandroidant.fundamentosjunit

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/****
 * Project: Fundamentos JUnit
 * From: com.cursosandroidant.fundamentosjunit
 * Created by Alain Nicolás Tello on 14/12/21 at 9:54
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
class LocationESRule : TestRule {
    var assertions: Assertions? = null

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement(){
            override fun evaluate() {
                assertions = Assertions()
                assertions?.setLocation("ES")
                try {
                    base?.evaluate()
                } finally {
                    assertions = null
                }
            }
        }
    }
}