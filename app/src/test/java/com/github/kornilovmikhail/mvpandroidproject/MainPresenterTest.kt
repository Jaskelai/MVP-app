package com.github.kornilovmikhail.mvpandroidproject

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.list.`MainView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    lateinit var mockViewState: `MainView$$State`

    @Mock
    lateinit var mockEventsRepo: EventsRepo

    @InjectMocks
    @Spy
    lateinit var mainPresenter: MainPresenter

    @Before
    fun setUp() {
        mainPresenter.setViewState(mockViewState)
    }

    @Test
    fun testGetEventsEmpty() {
        //Arrange
        val offset = 5
        doReturn(Single.just(ArrayList<Event>())).`when`(mockEventsRepo).getEvents(offset)
        //Act
        mainPresenter.getEvents(offset)
        //Assert
        verify(mockViewState).showProgressBar()
        verify(mockViewState).detachOnScrollListeners()
        verify(mockViewState).hideProgressBar()
    }

    @Test
    fun testGetEventsNotEmpty() {
        //Arrange
        val offset = 5
        val mockEvent = mock(Event::class.java)
        val events = arrayListOf(mockEvent)
        doReturn(Single.just(events)).`when`(mockEventsRepo).getEvents(offset)
        //Act
        mainPresenter.getEvents(offset)
        //Assert
        verify(mockViewState).showProgressBar()
        verify(mockEventsRepo).cacheEvents(events)
        verify(mockViewState).displayEvents(events)
        verify(mockViewState).displaySuccess()
        verify(mockViewState).hideProgressBar()
    }

    @Test
    fun testGetEventsWithError() {
        //Arrange
        val offset = 5
        doReturn(Single.error<List<Event>>(Throwable())).`when`(mockEventsRepo).getEvents(offset)
        //Act
        mainPresenter.getEvents(offset)
        //Assert
        verify(mockViewState).showProgressBar()
        verify(mockViewState).displayError()
        verify(mockViewState).hideProgressBar()
    }


    @Test
    fun testInitSharedPreferences() {
        //Arrange
        val mockSharedPreferences = mock(SharedPreferences::class.java)
        //Act
        mainPresenter.initSharedPreferences(mockSharedPreferences)
        //Assert
        verify(mockEventsRepo).setSharedPreferences(mockSharedPreferences)
    }

    @Test
    fun testSaveSharedPrefs() {
        //Arrange
        val expectedNumber = 4
        //Act
        mainPresenter.setSharedPrefs(expectedNumber)
        //Assert
        verify(mockEventsRepo).setCurrentPagination(expectedNumber)
    }

    @Test
    fun testEventClick() {
        //Arrange
        val expectedPosition = 2
        //Act
        mainPresenter.eventClick(expectedPosition)
        //Assert
        verify(mockViewState).navigateToMain(expectedPosition)
    }
}
