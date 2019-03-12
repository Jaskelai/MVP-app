package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.ui.main.dialog.PaginationDialogView

@InjectViewState
class PaginationDialogPresenter(private val pagination: Pagination) : MvpPresenter<PaginationDialogView>() {

    fun setPagination(paginationValue: Int) {
        pagination.setCurrentPagination(paginationValue)
    }

    fun dismiss() {
        viewState.hideDialog()
    }
}
