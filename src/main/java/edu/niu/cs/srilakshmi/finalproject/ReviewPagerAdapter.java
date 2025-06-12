

package edu.niu.cs.srilakshmi.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

// This adapter populates review pages inside a ViewPager to show question results
public class ReviewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<Question> questions;
    private List<String> userAnswers;

    // Constructor
    public ReviewPagerAdapter(Context context, List<Question> questions, List<String> userAnswers) {
        this.context = context;
        this.questions = questions;
        this.userAnswers = userAnswers;
    }

    // Total number of pages (questions)
    @Override
    public int getCount() {
        return questions != null ? questions.size() : 0;
    }

    // Confirms whether a page view corresponds to the returned object
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // Create a page for the given position
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.review_page, container, false);

        // Get current question and user answer
        Question question = questions.get(position);
        String userAnswer = userAnswers.get(position);
        String correctAnswer = question.getAnswer();
        List<String> choices = question.getChoices();

        // Set question text
        TextView questionText = layout.findViewById(R.id.reviewQuestionText);
        TextView questionNumber = layout.findViewById(R.id.questionNumber);
        questionNumber.setText("Question: " + (position + 1) + "/" + questions.size());
        questionText.setText("Q" + (position + 1) + ": " + question.getQuestion());

        // Set up option views
        TextView[] optionViews = {
                layout.findViewById(R.id.optionA),
                layout.findViewById(R.id.optionB),
                layout.findViewById(R.id.optionC),
                layout.findViewById(R.id.optionD)
        };

        String[] labels = {"A", "B", "C", "D"};

        // Display and color each choice
        for (int i = 0; i < optionViews.length; i++) {
            TextView option = optionViews[i];

            if (i < choices.size()) {
                String choice = choices.get(i);
                option.setText(labels[i] + ": " + choice);

                // Highlight correct answer in green
                if (choice.equals(correctAnswer)) {
                    option.setBackgroundColor(Color.parseColor("#388E3C"));
                    option.setTextColor(Color.WHITE);
                }

                // Highlight selected wrong answer in red
                if (choice.equals(userAnswer) && !choice.equals(correctAnswer)) {
                    option.setBackgroundColor(Color.parseColor("#C62828"));
                    option.setTextColor(Color.WHITE);
                }

            } else {
                // If choice is missing, mark as N/A
                option.setText(labels[i] + ": N/A");
                option.setTextColor(Color.GRAY);
                option.setBackgroundColor(Color.LTGRAY);
            }
        }

        // Navigation controls
        ViewPager viewPager = (ViewPager) container;
        Button nextButton = layout.findViewById(R.id.nextButton);
        Button prevButton = layout.findViewById(R.id.prevButton);
        TextView quitButton = layout.findViewById(R.id.quitButton); // Styled as button

        // Handle "Next"/"Done" button behavior
        if (position == getCount() - 1) {
            nextButton.setText("Done");
            nextButton.setOnClickListener(v -> {
                Intent intent = new Intent(context, QuizSelectionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            });
        } else {
            nextButton.setText("Next");
            nextButton.setOnClickListener(v -> viewPager.setCurrentItem(position + 1));
        }

        // Handle "Previous" button
        prevButton.setOnClickListener(v -> {
            if (position > 0) {
                viewPager.setCurrentItem(position - 1);
            }
        });

        // Handle "Quit" button
        quitButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuizSelectionActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });

        // Add the layout to the ViewPager
        container.addView(layout);
        return layout;
    }

    // Remove a page from the container
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    // Always refresh views
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
