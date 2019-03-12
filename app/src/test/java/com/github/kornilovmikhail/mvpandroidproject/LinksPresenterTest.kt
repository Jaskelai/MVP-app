package com.github.kornilovmikhail.mvpandroidproject

import com.github.kornilovmikhail.mvpandroidproject.data.repo.TempEvents
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Links
import com.github.kornilovmikhail.mvpandroidproject.presenter.LinksPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.links.`LinksView$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.ArrayList

@RunWith(MockitoJUnitRunner::class)
class LinksPresenterTest {

    @Mock
    lateinit var mockViewState: `LinksView$$State`

    @InjectMocks
    @Spy
    lateinit var linksPresenter: LinksPresenter

    @Before
    fun setup() {
        linksPresenter.setViewState(mockViewState)
    }

    @Test
    fun testGetLinks() {
        //Arrange
        val position = 0
        val listEvents = ArrayList<Event>()
        val event = Event(0, "", 0, 0, "", Links(0, "", "", ""))
        listEvents.add(event)
        TempEvents.events = listEvents
        //Act
        linksPresenter.getLinks(position)
        //Assert
        verify(mockViewState).setText("", "", "")
    }
}
