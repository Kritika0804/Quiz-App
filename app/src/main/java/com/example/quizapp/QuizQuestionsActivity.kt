package com.example.quizapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(),View.OnClickListener{

    private var mCurrentPosition: Int=1
    private var mQuestionsList: ArrayList<Question>?=null
    private var mSelectedOptionPosition : Int = 0


    private var progressBar:ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion: TextView?= null

    private var tvOptOne : TextView?= null
    private var tvOptTwo : TextView?= null
    private var tvOptThree: TextView?= null
    private var tvOptFour: TextView?= null

    private var btnSubmit: Button?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        tvOptOne = findViewById(R.id.tv_opt_one)
        tvOptTwo = findViewById(R.id.tv_opt_two)
        tvOptThree =  findViewById(R.id.tv_opt_three)
        tvOptFour = findViewById(R.id.tv_opt_four)
        btnSubmit = findViewById(R.id.btn_start)



        mQuestionsList = Constants.getQuestions()

        setQuestion()
        tvOptOne?.setOnClickListener(this)
        tvOptTwo?.setOnClickListener(this)
        tvOptThree?.setOnClickListener(this)
        tvOptFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
       // defaultOptionsView()


    }

    private fun setQuestion() {

        mCurrentPosition = 1
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        tvOptOne?.text = question.OptionOne
        tvOptTwo?.text = question.OptionTwo
        tvOptThree?.text = question.OptionThree
        tvOptFour?.text = question.OptionFour

//        !! is used since it,s a nullable value

        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }
        else{
            btnSubmit?.text="SUBMIT"
        }

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptOne?.let{
            options.add(0,it)
        }
        tvOptTwo?.let{
            options.add(1,it)
        }
        tvOptThree?.let{
            options.add(2,it)
        }
        tvOptFour?.let{
            options.add(3,it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }


    }
    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition= selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_opt_one -> {
                tvOptOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_opt_two -> {
                tvOptTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_opt_three -> {
                tvOptThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_opt_four -> {
                tvOptFour?.let {
                    selectedOptionView(it, 4)
                }
            }

            R.id.btn_start -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.CorrectAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.CorrectAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btnSubmit?.text = "FINISH"
                    } else {
                        btnSubmit?.text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0

                }
            }

        }
    }

    private fun answerView(answer: Int,drawableView: Int){
        when(answer){
            1->{
                tvOptOne?.background= ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            2->{
                tvOptTwo?.background= ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            3->{
                tvOptThree?.background= ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            4->{
                tvOptFour?.background= ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }

        }
    }
}