package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/****
 * Project: Inventory
 * From: com.cursosandroidant.inventory.addModule.viewModel
 * Created by Alain Nicolás Tello on 16/12/21 at 11:04
 * All rights reserved 2021.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * Web: www.alainnicolastello.com
 */
@RunWith(AndroidJUnit4::class)
class AddViewModelTest{
    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest(){
        val addViewModel = AddViewModel()
        val product = Product(117, "Xbox", 20, "https://upload.wikimedia.org/" +
                "wikipedia/commons/thumb/5/54/Xbox_Series_S_with_controller.jpg/480px-Xbox_Series_S_with_controller.jpg",
            4.8, 56)

        val observer = Observer<Boolean>{}
        try {
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value
            assertThat(result, `is`(true))
            //assertThat(result, not(nullValue()))// opt
        } finally {
            addViewModel.getResult().removeObserver(observer)
        }
    }
}