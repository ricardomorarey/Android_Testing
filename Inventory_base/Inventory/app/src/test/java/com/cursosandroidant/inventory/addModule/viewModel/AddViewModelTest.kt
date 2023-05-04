package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest{

    @get: Rule
    var instantExecuterRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest(){
        val viewModel =  AddViewModel()
        val product = Product(11, "RIck", 12,
        "https://thumb.pccomponentes.com/w-530-530/articles/1066/10665216/1277-microsoft-mando-inalambrico-xbox-series-one-pc-blanco.jpg",
        2.2, 100)
        val observer = Observer<Boolean>{}
        try {
            viewModel.getResult().observeForever(observer)
            viewModel.addProduct(product)
            val result = viewModel.getResult().value
            assertThat(result, `is`(true))
            assertThat(result, not(nullValue()))
        } finally {
            viewModel.getResult().removeObserver(observer)
        }

    }
}