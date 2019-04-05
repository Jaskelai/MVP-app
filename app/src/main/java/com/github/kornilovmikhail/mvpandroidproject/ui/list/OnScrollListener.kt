package com.github.kornilovmikhail.mvpandroidproject.ui.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val eventLambda: (Int) -> Unit
) : RecyclerView.OnScrollListener() {

    companion object {
        private var previousTotal = 0
        private var loading = true
        private val visibleThreshold = 0
        private var firstVisibleItem = 0
        private var visibleItemCount = 0
        private var totalItemCount = 0
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal =
                    totalItemCount

            }
        }

        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            eventLambda.invoke(totalItemCount)
            loading = true
        }
    }
}
