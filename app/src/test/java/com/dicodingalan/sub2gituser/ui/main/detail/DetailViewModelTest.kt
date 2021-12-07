package com.dicodingalan.sub2gituser.ui.main.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicodingalan.sub2gituser.database.User
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser
import com.dicodingalan.sub2gituser.repository.UserRepository
import com.dicodingalan.sub2gituser.ui.main.dummy.DummyUsers
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel
    private val dataUser = DummyUsers.getDetailUsers()[0]

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: UserRepository

    @Mock
    private lateinit var observer: Observer<User>

    @Before
    fun before() {
        application = mock(Application::class.java)
        detailViewModel = DetailViewModel(application)
    }


}