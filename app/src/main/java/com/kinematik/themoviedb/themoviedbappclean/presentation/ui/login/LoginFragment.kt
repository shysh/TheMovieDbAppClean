package com.kinematik.themoviedb.app.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.facebook.*
import com.facebook.login.LoginResult
import com.kinematik.themoviedb.themoviedbappclean.R
import com.kinematik.themoviedb.themoviedbappclean.di.Injectable
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*


class LoginFragment : Fragment(), Injectable {

    companion object {
        const val EMAIL = "email"
    }

    private lateinit var mCallbackManager: CallbackManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCallbackManager = CallbackManager.Factory.create()

        fb_login_btn.setReadPermissions(Arrays.asList("public_profile", "email"))
        fb_login_btn.fragment = this

        //https://stackoverflow.com/questions/29295987/android-facebook-4-0-sdk-how-to-get-email-date-of-birth-and-gender-of-user
        //https://stackoverflow.com/a/29559001
        //https://stackoverflow.com/a/37417599
        //https://stackoverflow.com/questions/47278858/ask-for-advice-how-should-i-intergrate-facebook-sdk-into-clean-architecture-in

        // Callback registration
        fb_login_btn.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {

                loginResult?.let { _loginResult ->
                    Profile.getCurrentProfile()?.let {
                        val firstName = it.firstName
                        val lastName = it.lastName
                        val picture = it.getProfilePictureUri(200, 200)
                    }

                    val request = GraphRequest.newMeRequest(
                        _loginResult.accessToken
                    ) { _object, _response ->
                        Log.v("LoginActivity", _response.toString())
                        // Application code
                        val email = _object.getString("email")
                        val birthday = _object.getString("birthday") // 01/31/1980 format
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,email,gender,birthday, ")
                    request.parameters = parameters
                    request.executeAsync()
                }

                //Navigation.findNavController(view).navigate(R.id.action_login_screen_to_home_screen)

                Log.DEBUG
            }

            override fun onCancel() { //
                Log.DEBUG// App code
            }

            override fun onError(exception: FacebookException) {
                Log.DEBUG// App code
            }
        })

        /*login_btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_screen_to_home_screen)
        }*/

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)

    }


}