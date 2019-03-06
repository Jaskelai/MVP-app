package com.github.kornilovmikhail.mvpandroidproject.ui.main.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.presenter.PaginationDialogPresenter
import kotlinx.android.synthetic.main.pagination_dialog.*

class PaginationDialog : MvpAppCompatDialogFragment(),PaginationDialogView {

    @InjectPresenter
    lateinit var paginationPresenter: PaginationDialogPresenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.label_dialog)
                .setView(activity?.layoutInflater?.inflate(R.layout.pagination_dialog, null))
                .setPositiveButton(
                    R.string.label_submit
                ) { dialog, _ ->
                    paginationPresenter.setPagination(edt_pagination.text.toString().toInt())
                    dialog.dismiss()
                }
                .setNegativeButton(
                    R.string.label_cancel
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
