package com.github.kornilovmikhail.mvpandroidproject.di.event.component

import com.github.kornilovmikhail.mvpandroidproject.di.app.component.AppComponent
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.EventModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.module.PresenterModule
import com.github.kornilovmikhail.mvpandroidproject.di.event.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.MainActivity
import dagger.Component

@EventScope
@Component(
    dependencies = [AppComponent::class],
    modules = [EventModule::class, PresenterModule::class]
)
interface EventComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)

    fun inject(linksActivity: LinksActivity)
}
