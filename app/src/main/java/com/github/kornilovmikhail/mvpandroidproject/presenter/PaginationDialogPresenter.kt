package com.github.kornilovmikhail.mvpandroidproject.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.github.kornilovmikhail.mvpandroidproject.data.Pagination
import com.github.kornilovmikhail.mvpandroidproject.ui.main.dialog.PaginationDialogView

@InjectViewState
class PaginationDialogPresenter : MvpPresenter<PaginationDialogView>() {
    fun setPagination(pagination: Int) {
        Pagination.setCurrentPagination(pagination)
    }
}
