package com.cursosandroidant.inventory.mainModule.view

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

/****
 * Project: Inventory
 * From: com.cursosandroidant.inventory.mainModule.view
 * Created by Alain Nicolás Tello on 11/01/22 at 18:13
 * All rights reserved 2022.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 ***/
fun clickInChild(id: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.withId(id)

        override fun getDescription(): String = "Child in ViewHolder."

        override fun perform(uiController: UiController?, view: View) {
            (view.findViewById(id) as View).performClick()
        }
    }
}