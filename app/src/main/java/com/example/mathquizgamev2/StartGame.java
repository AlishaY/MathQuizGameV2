package com.example.mathquizgamev2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {
    /*
    Dev icons originally made by Konpa (under MIT License)
    Link for download: https://devicons.github.io/devicon/
    License: https://github.com/devicons/devicon/blob/master/LICENSE
     */

    TextView tvResult;              // A TextView for showing Result
    ImageView ivShowImage;          // An ImageView for showing an image in question
    HashMap<String, Integer> map = new HashMap<>(); // Instantiate a HashMap to store technology names and corresponding image resource ids
    ArrayList<String> queList = new ArrayList<>(); // An ArrayList for storing technology names only
    int index;                      // Declare an index variable. We'll keep incrementing it as the quiz proceeds to the next question
    Button btn1, btn2, btn3, btn4;  // Declare four button object references for displaying four options to choose from
    TextView tvPoints;              // A TextView for displaying points
    int points;                     // An integer variable to store points


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) { //If you leave out this line, then only your code is run. The existing code is ignored completely
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);    // Set the content view with a layout file.
        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);

        final MediaPlayer correctAudio = MediaPlayer.create(this,R.raw.correct);
        // Initialize index with 0
        index = 0;
        // Populate queList with all the technology names
        queList.add("60");
        queList.add("38");
        queList.add("31");
        queList.add("42");
        queList.add("12");
        queList.add("60");
        queList.add("31");
        queList.add("19");
        queList.add("26");
        queList.add("68");
        // Put all the technology names with technology image resource ids in map.
        map.put(queList.get(0), R.drawable.q1);
        map.put(queList.get(1), R.drawable.q2);
        map.put(queList.get(2), R.drawable.q3);
        map.put(queList.get(3), R.drawable.q4);
        map.put(queList.get(4), R.drawable.q5);
        map.put(queList.get(5), R.drawable.q6);
        map.put(queList.get(6), R.drawable.q7);
        map.put(queList.get(7), R.drawable.q8);
        map.put(queList.get(8), R.drawable.q9);
        map.put(queList.get(9), R.drawable.q10);

        // Initialize points to 0
        points = 0;
        // Call startGame() method that is responsible for starting the quiz.
        startGame();
    }

    

    private void startGame() {
        tvPoints.setText(points + " / " + queList.size());
        // To generate a question, call generateQuestions() method and pass
        // index as parameter.
        generateQuestions(index);
    }

    private void generateQuestions(int index) {
            // Clone queList to a new ArrayList called queListTemp.
        ArrayList<String> queListTemp = (ArrayList<String>) queList.clone();
            // Get the correct answer for the current question from queList using index.
        String correctAnswer = queList.get(index);
            // You need to find three non-repeated incorrect answers randomly.
            // So, delete the correct answer from queListTemp.
            // Shuffle it and get first three elements from it.
        queListTemp.remove(correctAnswer);
        Collections.shuffle(queListTemp);
            // Create a temporary ArrayList for storing three non-repeated random answers
            // from queListTemp and one correct answer.
        ArrayList<String> newList = new ArrayList<>();
            // Get first three elements from queListTemp and add into newList.
        newList.add(queListTemp.get(0));
        newList.add(queListTemp.get(1));
        newList.add(queListTemp.get(2));
            // Also add the correct answer into newList
        newList.add(correctAnswer);
            // Shuffle newList so that the correct answer can be placed in one of the four
            // buttons, randomly.
        Collections.shuffle(newList);
            // Once you shuffle newList, set all four Button's text with the elements
            // from newList.
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
            // Also, set the ImageView with current image from map
        ivShowImage.setImageResource(map.get(queList.get(index)));
    }

    public void nextQuestion(View view) {
        // This method is called because the user has tapped the Next button,
        // so, set the background color of all the buttons to the color that we used in start_game.xml.
        btn1.setBackgroundColor(Color.parseColor("#2196f3"));
        btn2.setBackgroundColor(Color.parseColor("#2196f3"));
        btn3.setBackgroundColor(Color.parseColor("#2196f3"));
        btn4.setBackgroundColor(Color.parseColor("#2196f3"));
        // Enable the buttons
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);

        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        Button button4 = (Button) findViewById(R.id.btn4);
        button1.setBackgroundResource(R.drawable.optiondesign);
        button2.setBackgroundResource(R.drawable.optiondesign);
        button3.setBackgroundResource(R.drawable.optiondesign);
        button4.setBackgroundResource(R.drawable.optiondesign);

        index++;
        // Check if all questions have been asked.
        if (index > queList.size() - 1){
            // If true, hide the ImageView and Buttons.
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);

            // Go to GameOver screen with points
            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finish StartGame Activity
            finish();
        } else {
            // Till there is at least one question left, initialize countDownTimer with null and
            // call startGame()
//            countDownTimer = null;
            startGame();
        }
    }

    public void answerSelected(View view) {
        // Change the clicked Button's background color
        AudioManager amanager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = amanager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
        amanager.setStreamVolume(AudioManager.STREAM_ALARM, maxVolume, 0);

        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        Button button4 = (Button) findViewById(R.id.btn4);
        button1.setBackgroundResource(R.drawable.optiondesign);
        button2.setBackgroundResource(R.drawable.optiondesign);
        button3.setBackgroundResource(R.drawable.optiondesign);
        button4.setBackgroundResource(R.drawable.optiondesign);
        //view.setBackgroundColor(Color.parseColor("#17243e"));
        //view.setBackground();
        view.setBackgroundResource(R.drawable.optionselect);
        // Disable all four Buttons
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);

        // Get clicked button's text for user answer
        String answer = ((Button) view).getText().toString().trim();
        // And, get the correct answer for the current question from queList using index
        // as position.
        String correctAnswer = queList.get(index);
        // Compare answer and correctAnswer, that is, the answer selected by the user
        // and the correct answer for this question.
        if(answer.equals(correctAnswer)){
            // If true, the user has selected the right answer. So, increment points.
            final MediaPlayer correctAudio = MediaPlayer.create(this,R.raw.correct);
            points++;
            // Here we are incrementing points by 1 here, but, you can increment by any number
            // you want.
            // Update the TextViews for points and result
            correctAudio.start();
            tvPoints.setText(points + " / " + queList.size());
            tvResult.setText("Correct");
            tvResult.setTextColor(this.getResources().getColor(R.color.white));
        } else {
            // In else, that is, when the user answer is incorrect, show "Wrong" in tvResult.
            final MediaPlayer wrongAudio = MediaPlayer.create(this,R.raw.wrong2);
            wrongAudio.start();
            tvResult.setText("Wrong");
            tvResult.setTextColor(this.getResources().getColor(R.color.red));
        }
    }


}
