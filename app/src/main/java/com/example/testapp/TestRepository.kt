package com.example.testapp

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.channels.AsynchronousCloseException
import java.nio.charset.Charset

class TestRepository {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val questionsArray = obj.getJSONArray("questions")
            for (i in 0 until questionsArray.length()) {
                val questionObject = questionsArray.getJSONObject(i)
                val id = questionObject.getInt("id")
                val question = questionObject.getString("id")
                val optionOne = questionObject.getString("optionOne")
                val optionTwo = questionObject.getString("optionTwo")
                val optionThree = questionObject.getString("optionThree")
                val optionFour = questionObject.getString("optionFour")
                val correctAnswer = questionObject.getInt("correctAnswer")

                val currentQuestion = Question(
                    id,
                    question,
                    optionOne,
                    optionTwo,
                    optionThree,
                    optionFour,
                    correctAnswer
                )
                questionsList.add(currentQuestion)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return questionsList
    }


    fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val `myQuestionsJSONFile` = assets.open("Questions.json")
            val size = myQuestionsJSONFile.available()
            val buffer = ByteArray(size)
            myQuestionsJSONFile.read(buffer)
            myQuestionsJSONFile.close()
            json = String(buffer, charset)
        } catch (e: AsynchronousCloseException) {
            e.printStackTrace()
            return null
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}