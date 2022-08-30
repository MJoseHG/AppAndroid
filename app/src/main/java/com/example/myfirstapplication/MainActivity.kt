package com.example.myfirstapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

        usernameTie!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                notEmptyText()
            }

        })

        passwordTie!!.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                notEmptyText()
            }
        })

        clickText!!.setOnClickListener {
            val url = "https://es.dragon-ball-official.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
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
        if (user != "Hola") {
            usernameTil!!.error = getString(R.string.error)
            return false
        } else {
            usernameTil!!.error = null
        }
        return true
    }

    private fun configTermsOfUse() {
        val spanTest = SpannableStringBuilder()
        spanText.append(getString(R.string.checkBox))
    }

//    private fun configTermsOfUse() {
//        val spanText = SpannableStringBuilder()
//        spanText.append(getString(R.string.login_agree_terms_privacy1))
//        val terms2 = getString(R.string.login_agree_terms_privacy2)
//        spanText.append(terms2)
//        spanText.setSpan(object : ClickableSpan() {
//            override fun onClick(widget: View) {
//                presenter.onOpenUrl(getString(R.string.url_privacy_politics))
//            }
//
//            override fun updateDrawState(textPaint: TextPaint) {
//                textPaint.color = getColor(R.color.colorPrimaryDark)
//                textPaint.isUnderlineText = false
//            }
//        }, spanText.length - terms2.length, spanText.length, 0)
//
//        termsOfUse.movementMethod = LinkMovementMethod.getInstance()
//        termsOfUse.setText(spanText, TextView.BufferType.SPANNABLE)
//    }

    private fun isValidPassword(pass: String): Boolean {
        if (pass != "Mundo") {
            passwordTil!!.error = getString(R.string.error)
            return false
        } else {
            passwordTil!!.error = null
        }
        return true
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
            alert.setTitle(resources.getString(R.string.title_Ok))
            alert.setMessage(resources.getString(R.string.supporting_text_Ok))
            alert.setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            }
                .show()
        } else {
            alert.setTitle(resources.getString(R.string.title_Ko))
            alert.setMessage(resources.getString(R.string.supporting_text_Ko))
            alert.setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            }
                .show()
        }
    }
}

