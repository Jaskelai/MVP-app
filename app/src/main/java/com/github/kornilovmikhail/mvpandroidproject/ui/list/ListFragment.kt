package com.github.kornilovmikhail.mvpandroidproject.ui.list

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kornilovmikhail.mvpandroidproject.App
import com.github.kornilovmikhail.mvpandroidproject.R
import com.github.kornilovmikhail.mvpandroidproject.data.entity.Event
import com.github.kornilovmikhail.mvpandroidproject.di.event.component.DaggerEventComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.EventModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.presenter.ListPresenter
import com.github.kornilovmikhail.mvpandroidproject.ui.SecondActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsFragment
import com.github.kornilovmikhail.mvpandroidproject.ui.list.adapter.EventAdapter
import com.github.kornilovmikhail.mvpandroidproject.ui.list.dialog.PaginationDialog
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : MvpAppCompatFragment(), ListView {
    @Inject
    @InjectPresenter
    lateinit var listPresenter: ListPresenter

    @ProvidePresenter
    fun getPresenter(): ListPresenter = listPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerEventComponent.builder()
            .appComponent(App.getAppComponents())
            .eventModule(EventModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        listPresenter.getEvents(0)
    }

    private fun setupViews() {
        rv_events.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        rv_events.addOnScrollListener(OnScrollListener(
            rv_events.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
        ) {
            listPresenter.getEvents(it)
        })
    }

    override fun displayEvents(listEvents: List<Event>) {
        if (rv_events.adapter == null) {
            rv_events.adapter = EventAdapter(listEvents) {
                listPresenter.eventClick(it)
            }
        }
        (rv_events.adapter as EventAdapter).submitList(listEvents)
    }

    override fun displaySuccess() {
        Toast.makeText(context, getString(R.string.server_events_success), Toast.LENGTH_SHORT).show()
    }

    override fun displayError() {
        Toast.makeText(context, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        main_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        main_progressBar.visibility = ProgressBar.INVISIBLE
    }

    override fun detachOnScrollListeners() = rv_events.clearOnScrollListeners()

    override fun navigateToMain(position: Int) {
        val args = Bundle()
        args.putInt("position", position)
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = args
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, detailsFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = context?.let { SecondActivity.newIntent(it) }
        when (item.itemId) {
            R.id.action_pagination -> createDialog()
            R.id.action_open_fragments -> startActivity(intent)
        }
        return true
    }

    private fun createDialog() {
        val dialog = PaginationDialog()
        dialog.show(activity?.supportFragmentManager, "custom")
    }
}
