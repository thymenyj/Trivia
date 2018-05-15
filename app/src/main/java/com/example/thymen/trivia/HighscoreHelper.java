//package com.example.thymen.trivia;
//
//import android.content.Context;
//
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Logger;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class HighscoreHelper implements Response.Listener<JSONObject>, Response.ErrorListener {
//    public Context context;
//    public Callback activity;
//
//    public interface Callback {
//        void gotHighscores(ArrayList<Highscore> highscores);
//        void gotError(String message);
//    }
//
//    public HighscoreHelper(Context context) {
//        this.context = context;
//    }
//
//    public void postNewHighscore() {
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        database.setLogLevel(Logger.Level.DEBUG);
//
//        DatabaseReference myRef = database.getReference("highscores");
//        myRef.setValue("");
//    }
//
//    public void getHighscores(Callback activity) {
//        this.activity = activity;
//
//    }
//
//    public void onResponse(JSONObject response) {
//        try {
//            JSONArray array = response.getJSONArray("highscores");
//            int length = array.length();
//            ArrayList<Highscore> highscores = new ArrayList<>();
//
//            for (int i = 0; i < length; i++) {
//                Highscore highscoreItem = new Highscore();
//                JSONObject  = array.getJSONObject(i);
//
//
//        activity.gotHighscores(highscores);
//    }
//
//    public void onErrorResponse(VolleyError error) {
//        String message = error.getMessage();
//        activity.gotError(message);
//    }
//
//}
