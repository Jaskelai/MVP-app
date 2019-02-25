package com.github.kornilovmikhail.mvpandroidproject.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.network.model.Event
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.event_list_item.view.*

class EventAdapter(
    items: List<Event>,
    private val eventLambda: (Event) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventHolder>() {

    private var events: List<Event> = items

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): EventHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return EventHolder(view)
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(events[position].title)
        holder.itemView.setOnClickListener {
            eventLambda.invoke(events[position])
        }
    }

    class EventHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(eventName: String) {
            containerView.tv_list_item_name.text = eventName
        }
    }
}
