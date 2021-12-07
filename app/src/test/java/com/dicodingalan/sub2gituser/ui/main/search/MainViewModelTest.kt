package com.dicodingalan.sub2gituser.ui.main.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicodingalan.sub2gituser.datamodel.ItemsItem
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.dicodingalan.sub2gituser.datamodel.ResponseSearchUser
import com.dicodingalan.sub2gituser.ui.main.dummy.DummyUsers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class MainViewModelTest {


    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var observer: Observer<ArrayList<ItemsItem>>

    @Mock
    private lateinit var responseSearchUser: ResponseSearchUser

    @Before
    fun beFore(){
        mainViewModel = MainViewModel()
    }


    @Test
    fun setSearchUser() {
        //manipulation data dummy
        val dataDummy = DummyUsers.getSearchUser()
        `when`(dataDummy.size).thenReturn(3)
        val user = MutableLiveData<ArrayList<ItemsItem>>()
        user.value = dataDummy

        `when`(responseSearchUser.items).thenReturn(user as ArrayList<ItemsItem>)
        val mUsers = mainViewModel.responseUserSearch.value

        assertNotNull(mUsers)
        assertEquals(3, mUsers?.size)


        mainViewModel.responseUserSearch.observeForever(observer)
        verify(observer).onChanged(dataDummy as ArrayList<ItemsItem>?)
    }
}
