package com.github.kornilovmikhail.mvpandroidproject

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.presenter.ListPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.Screens
import com.github.kornilovmikhail.mvpandroidproject.ui.list.`ListView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import ru.terrakok.cicerone.Router


@RunWith(MockitoJUnitRunner::class)
class ListPresenterTest {

    @Mock
    lateinit var mockViewState: `ListView$$State`

    @Mock
    lateinit var mockEventsRepo: EventsRepo

    @Mock
    lateinit var router: Router

    @InjectMocks
    @Spy
    lateinit var listPresenter: ListPresenter

    @Before
    fun setUp() {
        listPresenter.setViewState(mockViewState)
    }

    @Test
    fun testGetEventsEmpty() {
        //Arrange
        val offset = 5
        doReturn(Single.just(ArrayList<Event>())).`when`(mockEventsRepo).getEvents(offset)
        //Act
        listPresenter.getEvents(offset)
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
        listPresenter.getEvents(offset)
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
        listPresenter.getEvents(offset)
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
        listPresenter.initSharedPreferences(mockSharedPreferences)
        //Assert
        verify(mockEventsRepo).setSharedPreferences(mockSharedPreferences)
    }

    @Test
    fun testSaveSharedPrefs() {
        //Arrange
        val expectedNumber = 4
        //Act
        listPresenter.setSharedPrefs(expectedNumber)
        //Assert
        verify(mockEventsRepo).setCurrentPagination(expectedNumber)
    }

    @Test
    fun testEventClick() {
        //Arrange
        val expectedPosition = 2
        val mockScreenProxy = mock(ProxyDetailScreen::class.java)
        val mockScreen = mock(Screens.DetailScreen::class.java)
        //Act
        listPresenter.eventClick(expectedPosition)
        //Assert
    }

    class ProxyDetailScreen {
        fun getDetailScreen(position: Int) = Screens.DetailScreen(position)
    }
}
