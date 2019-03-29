package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.PaginationPreferences
import com.github.kornilovmikhail.mvpandroidproject.ui.list.dialog.PaginationDialogView

@InjectViewState
class PaginationDialogPresenter(private val paginationPreferences: PaginationPreferences) :
    MvpPresenter<PaginationDialogView>() {

    fun setPagination(paginationValue: Int) {
        paginationPreferences.setCurrentPagination(paginationValue)
    }

    fun dismiss() {
        viewState.hideDialog()
    }
}
