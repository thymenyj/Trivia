package com.example.thymen.trivia;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class TriviaHelper implements Response.Listener<JSONObject>, Response.ErrorListener{
    public Context context;
    public Callback activity;

    public interface Callback {
        void gotQuestion(Question question);
        void gotError(String message);
    }

    public TriviaHelper(Context context) {
        this.context = context;
    }

    public void getNextQuestion(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);Random r = new Random();

        // takes random integer and creates a random category request
        int random = ThreadLocalRandom.current().nextInt(0, 3000);
        String url = "http://jservice.io/api/category?id=" + random;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,this, this);
        queue.add(jsonObjectRequest);
    }

    public void onResponse(JSONObject response) {

        try {
            // get JSONArray with clues
            JSONArray clues = response.getJSONArray("clues");
            int length = clues.length();
            int randomCorrect = ThreadLocalRandom.current().nextInt(0, length);
            JSONObject object = clues.getJSONObject(randomCorrect);

            // create a new question
            Question questionItem = new Question();

            // set question variable to the question value
            String question = object.getString("question");
            questionItem.setQuestion(question);

            // get the correct answer of question
            String correctAnswer = object.getString("answer");
            questionItem.setCorrectAnswer(correctAnswer);

            // get two random other answers
            String wrongAnswer1 = correctAnswer;
            String wrongAnswer2 = correctAnswer;

            while (wrongAnswer1 == correctAnswer) {
                int random1 = ThreadLocalRandom.current().nextInt(0, length);
                JSONObject wrongObject1 = clues.getJSONObject(random1);
                wrongAnswer1 = wrongObject1.getString("answer");
            }
            while (wrongAnswer2 == correctAnswer || wrongAnswer2 == wrongAnswer1) {
                int random2 = ThreadLocalRandom.current().nextInt(0, length);
                JSONObject wrongObject2 = clues.getJSONObject(random2);
                wrongAnswer2 = wrongObject2.getString("answer");
            }

            // make list of answers
            ArrayList answers = new ArrayList<String>();
            answers.add(correctAnswer);
            answers.add(wrongAnswer1);
            answers.add(wrongAnswer2);

            // shuffle list to get random correct place
            Collections.shuffle(answers);

            questionItem.setAnswers(answers);

            activity.gotQuestion(questionItem);
        }
        catch(JSONException e){
            e.printStackTrace();
            Log.d("RESPONSE", response.toString());
        }
    }

    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotError(message);
    }
}