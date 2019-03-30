package com.github.kornilovmikhail.mvpandroidproject

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.repository.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.`DetailView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    @Mock
    lateinit var mockViewState: `DetailView$$State`

    @Mock
    lateinit var mockEventsRepo: EventsRepo

    @InjectMocks
    @Spy
    lateinit var detailPresenter: DetailPresenter

    @Before
    fun setup() {
        detailPresenter.setViewState(mockViewState)
    }

    @Test
    fun testGetEvent() {
        //Arrange
        val position = 0
        val mockEvent = mock(Event::class.java)
        val mockEvents = arrayListOf(mockEvent)
        Mockito.doReturn(Single.just(mockEvents)).`when`(mockEventsRepo).getEvents(position)
        //Act
        detailPresenter.getEvent(position)
        //Assert
        verify(mockViewState).showProgressBar()
        verify(mockViewState).displayEvent(mockEvents[position])
        verify(mockViewState).hideProgressBar()
    }

    @Test
    fun testGetEventWithError() {
        //Arrange
        val position = 0
        Mockito.doReturn(Single.error<List<Event>>(Throwable())).`when`(mockEventsRepo).getEvents(position)
        //Act
        detailPresenter.getEvent(position)
        //Assert
        verify(mockViewState).showProgressBar()
        verify(mockViewState).displayError()
        verify(mockViewState).hideProgressBar()
    }
}
