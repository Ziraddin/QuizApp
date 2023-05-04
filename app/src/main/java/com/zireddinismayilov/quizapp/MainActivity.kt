package com.zireddinismayilov.quizapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.zireddinismayilov.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    val quizData = mutableListOf(
        mutableListOf("5+3", 8, 4, 6, 12),
        mutableListOf("4x6", 24, 18, 14, 12),
        mutableListOf("32/8", 4, 7, 6, 9),
        mutableListOf("17-4", 13, 15, 14, 16)
    )
    var rightAnswer: String? = null
    var qCount = 1
    var cAns = 0
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        quizData.shuffle()
        nextQuestion()
    }

    fun nextQuestion() {
        val quiz = quizData[0]
        rightAnswer = quiz[1].toString()
        binding.qcount.setText("Question $qCount")
        binding.question.setText(quiz[0].toString())

        quiz.removeAt(0)
        quiz.shuffle()

        binding.opt1.setText(quiz[0].toString())
        binding.opt2.setText(quiz[1].toString())
        binding.opt3.setText(quiz[2].toString())
        binding.opt4.setText(quiz[3].toString())

        quizData.removeAt(0)
    }

    fun checkAnswer(view: View) {
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        var alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "Correct!"
            cAns++
        } else {
            alertTitle = "Wrong"
        }

        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Right Answer : $rightAnswer")
            .setPositiveButton("OK") { dialogInterface, i -> checkQuizCount() }
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount() {
        if (qCount == 4) {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", cAns)
            startActivity(intent)
        } else {
            qCount++
            nextQuestion()
        }
    }

}