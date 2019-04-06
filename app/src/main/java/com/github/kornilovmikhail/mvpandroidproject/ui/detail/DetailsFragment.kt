package com.github.kornilovmikhail.mvpandroidproject.ui.detail

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
import com.github.kornilovmikhail.mvpandroidproject.presenter.DetailPresenter
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : MvpAppCompatFragment(), DetailView {
    @Inject
    @InjectPresenter
    lateinit var detailPresenter: DetailPresenter

    private var position = 0

    @ProvidePresenter
    fun getPresenter(): DetailPresenter = detailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerEventComponent.builder()
            .appComponent(App.getAppComponents())
            .eventModule(EventModule())
            .presenterModule(PresenterModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        position = arguments?.getInt(EXTRA_POSITION) as Int
        setHasOptionsMenu(true)
        detailPresenter.getEvent(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_details, container, false)

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_open_links -> detailPresenter.onIconClicked(position)
        }
        return true
    }

    override fun displayEvent(event: Event) {
        tv_title_details_activity.text = event.title
        tv_details_details_activity.text = event.details
        tv_event_date_details_activity.text = event.eventDate.toString()
    }

    override fun displayError() {
        Toast.makeText(context, getString(R.string.server_events_error), Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        details_progressBar.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgressBar() {
        details_progressBar.visibility = ProgressBar.INVISIBLE
    }

    companion object {
        private const val EXTRA_POSITION = "POSITION"

        fun getInstance(position: Int): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val args = Bundle()
            args.putInt(EXTRA_POSITION, position)
            detailsFragment.arguments = args
            return detailsFragment
        }
    }
}
