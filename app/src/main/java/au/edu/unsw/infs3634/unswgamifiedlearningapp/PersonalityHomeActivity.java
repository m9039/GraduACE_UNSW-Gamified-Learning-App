package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PersonalityHomeActivity extends AppCompatActivity {

    Button btnOpenness, btnConscientiousness, btnExtraversion, btnAgreeableness, btnNeuroticism, btnViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalityhome);

        btnOpenness = findViewById(R.id.btnOpenness);
        btnConscientiousness = findViewById(R.id.btnConscientiousness);
        btnExtraversion = findViewById(R.id.btnExtraversion);
        btnAgreeableness = findViewById(R.id.btnAgreeableness);
        btnNeuroticism = findViewById(R.id.btnNeuroticism);
        btnViewResults = findViewById(R.id.btnViewResults);

        btnOpenness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quizType = "Openness";
                Intent intent = new Intent(PersonalityHomeActivity.this, PersonalityQuizActivity.class);
                intent.putExtra("receiveQuiz", quizType);
                startActivity(intent);
            }
        });

        btnConscientiousness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quizType = "Conscientiousness";
                Intent intent = new Intent(PersonalityHomeActivity.this, PersonalityQuizActivity.class);
                intent.putExtra("receiveQuiz", quizType);
                startActivity(intent);
            }
        });

        btnExtraversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quizType = "Extraversion";
                Intent intent = new Intent(PersonalityHomeActivity.this, PersonalityQuizActivity.class);
                intent.putExtra("receiveQuiz", quizType);
                startActivity(intent);
            }
        });

        btnAgreeableness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quizType = "Agreeableness";
                Intent intent = new Intent(PersonalityHomeActivity.this, PersonalityQuizActivity.class);
                intent.putExtra("receiveQuiz", quizType);
                startActivity(intent);
            }
        });

        btnNeuroticism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quizType = "Neuroticism";
                Intent intent = new Intent(PersonalityHomeActivity.this, PersonalityQuizActivity.class);
                intent.putExtra("receiveQuiz", quizType);
                startActivity(intent);
            }
        });


    }


}