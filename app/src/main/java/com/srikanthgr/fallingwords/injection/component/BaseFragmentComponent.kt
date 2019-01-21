package com.srikanthgr.fallingwords.injection.component


import com.srikanthgr.fallingwords.injection.FragmentScoped
import com.srikanthgr.fallingwords.presentation.base.BaseFragment
import dagger.Component

/**
 * Base application component.
 */
@FragmentScoped
@Component(dependencies = [ApplicationComponent::class])
interface BaseFragmentComponent {
    fun inject(baseFragment: BaseFragment)
}
