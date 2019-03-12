package com.github.kornilovmikhail.mvpandroidproject

import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.presenter.PaginationDialogPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.main.dialog.`PaginationDialogView$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PaginationDialogPresenterTest {

    @Mock
    lateinit var mockPagination: Pagination

    @Mock
    lateinit var mockViewState: `PaginationDialogView$$State`

    @InjectMocks
    @Spy
    lateinit var paginationDialogPresenter: PaginationDialogPresenter

    @Before
    fun setup() {
        paginationDialogPresenter.setViewState(mockViewState)
    }

    @Test
    fun testFunPagination() {
        //Arrange
        val pagination = 2
        //Act
        paginationDialogPresenter.setPagination(pagination)
        //Assert
        Mockito.verify(mockPagination).setCurrentPagination(pagination)
    }

    @Test
    fun dismiss() {
        //Arrange
        //Act
        paginationDialogPresenter.dismiss()
        //Assert
        Mockito.verify(mockViewState).hideDialog()
    }
}