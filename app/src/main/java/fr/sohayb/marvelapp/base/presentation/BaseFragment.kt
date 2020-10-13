package fr.sohayb.marvelapp.base.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import fr.sohayb.marvelapp.app.domain.AppState
import fr.sohayb.marvelapp.app.presentation.AppViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import androidx.lifecycle.observe

abstract class BaseFragment: Fragment() {

    protected val viewModel: AppViewModel by inject { parametersOf(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    protected abstract fun render(appState: AppState)

    protected fun showGeneralError(error: Error){

    }
}