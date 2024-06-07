package com.example.quizapp
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMathBinding
import kotlin.random.Random

class MathActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMathBinding
    private var currentQuestionIndex = 0
    private var score = 0
    private var totalQuestions = 0
    private var correctAnswerIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()
        binding.option1Button.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener {
            checkAnswer(2)
        }
        binding.option4Button.setOnClickListener {
            checkAnswer(3)
        }
        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
        binding.restartButton.visibility = View.GONE
    }

    private fun correctButtonColors(buttonIndex : Int){
        when (buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.GREEN)
            1 -> binding.option2Button.setBackgroundColor(Color.GREEN)
            2 -> binding.option3Button.setBackgroundColor(Color.GREEN)
            3 -> binding.option4Button.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex : Int){
        when (buttonIndex){
            0 -> binding.option1Button.setBackgroundColor(Color.RED)
            1 -> binding.option2Button.setBackgroundColor(Color.RED)
            2 -> binding.option3Button.setBackgroundColor(Color.RED)
            3 -> binding.option4Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option4Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this,"Your Score: $score out of ${totalQuestions}", Toast.LENGTH_LONG).show()
        testVariable.myScore = testVariable.myScore +score
        binding.restartButton.isEnabled = true
    }

    private fun generateQuestion(): Pair<String, Int> {
        val num1 = Random.nextInt(1, 50)
        val num2 = Random.nextInt(1, 50)
        val operator = Random.nextInt(4)
        val question: String
        val correctAnswer: Int

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
    private fun displayQuestion(){
        val questionInfo = generateQuestion()
        val question = questionInfo.first
        val correctAnswer = questionInfo.second

        binding.questionNums.text = "Questions: "+ totalQuestions.toString() + "/10"

        binding.questionText.text = question

        val options = generateOptions(correctAnswer)
        binding.option1Button.text = options[0].toString()
        binding.option2Button.text = options[1].toString()
        binding.option3Button.text = options[2].toString()
        binding.option4Button.text = options[3].toString()

        resetButtonColors()
    }

    private fun generateOptions(correctAnswer: Int): List<Int> {
        val options = mutableListOf(correctAnswer)
        while (options.size < 4) {
            val randomOffset = Random.nextInt(-5, 6)
            val option = correctAnswer + randomOffset
            if (option != correctAnswer && !options.contains(option)) {
                options.add(option)
            }
        }
        val newoptions = options.shuffled()
        for (i in newoptions.indices) {
            if (newoptions[i] == correctAnswer) {
                correctAnswerIndex = i
                break
            }
        }
        return newoptions
    }

    private fun checkAnswer(selectedAnswerIndex:Int){
        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < 9){
            currentQuestionIndex++
            binding.questionText.postDelayed({displayQuestion()},1000)
        }else{
            showResults()
            binding.restartButton.visibility = View.VISIBLE
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        binding.restartButton.isEnabled = false
        totalQuestions = 0
        binding.restartButton.visibility = View.GONE
        displayQuestion()
    }
}
