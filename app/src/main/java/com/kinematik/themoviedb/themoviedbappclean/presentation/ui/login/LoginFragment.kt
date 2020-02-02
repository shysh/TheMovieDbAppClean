package com.kinematik.themoviedb.app.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.presentation.di.Injectable
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_screen_to_home_screen)
        }

    }


}