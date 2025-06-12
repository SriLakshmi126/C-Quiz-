

package edu.niu.cs.srilakshmi.finalproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

// This class represents a quiz question and implements Parcelable to pass data between activities
public class Question implements Parcelable {

    private String question;           // The text of the question
    private List<String> choices;      // List of possible answer choices (typically 4)
    private String answer;             // The correct answer string

    // Constructor to create a Question object
    public Question(String question, List<String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    // Constructor used for Parcelable (to recreate from Parcel)
    protected Question(Parcel in) {
        question = in.readString();                // Read question text
        choices = in.createStringArrayList();      // Read list of choices
        answer = in.readString();                  // Read correct answer
    }

    // Parcelable Creator to regenerate Question objects from Parcels
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    // Getter for question text
    public String getQuestion() {
        return question;
    }

    // Getter for list of answer choices
    public List<String> getChoices() {
        return choices;
    }

    // Getter for the correct answer
    public String getAnswer() {
        return answer;
    }

    @Override
    public int describeContents() {
        return 0; // No special content types
    }

    // Write the object's data to the Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);          // Write question
        dest.writeStringList(choices);       // Write choices
        dest.writeString(answer);            // Write correct answer
    }
}
