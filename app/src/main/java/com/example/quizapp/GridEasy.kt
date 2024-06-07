package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.graphics.Color
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import kotlin.time.times

class GridEasy: AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private var mistakeCount = 0
    private val maxMistakes = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        gridLayout = findViewById(R.id.grid_layout)
        setupGame()
    }

    private var firstClick: Button? = null
    private var secondClick: Button? = null

    private fun setupGame() {
        val endGame: TextView = findViewById(R.id.game_lost)
        val tryAgain: Button = findViewById(R.id.try_again)
        val backToMain: Button = findViewById(R.id.back_to_main)
        val numList = createOperations(8)
        val operationAndResult = HashMap<String, String>()
        for (i in 0..7){
            val op = numList[i*2]
            val result = numList[i*2+1]
            operationAndResult.put(op, result)
        }

        val numListShuffled = numList.shuffle()
        gridLayout.removeAllViews()
        gridLayout.columnCount = 4 // Set column count for a 4x4 grid


        numList.forEach {item ->
            val button = Button(this).apply {
                text = item
                layoutParams = GridLayout.LayoutParams(
                    GridLayout.spec(GridLayout.UNDEFINED, 1f),
                    GridLayout.spec(GridLayout.UNDEFINED, 1f)
                ).apply {
                    width = 0
                    height = 0
                    textSize = 25F
                    setMargins(8, 8, 8, 8)
                }
                setBackgroundColor(Color.rgb(238, 220, 130))
                setOnClickListener {
                    if (firstClick == null) {
                        firstClick = it as Button
                        firstClick?.setBackgroundColor(Color.rgb(187, 134, 252))
                        firstClick?.isEnabled = false
                    } else if (secondClick == null && it != firstClick) {
                        secondClick = it as Button
                        if (operationAndResult.get(firstClick?.text) == secondClick?.text || operationAndResult.get(secondClick?.text) == firstClick?.text){
                            secondClick?.setBackgroundColor(Color.rgb(187, 134, 252))
                            firstClick?.visibility = View.INVISIBLE
                            secondClick?.visibility = View.INVISIBLE
                        } else {
                            mistakeCount++
                            firstClick?.setBackgroundColor(Color.rgb(187, 134, 252))
                            secondClick?.setBackgroundColor(Color.rgb(187, 134, 252))
                            if (mistakeCount > maxMistakes) {
                                gridLayout.visibility = View.GONE
                                endGame.visibility = View.VISIBLE
                                tryAgain.visibility = View.VISIBLE
                                backToMain.visibility = View.VISIBLE

                            }
                            firstClick?.isEnabled = true
                            firstClick?.setBackgroundColor(Color.rgb(238, 220, 130))
                            secondClick?.setBackgroundColor(Color.rgb(238, 220, 130))
                        }
                        firstClick = null
                        secondClick = null
                    }
                }
            }
            gridLayout.addView(button)
        }

        tryAgain.setOnClickListener {
            val intent = Intent(this, GridActivity::class.java)
            startActivity(intent)
        }
        backToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createOperations(numOps: Int): MutableList<String>{
        val combinedList = mutableListOf<String>()
        val operationAndResult = HashMap<String, Int>()

        for (i in 1..numOps) {
            val num1 = Random.nextInt(1, 12)
            val num2 = Random.nextInt(1, 12)
            val operationType = Random.nextInt(1, 4) // 1 for addition, 2 for subtraction, 3 for multiplication

            when (operationType) {
                1 -> {
                    val result = num1 + num2
                    operationAndResult.put("$num1 + $num2", result)
                    combinedList.add("$num1 + $num2")
                    combinedList.add(result.toString())
                }
                2 -> {
                    val result = num1 - num2
                    operationAndResult.put("$num1 - $num2", result)
                    combinedList.add("$num1 - $num2")
                    combinedList.add(result.toString())
                }
                3 -> {
                    val result = num1 * num2
                    operationAndResult.put("$num1 * $num2", result)
                    combinedList.add("$num1 * $num2")
                    combinedList.add(result.toString())
                }
            }
        }
        return combinedList
    }
}