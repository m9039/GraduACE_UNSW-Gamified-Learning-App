package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PersonalityHomeActivity extends AppCompatActivity {

    Button btnOpenness, btnConscientiousness, btnExtraversion, btnAgreeableness, btnNeuroticism, btnViewResults;
    Button ibMenu;

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
        ibMenu = findViewById(R.id.ibMenu);

        ibMenuClicked();

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

    private void ibMenuClicked() {
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuScreen();
            }
        });
    }

    private void showMenuScreen() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PersonalityHomeActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu, (LinearLayout)findViewById(R.id.llMenu));
        Button btnClose = bottomSheetView.findViewById(R.id.btnClose);
        Button btnHome = bottomSheetView.findViewById(R.id.btnHome);
        Button btnPersonalityQuizzes = bottomSheetView.findViewById(R.id.btnPersonalityQuizzes);
        Button btnMockAssessments = bottomSheetView.findViewById(R.id.btnMockAssessments);
        Button btnJobSuggestions = bottomSheetView.findViewById(R.id.btnJobSuggestions);
        Button btnResourceVideos = bottomSheetView.findViewById(R.id.btnResourceVideos);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityHomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnPersonalityQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityHomeActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
            }
        });

        btnMockAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityHomeActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
            }
        });

        btnJobSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityHomeActivity.this, JobHomeActivity.class);
                startActivity(intent);
            }
        });

        btnResourceVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityHomeActivity.this, ResourceHomeActivity.class);
                startActivity(intent);
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


}