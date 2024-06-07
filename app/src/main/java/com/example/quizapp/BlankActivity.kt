package com.example.quizapp
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityBlankBinding
import kotlin.random.Random

class BlankActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlankBinding
    private var currentQuestionIndex = 0
    private var score = 0
    private var totalQuestions = 0
    private var correctAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.submitButton.setOnClickListener {
            val userAnswerText = binding.userInput.text.toString()
            if (userAnswerText.isNotEmpty()) {
                val userAnswer = userAnswerText.toIntOrNull()
                if (userAnswer != null) {
                    checkAnswer(userAnswer)
                } else {
                    Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter your answer.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.restartButton.setOnClickListener {
            restartQuiz()
        }
        binding.restartButton.visibility = View.GONE
        binding.correctAnswer.visibility = View.GONE
    }

//    private fun correctButtonColors() {
//        binding.userInput.setBackgroundColor(Color.GREEN)
//    }
//
//    private fun wrongButtonColors() {
//        binding.userInput.setBackgroundColor(Color.RED)
//    }

//    private fun resetButtonColors() {
//        binding.userInput.setBackgroundColor(Color.WHITE)
//    }

    private fun showResults() {
        Toast.makeText(this, "Your Score: $score out of $totalQuestions", Toast.LENGTH_LONG).show()
        testVariable.myScore = testVariable.myScore +score
        binding.restartButton.isEnabled = true
    }

    private fun generateQuestion(): Pair<String, Int> {
        val num1 = Random.nextInt(1, 50)
        val num2 = Random.nextInt(1, 50)
        val operator = Random.nextInt(4)
        val question: String
//        val correctAnswer: Int

        when (operator) {
            0 -> {
                question = "$num1 + $num2 ="
                correctAnswer = num1 + num2
            }
            1 -> {
                if(num1 < num2){
                    question = "$num2 - $num1 ="
                    correctAnswer = num2 - num1
                }else {
                    question = "$num1 - $num2 ="
                    correctAnswer = num1 - num2
                }
            }
            3 -> {
                if (num1 % num2 == 0) {
                    question = "$num1 / $num2 ="
                    correctAnswer = num1 / num2
                }
                else if(num2 % num1 == 0){
                    question = "$num2 / $num1 ="
                    correctAnswer = num2 / num1
                }else{
                    question = "$num1 + $num2 ="
                    correctAnswer = num1 + num2
                }
            }
            else -> {
                if (num1 > 10 && num2 > num1){
                    question = "$num2 - $num1 ="
                    correctAnswer = num2 - num1
                }else if(num1 > num2 && num2 > 10){
                    question = "$num1 - $num2 ="
                    correctAnswer = num1 - num2
                }else {
                    question = "$num1 * $num2 ="
                    correctAnswer = num1 * num2
                }
            }
        }
        totalQuestions++
        return Pair(question, correctAnswer)
    }

    @SuppressLint("SetTextI18n")
    private fun displayQuestion() {
        val questionInfo = generateQuestion()
        val question = questionInfo.first

        binding.questionNums.text = "Questions: $totalQuestions/10"
        binding.questionText.text = question

//        resetButtonColors()
        binding.userInput.setText("")
        binding.correctAnswer.visibility = View.GONE
        binding.userInput.setBackgroundColor(Color.WHITE)
    }

    private fun checkAnswer(userAnswer: Int) {
        if (userAnswer == correctAnswer) {
            score++
            binding.userInput.setBackgroundColor(Color.GREEN)

//            generateQuestion()
//            correctButtonColors()
        } else {
            binding.userInput.setBackgroundColor(Color.RED)
            binding.correctAnswer.visibility = View.VISIBLE
            binding.correctAnswer.text = "Correct Answer: $correctAnswer"
//            wrongButtonColors()
//            correctButtonColors()
//            generateQuestion()
        }

        if (currentQuestionIndex < 9) {
            currentQuestionIndex++
            binding.questionText.postDelayed({ displayQuestion() }, 1000)
        } else {
            showResults()
            binding.restartButton.visibility = View.VISIBLE
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        binding.restartButton.isEnabled = false
        totalQuestions = 0
        binding.restartButton.visibility = View.GONE
        binding.userInput.setText("")
        displayQuestion()
    }
}
