package com.github.kornilovmikhail.mvpandroidproject

import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Links
import com.github.kornilovmikhail.mvpandroidproject.data.repo.EventsRepo
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.links.`LinksView$$State`
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LinksPresenterTest {

    @Mock
    lateinit var mockViewState: `LinksView$$State`

    @Mock
    lateinit var mockEventsRepo: EventsRepo

    @InjectMocks
    @Spy
    lateinit var linksPresenter: LinksPresenter

    @Before
    fun setup() {
        linksPresenter.setViewState(mockViewState)
    }

    @Test
    fun testGetEvent() {
        //Arrange
        val position = 0
        val mockEvent = mock(Event::class.java)
        val mockEvents = arrayListOf(mockEvent)
        Mockito.doReturn(Single.just(mockEvents)).`when`(mockEventsRepo).getEvents(position)
        //Act
        linksPresenter.getLinks(position)
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
        linksPresenter.getLinks(position)
        //Assert
        verify(mockViewState).showProgressBar()
        verify(mockViewState).displayError()
        verify(mockViewState).hideProgressBar()
    }
}
