package com.github.kornilovmikhail.mvpandroidproject

import android.content.Context
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsDBRepo
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.main.`MainView$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mock
import org.mockito.internal.util.reflection.FieldSetter


@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    lateinit var mockViewState: `MainView$$State`

    @InjectMocks
    @Spy
    lateinit var mainPresenter: MainPresenter

    @Mock
    lateinit var context: Context

    @Before
    fun setUp() {
        mainPresenter.setViewState(mockViewState)
//        this.sharedPreferences = Mockito.mock(SharedPreferences::class.java)
//        val context = Mockito.mock(Context::class.java)
//        doReturn(sharedPreferences).`when`(context.getSharedPreferences(anyString(),anyInt()))
    }

    @Test
    fun testGetEventsZero() {
        //Arrange
        val offset = 0
        val mockEventsDBRepo = mock(EventsDBRepo::class.java)
        FieldSetter.setField(mockEventsDBRepo, mockEventsDBRepo.javaClass.getDeclaredField("context"), context)
        //Act
        mainPresenter.getEvents(offset)
        //Assert
//        verify(mockEventsDBRepo).getEvents()
    }

    @Test
    fun testGetEventsMoreZero() {
        //Arrange
        val offset = 5
        //Act
        mainPresenter.getEvents(offset)
        //Assert
        verify(mainPresenter).getEvents(offset)
    }


//    @Test
//    fun testInitSharedPrefs() {
//        //Arrange
//        val mockPagination = mock(PaginationWrapper::class.java)
//        //Act
//        mainPresenter.initSharedPrefs(sharedPreferences)
//        //Assert
//        verify(mockPagination).setSharedPrefs(sharedPreferences)

//    @Test
//    fun testSaveSharedPrefs() {
//        //Arrange
//        val mockPagination = mock(Pagination::class.java)
//        //Act
//        mainPresenter.setSharedPrefs(4)
//        //Assert
//        verify(mockPagination).setCurrentPagination(4)
//    }

    @Test
    fun testEventClick() {
        //Arrange
        val position = 2
        //Act
        mainPresenter.eventClick(position)
        //Assert
        verify(mockViewState).navigateToMain(position)
    }

}