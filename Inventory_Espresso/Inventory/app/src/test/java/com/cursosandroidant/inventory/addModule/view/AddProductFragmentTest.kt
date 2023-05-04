package com.cursosandroidant.inventory.addModule.view

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.runner.RunWith

/****
 * Project: Inventory
 * From: com.cursosandroidant.inventory.addModule.view
 * Created by Alain Nicolás Tello on 16/12/21 at 12:57
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 */
@RunWith(AndroidJUnit4::class)
class AddProductFragmentTest{
    @Test
    fun createDialog_notNullTest(){
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_Inventory)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{ fragment ->
            assertThat(fragment.dialog, `is`(notNullValue()))
        }
    }

    @Test
    fun cancelDialog_isNullTest(){
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_Inventory)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{ fragment ->
            (fragment.dialog as? AlertDialog)?.let {
                it.getButton(DialogInterface.BUTTON_NEGATIVE).performClick()
                assertThat(fragment.dialog, `is`(nullValue()))
            }
        }
    }
}