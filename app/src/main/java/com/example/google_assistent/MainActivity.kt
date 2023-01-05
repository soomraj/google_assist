package com.example.google_assistent

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

@Suppress("DEPRECATION")
class MainActivity: AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var btnSearch: Button
    private lateinit var micIV: ImageView
    private val REQUEST_CODE_SPEECH_INPUT = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // on below line we are creating a constant value
        micIV = findViewById(R.id.mic)
        title = "Speech"
        editText = findViewById(R.id.edittext)
        btnSearch = findViewById(R.id.btnSearch)
        micIV.setOnClickListener {
            // on below line we are calling speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // on below line we are passing language model
            // and model free form in our intent
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM

            )

            // on below line we are passing our
            // language as a default language.
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            // on below line we are specifying a prompt
            // message as speak to text on below line.
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // on below line we are specifying a try catch block.
            // in this block we are calling a start activity
            // for result method and passing our result code.
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
                // val intent = Intent(Intent.ACTION_WEB_SEARCH)
                //val term = editText.text.toString()
                // intent.putExtra(SearchManager.QUERY, term)
                // startActivity(intent)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        this@MainActivity, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

    // on below line we are calling on activity result method.
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // in this method we are checking request
        // code with our result code.
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>
                //val i = Intent(applicationContext, Google::class.java)
                //  startActivity(i)
                // val term = editText.text.toString()
                // on below line we are setting data
                // to our output text view.
                editText.setText(
                    Objects.requireNonNull(res)[0]

                )}
            btnSearch.setOnClickListener {
                val intent = Intent(Intent.ACTION_WEB_SEARCH)
                val term = editText.text.toString()
                intent.putExtra(SearchManager.QUERY, term)
                startActivity(intent)
            }
        }
    }
}

