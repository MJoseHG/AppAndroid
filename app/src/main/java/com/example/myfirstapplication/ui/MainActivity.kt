package com.example.myfirstapplication.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.myfirstapplication.R
import com.example.myfirstapplication.data.database.AppDatabase
import com.example.myfirstapplication.data.database.entities.User
import com.example.myfirstapplication.data.database.repositories.UserRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

//Aqui es appcompact para que tengamos el onresume, oncreate... etc
class MainActivity : AppCompatActivity() {
    private var usernameTil: TextInputLayout? = null
    private var passwordTil: TextInputLayout? = null
    private var usernameTie: TextInputEditText? = null
    private var passwordTie: TextInputEditText? = null
    private var clickText: TextView? = null
    private var checkButton: CheckBox? = null
    private var buttonLogin: Button? = null
    private var button01: Button? = null
    private var button02: Button? = null
    private var button03: Button? = null
    private var button04: Button? = null
    private var button05: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference TILs
        usernameTil = findViewById<View>(R.id.usernameLayout) as TextInputLayout
        passwordTil = findViewById<View>(R.id.passwordLayout) as TextInputLayout

        // Reference TIEs
        usernameTie = findViewById<View>(R.id.username) as TextInputEditText
        passwordTie = findViewById<View>(R.id.password) as TextInputEditText

        // Reference Button_CheckBox
        clickText = findViewById<View>(R.id.clickText) as TextView
        checkButton = findViewById<View>(R.id.checkButton) as CheckBox
        buttonLogin = findViewById<View>(R.id.loginButton) as Button
        button01 = findViewById<View>(R.id.button1) as Button
        button02 = findViewById<View>(R.id.button2) as Button
        button03 = findViewById<View>(R.id.button3) as Button
        button04 = findViewById<View>(R.id.button4) as Button
        button05 = findViewById<View>(R.id.button5) as Button

        configTermsOfUse()

        usernameTie!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                usernameTil!!.error = null
                notEmptyText()
            }

        })

        passwordTie!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                passwordTil!!.error = null
                notEmptyText()
            }
        })

        clickText!!.setOnClickListener {
            openUrl(url = getString(R.string.url_forgot_password))
        }

        checkButton!!.setOnCheckedChangeListener { _, isChecked ->
            notEmptyText()
            return@setOnCheckedChangeListener
        }

        buttonLogin!!.setOnClickListener {
            validData()
        }
    }

    fun allClicks(view: View) {
        val button = view as Button
        val text: String = getString(R.string.welcome_messages, button.text)

        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun isValidUser(user: String): Boolean {
        if (user.isEmpty()) {
            usernameTil!!.error = getString(R.string.error)
            return false
        } else {
            usernameTil!!.error = null
        }
        return true
    }

    private fun isValidPassword(pass: String): Boolean {
        if (pass.isEmpty()) {
            passwordTil!!.error = getString(R.string.error)
            return false
        } else {
            passwordTil!!.error = null
        }
        return true
    }

    private fun openUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun configTermsOfUse() {
        val spanText = SpannableStringBuilder()
        spanText.append(getString(R.string.login_terms_privacy1))
        val terms2 = getString(R.string.login_terms_privacy2)
        spanText.append(terms2)
        spanText.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                openUrl(url = getString(R.string.url_privacy_politics))
            }

            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = getColor(R.color.blue_200)
                textPaint.isUnderlineText = false
            }
        }, spanText.length - terms2.length, spanText.length, 0)

        checkButton?.movementMethod = LinkMovementMethod.getInstance()
        checkButton?.setText(spanText, TextView.BufferType.SPANNABLE)
    }

    private fun notEmptyText() {
        val usernameEmptyText = usernameTie!!.text!!.isNotEmpty()
        val passwordEmptyText = passwordTie!!.text!!.isNotEmpty()
        val isChecked = checkButton?.isChecked ?: false

        buttonLogin!!.isEnabled = usernameEmptyText && passwordEmptyText && isChecked
    }

    private fun validData() {
        val usernameText = usernameTie!!.text.toString()
        val passwordText = passwordTie!!.text.toString()
        val alert = MaterialAlertDialogBuilder(this)

        val a = isValidUser(usernameText)
        val b = isValidPassword(passwordText)
        if (a && b) {
            checkUser(usernameText, passwordText)
        } else {
            alert.setTitle(resources.getString(R.string.title_Ko))
            alert.setMessage(resources.getString(R.string.supporting_text_Ko))
            alert.setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            }
                .show()
        }
    }

    private fun checkUser(userName: String, password: String) {
        //TODO Iniciar Room de su instancia
        val db = AppDatabase.getInstance(this)
        //TODO Traer todos los usuarios guardados, ahora del repositorio
        val users = UserRepository.getUsers(db!!)
        //TODO Esto vamos a ver cuando lo de antes funcione, que falla y que no
        users?.forEach { user ->
            if (userName != user.userName && password != user.passwordName) {
                UserRepository.insert(User(0, userName, password), db)
                Toast.makeText(this, getString(R.string.create_login), Toast.LENGTH_LONG).show()
            }
            if (userName == user.userName && password == user.passwordName) {
                Toast.makeText(this, getString(R.string.correct_login), Toast.LENGTH_LONG).show()
            }
            else if (userName == user.userName && password != user.passwordName)
                Toast.makeText(this, getString(R.string.error_login), Toast.LENGTH_LONG).show()
        }

        //TODO Mostrar mensaje en función del paso anterior


    }
}

