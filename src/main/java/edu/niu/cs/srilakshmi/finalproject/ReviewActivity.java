

package edu.niu.cs.srilakshmi.finalproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

// This activity allows the user to review their answers after completing a quiz
public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review); // Load the layout with ViewPager

        // Retrieve questions and user answers from the intent
        ArrayList<Question> questions = getIntent().getParcelableArrayListExtra("questions");
        ArrayList<String> userAnswers = getIntent().getStringArrayListExtra("userAnswers");

        // Validate the data before continuing
        if (questions == null || userAnswers == null || questions.size() != userAnswers.size()) {
            Log.e("REVIEW", "Mismatch in questions and answers list size or null data.");
            finish(); // Exit activity if data is invalid
            return;
        }

        // Set up the ViewPager to show review pages for each question
        ViewPager viewPager = findViewById(R.id.reviewViewPager);
        ReviewPagerAdapter adapter = new ReviewPagerAdapter(this, questions, userAnswers);
        viewPager.setAdapter(adapter); // Connect adapter to ViewPager
    }
}
