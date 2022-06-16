package com.loud9ja.loud9ja.ui.authentication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.loud9ja.loud9ja.R
import com.loud9ja.loud9ja.data.Platform
import com.loud9ja.loud9ja.databinding.ActivityLoginBinding
import com.loud9ja.loud9ja.domain.network.api.request.LoginRequest
import com.loud9ja.loud9ja.ui.home.HomeActivity
import com.loud9ja.loud9ja.utils.AuthPreference
import com.loud9ja.loud9ja.utils.DataState
import com.loud9ja.loud9ja.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager
    private lateinit var mAuth: FirebaseAuth
    private val TAG = "LoginActivity"
    val RC_SIGN_IN: Int = 123
    lateinit var signInClient: GoogleSignInClient
    lateinit var signInOptions: GoogleSignInOptions
    val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    getUserProfile(loginResult?.accessToken, loginResult?.accessToken?.userId)
                    handleFacebookAccessToken(loginResult?.accessToken)
                }

                override fun onCancel() {
                    Toast.makeText(this@LoginActivity, "Login Cancelled", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: FacebookException) {
                    Toast.makeText(this@LoginActivity, exception.message, Toast.LENGTH_LONG).show()
                }
            })

        initiazeUI()
        setInGoogleLogin()
        binding.fabFacebook.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile", "email"))
        }
        binding.cirLoginButton.setOnClickListener {
            if (binding.editTextEmail.text.toString().isEmpty()) {
                showMessageToast("Please input email address")
            }
            if (binding.editTextPassword.text.toString()
                    .isEmpty() || binding.editTextPassword.text.toString().length < 6
            ) {
                showMessageToast("Password should at least be 6 characters")
            }
            val loginRequest = LoginRequest(
                binding.editTextEmail.text.toString().trim(),
                binding.editTextPassword.text.toString().trim(),
                Platform.LOUD
            )
            authViewModel.loginUser(loginRequest)
            //authViewModel.login(loginRequest, this)
            observeLoginRequest()

        }


    }

    private fun observeLoginRequest() {
        binding.loadingBar.visibility = View.VISIBLE
        authViewModel.loginResponse.observe(this) { data ->
            when (data) {
                is DataState.Success -> {
                    AuthPreference(this).saveData(
                        data.data.data.name,
                        data.data.data.email,
                        data.data.token
                    )
                    PreferenceHelper(this).saveUser(
                        data.data.data.name,
                        data.data.data.email,
                        data.data.token
                    )
                    onLoginSuccess()
                    Log.d(TAG, "observeLoginRequest: $data")
                }
                is DataState.Loading -> {
                    binding.loadingBar.visibility = View.VISIBLE
                }
                is DataState.Error -> {
                    binding.loadingBar.visibility = View.GONE
                    Log.d(TAG, "observeLoginRequest: data.exception")
                }
            }
        }
    }

    private fun showMessageToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onLoginClick(view: View?) {
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.apply {
            this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            this.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        finish()
    }

    private fun initiazeUI() {
        binding.fabGoogle.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val loginIntent: Intent = signInClient.signInIntent
        startActivityForResult(loginIntent, RC_SIGN_IN)
    }

    private fun googleFirebaseAuth(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                onLoginSuccess()
            } else {
                Toast.makeText(
                    this,
                    "Google sign In failed ${it.exception?.stackTrace}",
                    Toast.LENGTH_LONG
                )
                    .show()
                Log.e(TAG, "googleFirebaseAuth: ${it.exception?.localizedMessage}")
            }
        }
    }

    @SuppressLint("LongLogTag")
    fun getUserProfile(token: AccessToken?, userId: String?) {
        val parameters = Bundle()
        parameters.putString(
            "fields",
            "id, first_name, middle_name, last_name, name, picture, email"
        )
        GraphRequest(
            token,
            "/$userId/",
            parameters,
            HttpMethod.GET
        ) { response ->
            val jsonObject = response.jsonObject

            // Facebook Access Token
            // You can see Access Token only in Debug mode.
            // You can't see it in Logcat using Log.d, Facebook did that to avoid leaking user's access token.
            if (BuildConfig.DEBUG) {
                FacebookSdk.setIsDebugEnabled(true)
                FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS)
            }

            // Facebook Id
            if (jsonObject.has("id")) {
                val facebookId = jsonObject.getString("id")
                Log.i("Facebook Id: ", facebookId.toString())
            } else {
                Log.i("Facebook Id: ", "Not exists")
            }


            // Facebook First Name
            if (jsonObject.has("first_name")) {
                val facebookFirstName = jsonObject.getString("first_name")
                Log.i("Facebook First Name: ", facebookFirstName)
            } else {
                Log.i("Facebook First Name: ", "Not exists")
            }


            // Facebook Middle Name
            if (jsonObject.has("middle_name")) {
                val facebookMiddleName = jsonObject.getString("middle_name")
                Log.i("Facebook Middle Name: ", facebookMiddleName)
            } else {
                Log.i("Facebook Middle Name: ", "Not exists")
            }


            // Facebook Last Name
            if (jsonObject.has("last_name")) {
                val facebookLastName = jsonObject.getString("last_name")
                Log.i("Facebook Last Name: ", facebookLastName)
            } else {
                Log.i("Facebook Last Name: ", "Not exists")
            }


            // Facebook Name
            if (jsonObject.has("name")) {
                val facebookName = jsonObject.getString("name")
                Log.i("Facebook Name: ", facebookName)
                Toast.makeText(this, facebookName, Toast.LENGTH_LONG).show()
            } else {
                Log.i("Facebook Name: ", "Not exists")
            }


            // Facebook Profile Pic URL
            if (jsonObject.has("picture")) {
                val facebookPictureObject = jsonObject.getJSONObject("picture")
                if (facebookPictureObject.has("data")) {
                    val facebookDataObject = facebookPictureObject.getJSONObject("data")
                    if (facebookDataObject.has("url")) {
                        val facebookProfilePicURL = facebookDataObject.getString("url")
                        Log.i("Facebook Profile Pic URL: ", facebookProfilePicURL)
                    }
                }
            } else {
                Log.i("Facebook Profile Pic URL: ", "Not exists")
            }

            // Facebook Email
            if (jsonObject.has("email")) {
                val facebookEmail = jsonObject.getString("email")
                Log.i("Facebook Email: ", facebookEmail)
            } else {
                Log.i("Facebook Email: ", "Not exists")
            }
        }.executeAsync()
    }

    private fun handleFacebookAccessToken(token: AccessToken?) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token!!.token)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user: FirebaseUser = mAuth.currentUser!!
                    Log.i(TAG, "onComplete: login completed with user: $user")
                    onLoginSuccess()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        this@LoginActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    googleFirebaseAuth(account)
                }
            } catch (e: ApiException) {
                Toast.makeText(
                    this,
                    "Google sign In failed ${e.stackTrace}",
                    Toast.LENGTH_LONG
                ).show()
                Log.e(TAG, "onActivityResult: ", e)
            }
        }
    }

    private fun setInGoogleLogin() {
        signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client))
            .requestEmail()
            .build()
        signInClient = GoogleSignIn.getClient(this, signInOptions)

    }


}