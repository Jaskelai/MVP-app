package com.github.kornilovmikhail.mvpandroidproject.di.component

import com.github.kornilovmikhail.mvpandroidproject.di.module.*
import com.github.kornilovmikhail.mvpandroidproject.di.scope.EventScope
import com.github.kornilovmikhail.mvpandroidproject.ui.detail.DetailsActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.links.LinksActivity
import com.github.kornilovmikhail.mvpandroidproject.ui.main.MainActivity
import dagger.Component

@EventScope
@Component(
    dependencies = [AppComponent::class],
    modules = [BaseModule::class, MainModule::class, DetailModule::class,
        LinksModule::class, DataNetModule::class, DataDBModule::class]
)
interface EventComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)

    fun inject(linksActivity: LinksActivity)
}
