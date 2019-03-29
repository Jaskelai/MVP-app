package com.github.kornilovmikhail.mvpandroidproject

import com.github.kornilovmikhail.mvpandroidproject.data.PaginationPreferences
import com.github.kornilovmikhail.mvpandroidproject.presenter.PaginationDialogPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.list.dialog.`PaginationDialogView$$State`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PaginationPreferencesDialogPresenterTest {

    @Mock
    lateinit var mockViewState: `PaginationDialogView$$State`

    @Mock
    lateinit var mockPaginationPreferences: PaginationPreferences

    @InjectMocks
    @Spy
    lateinit var paginationDialogPresenter: PaginationDialogPresenter

    @Before
    fun setup() {
        paginationDialogPresenter.setViewState(mockViewState)
    }

    @Test
    fun testSetPagination() {
        //Arrange
        val expectedPagination = 2
        //Act
        paginationDialogPresenter.setPagination(expectedPagination)
        //Assert
        Mockito.verify(mockPaginationPreferences).setCurrentPagination(expectedPagination)
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
