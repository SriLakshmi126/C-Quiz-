

package edu.niu.cs.srilakshmi.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuizSelectionActivity extends AppCompatActivity {

    private Button selectedButton = null; // Tracks currently selected button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);

        int[] quizButtons = {
                R.id.quiz1, R.id.quiz2, R.id.quiz3, R.id.quiz4, R.id.quiz5
        };

        for (int i = 0; i < quizButtons.length; i++) {
            int quizNumber = i + 1;
            Button button = findViewById(quizButtons[i]);

            button.setOnClickListener(v -> {
                // Reset previously selected button to default
                if (selectedButton != null) {
                    selectedButton.setBackgroundResource(R.drawable.quiz_button_background);
                    selectedButton.setTextColor(Color.BLACK);
                }

                // Set current button as selected
                selectedButton = button;
                selectedButton.setBackgroundResource(R.drawable.quiz_button_selected);
                selectedButton.setTextColor(Color.BLACK);

                // Start the quiz immediately
                Intent intent = new Intent(QuizSelectionActivity.this, QuizActivity.class);
                intent.putExtra("quizNumber", quizNumber);
                startActivity(intent);
            });
        }

        // Go Back Button
        Button goToAppButton = findViewById(R.id.goToAppButton);
        goToAppButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuizSelectionActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Prevent return with back button
        });
    }
}
