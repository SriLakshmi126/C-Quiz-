

package edu.niu.cs.srilakshmi.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    private TextView questionText, questionNumber, quitButton, timerText;
    private RadioGroup choicesGroup;
    private Button nextButton, previousButton, submitButton;

    private ArrayList<Integer> correctQuestions = new ArrayList<>();
    private ArrayList<Integer> incorrectQuestions = new ArrayList<>();
    private ArrayList<String> userAnswers = new ArrayList<>();

    private CountDownTimer countDownTimer; // For countdown timer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Link UI elements
        questionText = findViewById(R.id.questionText);
        questionNumber = findViewById(R.id.questionNumber);
        quitButton = findViewById(R.id.quitButton);
        choicesGroup = findViewById(R.id.choicesGroup);
        nextButton = findViewById(R.id.nextButton);
        previousButton = findViewById(R.id.previousButton);
        submitButton = findViewById(R.id.submitButton);
        timerText = findViewById(R.id.timerText);

        // Handle quit button
        quitButton.setOnClickListener(v -> {
            if (countDownTimer != null) countDownTimer.cancel();
            Intent intent = new Intent(QuizActivity.this, QuizSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        // Load questions
        int quizNumber = getIntent().getIntExtra("quizNumber", 1);
        questions = QuestionData.getQuestionsForQuiz(quizNumber);

        showQuestion();

        // Start 3-minute timer
        startCountdownTimer();

        // Next button logic
        nextButton.setOnClickListener(v -> {
            if (!saveAnswer()) return;
            currentIndex++;
            showQuestion();
        });

        // Previous button logic
        previousButton.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                showQuestion();
            } else {
                Toast.makeText(this, "This is the first question", Toast.LENGTH_SHORT).show();
            }
        });

        // Submit button logic
        submitButton.setOnClickListener(v -> {
            if (!saveAnswer()) return;
            if (countDownTimer != null) countDownTimer.cancel(); // stop timer
            goToResult();
        });
    }

    // Save the selected answer, return false if none selected
    private boolean saveAnswer() {
        int selectedId = choicesGroup.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return false;
        }

        RadioButton selected = findViewById(selectedId);
        String answer = selected.getText().toString();

        if (currentIndex >= userAnswers.size()) {
            userAnswers.add(answer);
            if (questions.get(currentIndex).getAnswer().equals(answer)) {
                score++;
                correctQuestions.add(currentIndex + 1);
            } else {
                incorrectQuestions.add(currentIndex + 1);
            }
        } else {
            userAnswers.set(currentIndex, answer);
        }
        return true;
    }

    // Display current question and options
    private void showQuestion() {
        choicesGroup.clearCheck();
        Question q = questions.get(currentIndex);

        questionNumber.setText("Question: " + (currentIndex + 1) + "/" + questions.size());
        questionText.setText(q.getQuestion());

        List<String> choices = q.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            ((RadioButton) choicesGroup.getChildAt(i)).setText(choices.get(i));
        }

        // Restore selection if previously answered
        if (currentIndex < userAnswers.size()) {
            String selectedAnswer = userAnswers.get(currentIndex);
            for (int i = 0; i < choicesGroup.getChildCount(); i++) {
                RadioButton rb = (RadioButton) choicesGroup.getChildAt(i);
                if (rb.getText().toString().equals(selectedAnswer)) {
                    rb.setChecked(true);
                    break;
                }
            }
        }

        // Show submit button on last question only
        if (currentIndex == questions.size() - 1) {
            nextButton.setVisibility(View.GONE);
            submitButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);
        }
    }

    // Start countdown timer for 3 minutes
    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(180000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                timerText.setText(String.format("%02d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                Toast.makeText(QuizActivity.this, "Time's up! Auto-submitting...", Toast.LENGTH_LONG).show();
                autoSubmitQuiz();
            }
        }.start();
    }

    // Called when timer finishes
    private void autoSubmitQuiz() {
        // If answer not saved for last question
        if (currentIndex >= userAnswers.size()) {
            userAnswers.add(""); // No answer given
            incorrectQuestions.add(currentIndex + 1);
        }

        goToResult();
    }

    // Navigate to result screen
    private void goToResult() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questions.size());
        intent.putIntegerArrayListExtra("correctList", correctQuestions);
        intent.putIntegerArrayListExtra("incorrectList", incorrectQuestions);
        intent.putParcelableArrayListExtra("questions", new ArrayList<>(questions));
        intent.putStringArrayListExtra("userAnswers", userAnswers);
        startActivity(intent);
        finish();
    }
}
