package com.srikanthgr.fallingwords.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.srikanthgr.fallingwords.FallingWordsApplication
import com.srikanthgr.fallingwords.injection.component.DaggerBaseFragmentComponent
import com.srikanthgr.fallingwords.utils.ProjectViewModelFactory


import javax.inject.Inject


/**
 * Base fragment which does all the initialization for the downstream fragments.
 */
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ProjectViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = activity!!.application as FallingWordsApplication
        DaggerBaseFragmentComponent.builder().applicationComponent(app.applicationComponent)
            .build().inject(this)
    }

}
