package fr.sohayb.marvelapp.main.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import fr.sohayb.marvelapp.R
import fr.sohayb.marvelapp.app.domain.AppState
import fr.sohayb.marvelapp.base.presentation.BaseFragment
import fr.sohayb.marvelapp.main.domain.MainAction
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatch(MainAction.GetAllCharacters)
    }


    override fun render(appState: AppState) {
        appState.mainState.let {

        }
    }

    private fun onClickResultEvent() {

    }

}