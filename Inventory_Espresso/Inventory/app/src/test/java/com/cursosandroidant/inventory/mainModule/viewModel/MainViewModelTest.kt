package com.cursosandroidant.inventory.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/****
 * Project: Inventory
 * From: com.cursosandroidant.inventory.mainModule.viewModel
 * Created by Alain Nicolás Tello on 16/12/21 at 12:09
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 */
//@Config(sdk = [21, 22, 30])
//@Config(maxSdk = 30)
@RunWith(AndroidJUnit4::class)
class MainViewModelTest{
    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkWelcomeTest(){
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}

        try {
            mainViewModel.isWelcome().observeForever(observer)
            val result = mainViewModel.isWelcome().value
            assertThat(result, not(nullValue()))
            assertThat(result, `is`(true))
        } finally {
            mainViewModel.isWelcome().removeObserver(observer)
        }
    }
}