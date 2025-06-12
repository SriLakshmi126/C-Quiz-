

package edu.niu.cs.srilakshmi.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String email = getIntent().getStringExtra("email"); // if needed

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, QuizSelectionActivity.class);
            intent.putExtra("email", email); // optional if needed in selection
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
