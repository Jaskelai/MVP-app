package com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.presenter.MainPresenter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.event_list_item.view.*
import javax.inject.Inject

class EventAdapter @Inject constructor(
    private val mainPresenter: MainPresenter
) : ListAdapter<Event, EventAdapter.EventHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): EventHolder {
        return EventHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.event_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int = mainPresenter.getListItemsCount()

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        mainPresenter.onBindListOnPosition(position, holder)
        holder.itemView.setOnClickListener {
            mainPresenter.eventClick(position) }
    }

    override fun submitList(list: List<Event>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean = oldItem == newItem

    }

    class EventHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer, EventListView {

        override fun setText(eventName: String) {
            containerView.tv_list_item_name.text = eventName
        }
    }
}
