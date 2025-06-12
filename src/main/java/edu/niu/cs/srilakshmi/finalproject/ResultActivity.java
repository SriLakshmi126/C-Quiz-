

package edu.niu.cs.srilakshmi.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

// This activity displays the quiz result including score, correct/incorrect question numbers, and navigation to review
public class ResultActivity extends AppCompatActivity {

    private TextView resultText, correctText, incorrectText;
    private Button finishButton, reviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Link UI elements
        resultText = findViewById(R.id.resultText);
        correctText = findViewById(R.id.correctText);
        incorrectText = findViewById(R.id.incorrectText);
        finishButton = findViewById(R.id.finishButton);
        reviewButton = findViewById(R.id.reviewButton);

        // Get data passed from QuizActivity
        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        ArrayList<Integer> correctList = getIntent().getIntegerArrayListExtra("correctList");
        ArrayList<Integer> incorrectList = getIntent().getIntegerArrayListExtra("incorrectList");
        ArrayList<Question> questions = getIntent().getParcelableArrayListExtra("questions");
        ArrayList<String> userAnswers = getIntent().getStringArrayListExtra("userAnswers");

        // Display the user's score and formatted correct/incorrect question numbers
        resultText.setText("Your Score: " + score + " / " + total);
        correctText.setText("Correct Questions: " + formatQuestionList(correctList, total));
        incorrectText.setText("Incorrect Questions: " + formatQuestionList(incorrectList, total));

        Log.d("RESULT", "Sending " + (questions != null ? questions.size() : 0) + " questions to ReviewActivity");

        // Navigate to ReviewActivity with questions and user answers
        reviewButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ReviewActivity.class);
            intent.putParcelableArrayListExtra("questions", new ArrayList<>(questions));
            intent.putStringArrayListExtra("userAnswers", userAnswers);
            startActivity(intent);
        });

        // Finish the activity and go back
        finishButton.setOnClickListener(v -> finish());
    }

    /**
     * Formats a list of question indexes into Q1, Q2, ... style.
     * Returns "None" if list is empty or invalid.
     */
    private String formatQuestionList(ArrayList<Integer> list, int total) {
        if (list == null || list.isEmpty()) return "None";

        // Remove duplicates and maintain insertion order
        Set<Integer> uniqueIndexes = new LinkedHashSet<>(list);
        StringBuilder sb = new StringBuilder();

        for (int index : uniqueIndexes) {
            if (index > 0 && index <= total) {
                sb.append("Q").append(index).append(", ");
            }
        }

        // Remove trailing comma and space if needed
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }

        return sb.length() > 0 ? sb.toString() : "None";
    }
}
