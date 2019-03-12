package com.github.kornilovmikhail.mvpandroidproject

import android.content.SharedPreferences
import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.main.`MainView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mock


@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    lateinit var mockViewState: `MainView$$State`

    @Mock
    lateinit var mockEventsRepo: EventsRepo

    @Mock
    lateinit var mockPagination: Pagination

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
        verify(mockViewState).detachOnScrollListeners()
        verify(mockViewState).showProgressBar()
        verify(mockViewState).hideProgressBar()
    }

    @Test
    fun testGetEventsNotEmpty() {
        //Arrange
        val offset = 5
        val mockEvent = Mockito.mock(Event::class.java)
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
    fun testInitSharedPrefs() {
        //Arrange
        val mockSharedPreferences = mock(SharedPreferences::class.java)
        //Act
        mainPresenter.initSharedPrefs(mockSharedPreferences)
        //Assert
        verify(mockPagination).setSharedPrefs(mockSharedPreferences)
    }

    @Test
    fun testSaveSharedPrefs() {
        //Arrange
        //Act
        mainPresenter.setSharedPrefs(4)
        //Assert
        verify(mockPagination).setCurrentPagination(4)
    }

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