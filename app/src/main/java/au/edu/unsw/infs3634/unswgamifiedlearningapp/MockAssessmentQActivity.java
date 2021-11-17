package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MockAssessmentQActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNumber, tvQuizTitle;
    private String quizType;
    private Button btnOptionA, btnOptionB, btnOptionC, btnOptionD, btnNext, btnResults, btnSolution;
    private ImageView ivImage;
    private ArrayList<MockAssessment> numericalQuizArrayList;
    private ArrayList<MockAssessment> verbalQuizArrayList;
    private ArrayList<MockAssessment> logicalQuizArrayList;
    int currentScore = 0, currentQuestionPosition = 0, currentArrayPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_assessment_activity);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        btnOptionA = findViewById(R.id.btnOptionA);
        btnOptionB = findViewById(R.id.btnOptionB);
        btnOptionC = findViewById(R.id.btnOptionC);
        btnOptionD = findViewById(R.id.btnOptionD);
        btnNext = findViewById(R.id.btnNextQuestion);
        btnResults = findViewById(R.id.btnViewResults);
        btnSolution = findViewById(R.id.btnSolution);
        ivImage = findViewById(R.id.ivImage);
        numericalQuizArrayList = new ArrayList<>();
        verbalQuizArrayList = new ArrayList<>();
        logicalQuizArrayList = new ArrayList<>();

        Intent intent = getIntent();
        quizType = intent.getStringExtra("receiveQuiz");

        if (quizType.equals("Numerical")){
            tvQuizTitle.setText("Numerical Reasoning Test");
            getNumericalQuestions(numericalQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        } else if (quizType.equals("Verbal")) {
            tvQuizTitle.setText("Verbal Reasoning Test");
            getVerbalQuestions(verbalQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        } else if (quizType.equals("Logical")) {
            tvQuizTitle.setText("Logical Reasoning Test");
            getLogicalQuestions(logicalQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        }
    }

    private void setDataToViews(int currentQuestionPosition, int currentArrayPosition){
        currentQuestionPosition++;
        tvQuestionNumber.setText(currentQuestionPosition + "/5");
        if (quizType.equals("Numerical")){
            tvQuestion.setText(numericalQuizArrayList.get(currentArrayPosition).getQuestion());
            ivImage.setImageResource(numericalQuizArrayList.get(currentArrayPosition).getImage());
            btnOptionA.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionA());
            btnOptionB.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionB());
            btnOptionC.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionC());
            btnOptionD.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionD());
        } else if (quizType.equals("Verbal")) {
            tvQuestion.setText(verbalQuizArrayList.get(currentArrayPosition).getQuestion());
        } else if (quizType.equals("Logical")) {
            tvQuestion.setText(logicalQuizArrayList.get(currentArrayPosition).getQuestion());
        }
    }

    private void getNumericalQuestions(ArrayList<MockAssessment> numericalQuizArrayList) {
        numericalQuizArrayList.add(new MockAssessment("If Heathrow Airport pledged in January to reduce cancelled flights by 80% by March, by how many cancelled flights have they failed to reach this target?", R.drawable.numerical1, "4", "0", "14", "18", "4", "Step 1: Take the number of flights cancelled in January and calculate an 80% reduction: \n 30 × (1-0.8) = 6 \n Step 2: Subtract this figure from the March figure:\n 10 - 6 = 4, so the answer is 4"));
        numericalQuizArrayList.add(new MockAssessment("If there were 50,000 people employed in Blackpool in 2021 what is the ratio of employed to unemployed people in that year?", R.drawable.numerical2, "25:1", "12.5:1", "10:1", "8.33:1", "10:1", "50k : 5k \n 50 / 5 = 10 \n so the answer is 10:1"));
        numericalQuizArrayList.add(new MockAssessment("What was the average total percentage decrease in the number of homes sold by Bradfield Homes and Thompson Homes from May to June?", R.drawable.numerical3, "18.18%", "13.26%", "13.04%", "8.33%", "13.04%", "May = 110 + 120 = 230 June = 90 + 110 = 200 230 - 200 = 30 \n 30 / 230 x 100 = 13.04%, so the answer is 13.04%"));
        numericalQuizArrayList.add(new MockAssessment("2,000 CHF was used to purchase USD in Q2 and then sold in Q4. How much will the amount be worth in CHF?", R.drawable.numerical4, "2,117.65", "2,098.03", "2,077.67", "1,981.48", "2,117.65", "Step 1: Calculate how many USD you can buy with CHF 2,000 in Q2: \n 2,000 × 1.08 = USD 2,160 \n Step 2: Calculate how many CHF you can purchase with USD 2,160 in Q4: \n 2,160 ÷ 1.02 = CHF 2,117.65, so the answer is 2,117.65"));
        numericalQuizArrayList.add(new MockAssessment("A Flume, a Rouser and a Lior car are driven around a test track for 450 miles. How much CO2, to the nearest kg, is emitted by the three cars? (1.61 km = 1 mile)?", R.drawable.numerical5, "200kg", "201kg", "202kg", "203kg", "202kg", "Step 1: sum the CO2 emissions for the 3 cars 94 + 86 + 99 = 279 \n Step 2: put into a miles: km ratio 279 x 1.61 = 449.19 g / per mile\n Step 3: convert the emissions from g/km 449.19 x 450 = 202,136 g = 202 kg\n Thus the correct answer is 202 kg"));
    }

    private void getVerbalQuestions(ArrayList<MockAssessment> verbalQuizArrayList) {
        verbalQuizArrayList.add(new MockAssessment("If Heathrow Airport pledged in January to reduce cancelled flights by 80% by March, by how many cancelled flights have they failed to reach this target?", R.drawable.numerical1, "4", "0", "14", "18", "4", "Step 1: Take the number of flights cancelled in January and calculate an 80% reduction: \n 30 × (1-0.8) = 6 \n Step 2: Subtract this figure from the March figure:\n 10 - 6 = 4, so the answer is 4"));
        verbalQuizArrayList.add(new MockAssessment("If there were 50,000 people employed in Blackpool in 2021 what is the ratio of employed to unemployed people in that year?", R.drawable.numerical2, "25:1", "12.5:1", "10:1", "8.33:1", "10:1", "50k : 5k \n 50 / 5 = 10 \n so the answer is 10:1"));
        verbalQuizArrayList.add(new MockAssessment("What was the average total percentage decrease in the number of homes sold by Bradfield Homes and Thompson Homes from May to June?", R.drawable.numerical3, "18.18%", "13.26%", "13.04%", "8.33%", "13.04%", "May = 110 + 120 = 230 June = 90 + 110 = 200 230 - 200 = 30 \n 30 / 230 x 100 = 13.04%, so the answer is 13.04%"));
        verbalQuizArrayList.add(new MockAssessment("2,000 CHF was used to purchase USD in Q2 and then sold in Q4. How much will the amount be worth in CHF?", R.drawable.numerical4, "2,117.65", "2,098.03", "2,077.67", "1,981.48", "2,117.65", "Step 1: Calculate how many USD you can buy with CHF 2,000 in Q2: \n 2,000 × 1.08 = USD 2,160 \n Step 2: Calculate how many CHF you can purchase with USD 2,160 in Q4: \n 2,160 ÷ 1.02 = CHF 2,117.65, so the answer is 2,117.65"));
        verbalQuizArrayList.add(new MockAssessment("A Flume, a Rouser and a Lior car are driven around a test track for 450 miles. How much CO2, to the nearest kg, is emitted by the three cars? (1.61 km = 1 mile)?", R.drawable.numerical5, "200kg", "201kg", "202kg", "203kg", "202kg", "Step 1: sum the CO2 emissions for the 3 cars 94 + 86 + 99 = 279 \n Step 2: put into a miles: km ratio 279 x 1.61 = 449.19 g / per mile\n Step 3: convert the emissions from g/km 449.19 x 450 = 202,136 g = 202 kg\n Thus the correct answer is 202 kg"));
    }

    private void getLogicalQuestions(ArrayList<MockAssessment> logicalQuizArrayList) {
        logicalQuizArrayList.add(new MockAssessment("If Heathrow Airport pledged in January to reduce cancelled flights by 80% by March, by how many cancelled flights have they failed to reach this target?", R.drawable.numerical1, "4", "0", "14", "18", "4", "Step 1: Take the number of flights cancelled in January and calculate an 80% reduction: \n 30 × (1-0.8) = 6 \n Step 2: Subtract this figure from the March figure:\n 10 - 6 = 4, so the answer is 4"));
        logicalQuizArrayList.add(new MockAssessment("If there were 50,000 people employed in Blackpool in 2021 what is the ratio of employed to unemployed people in that year?", R.drawable.numerical2, "25:1", "12.5:1", "10:1", "8.33:1", "10:1", "50k : 5k \n 50 / 5 = 10 \n so the answer is 10:1"));
        logicalQuizArrayList.add(new MockAssessment("What was the average total percentage decrease in the number of homes sold by Bradfield Homes and Thompson Homes from May to June?", R.drawable.numerical3, "18.18%", "13.26%", "13.04%", "8.33%", "13.04%", "May = 110 + 120 = 230 June = 90 + 110 = 200 230 - 200 = 30 \n 30 / 230 x 100 = 13.04%, so the answer is 13.04%"));
        logicalQuizArrayList.add(new MockAssessment("2,000 CHF was used to purchase USD in Q2 and then sold in Q4. How much will the amount be worth in CHF?", R.drawable.numerical4, "2,117.65", "2,098.03", "2,077.67", "1,981.48", "2,117.65", "Step 1: Calculate how many USD you can buy with CHF 2,000 in Q2: \n 2,000 × 1.08 = USD 2,160 \n Step 2: Calculate how many CHF you can purchase with USD 2,160 in Q4: \n 2,160 ÷ 1.02 = CHF 2,117.65, so the answer is 2,117.65"));
        logicalQuizArrayList.add(new MockAssessment("A Flume, a Rouser and a Lior car are driven around a test track for 450 miles. How much CO2, to the nearest kg, is emitted by the three cars? (1.61 km = 1 mile)?", R.drawable.numerical5, "200kg", "201kg", "202kg", "203kg", "202kg", "Step 1: sum the CO2 emissions for the 3 cars 94 + 86 + 99 = 279 \n Step 2: put into a miles: km ratio 279 x 1.61 = 449.19 g / per mile\n Step 3: convert the emissions from g/km 449.19 x 450 = 202,136 g = 202 kg\n Thus the correct answer is 202 kg"));
    }
}