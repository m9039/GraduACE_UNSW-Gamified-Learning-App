package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ortiz.touchview.TouchImageView;

import java.util.ArrayList;

public class MockAssessmentQActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNumber, tvQuizTitle;
    private String quizType, result;
    private Button btnOptionA, btnOptionB, btnOptionC, btnOptionD, btnNext, btnResults, btnSolution;
    Button ibMenu;
    //TouchImageView enables user to zoom into question images
    //Reference Mike Ortiz:https://github.com/MikeOrtiz/TouchImageView
    private TouchImageView ivImage;
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
        ibMenu = findViewById(R.id.ibMenu);

        //When menu button clicked, launch this method
        ibMenuClicked();

        //calls the intent and passes respective quiz type
        Intent intent = getIntent();
        quizType = intent.getStringExtra("receiveQuiz");

        //Calling the corresponding array list and populating data
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

        //Calls method when buttons are clicked
        nextButtonClicked();
        optionAClicked();
        optionBClicked();
        optionCClicked();
        optionDClicked();
        solutionButtonClicked();

    }

    //Launches when next button is clicked
    private void nextButtonClicked (){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting them so that they are all clickable
                btnOptionA.setClickable(true);
                btnOptionA.setEnabled(true);
                btnOptionB.setClickable(true);
                btnOptionB.setEnabled(true);
                btnOptionC.setClickable(true);
                btnOptionC.setEnabled(true);
                btnOptionD.setClickable(true);
                btnOptionD.setEnabled(true);

                //Setting the background colour back to white
                btnOptionA.setBackgroundColor(getColor(R.color.white));
                btnOptionB.setBackgroundColor(getColor(R.color.white));
                btnOptionC.setBackgroundColor(getColor(R.color.white));
                btnOptionD.setBackgroundColor(getColor(R.color.white));

                //Resetting zoom function
                ivImage.resetZoom();

                //When user gets to the last question in the quiz
                if (currentArrayPosition == 4){
                    //Hide the next button
                    btnNext.setVisibility(View.GONE);
                    //Show the results button
                    btnResults.setVisibility(View.VISIBLE);

                    //When result button is clicked
                    btnResults.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            //sets score according with which quiz the user completed
                            if (quizType.equals("Numerical")){
                                result = "Your Numerical Reasoning test score is:\n" + currentScore + "/5";
                            } else if (quizType.equals("Verbal")){
                                result = "Your Verbal Reasoning test score is:\n" + currentScore + "/5";
                            } else if (quizType.equals("Logical")){
                                result = "Your Logical Reasoning test score is:\n" + currentScore + "/5";
                            }
                            //Calls method to show bottom screen and results
                            showResultBottomScreen();
                        }
                    });
                } else {
                    //if user not up to the last question, next question is displayed
                    currentQuestionPosition++;
                    currentArrayPosition++;
                    setDataToViews(currentQuestionPosition, currentArrayPosition);
                }
            }
        });
    }

    //launch method when solution button is clicked
    private void solutionButtonClicked(){
        btnSolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSolutionScreen();
            }
        });
    }

    //Source: https://www.youtube.com/watch?v=5lmhxob61eg&list=RDCMUC0RhatS1pyxInC00YKjjBqQ&start_radio=1
    //when option A is clicked
    public void optionAClicked(){
        btnOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //disable clickability for other buttons as users are unable to change responses once they have chosen
                btnOptionB.setClickable(false);
                btnOptionB.setEnabled(false);
                btnOptionC.setClickable(false);
                btnOptionC.setEnabled(false);
                btnOptionD.setClickable(false);
                btnOptionD.setEnabled(false);
                if (quizType.equals("Numerical")){
                    //if the answer to this question is equal to the option selected
                    if (numericalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionA.getText().toString().trim().toLowerCase())){
                        //set the background colour to green
                        //Source: https://stackoverflow.com/questions/30689924/how-to-change-color-of-a-button-when-it-is-pressed-like-in-a-quiz-app
                        btnOptionA.setBackgroundColor(getColor(R.color.green));
                        //increment score
                        currentScore++;
                    } else {
                        //otherwise set it to red because it is incorrect
                        btnOptionA.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Verbal")){
                    //if the answer to this question is equal to the option selected
                    if (verbalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionA.getText().toString().trim().toLowerCase())){
                        //set the background colour to green
                        btnOptionA.setBackgroundColor(getColor(R.color.green));
                        //increment score
                        currentScore++;
                    } else {
                        //otherwise set it to red because it is incorrect
                        btnOptionA.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Logical")){
                    //if the answer to this question is equal to the option selected
                    if (logicalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionA.getText().toString().trim().toLowerCase())){
                        //set background colour to green
                        btnOptionA.setBackgroundColor(getColor(R.color.green));
                        //increment score
                        currentScore++;
                    } else {
                        //otherwise set it to red because it is incorrect
                        btnOptionA.setBackgroundColor(getColor(R.color.red));
                    }
                }
            }
        });
    }

    public void optionBClicked(){
        btnOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOptionA.setClickable(false);
                btnOptionA.setEnabled(false);
                btnOptionC.setClickable(false);
                btnOptionC.setEnabled(false);
                btnOptionD.setClickable(false);
                btnOptionD.setEnabled(false);

                if (quizType.equals("Numerical")){
                    if (numericalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionB.getText().toString().trim().toLowerCase())){
                        btnOptionB.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionB.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Verbal")){
                    if (verbalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionB.getText().toString().trim().toLowerCase())){
                        btnOptionB.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionB.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Logical")){
                    if (logicalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionB.getText().toString().trim().toLowerCase())){
                        btnOptionB.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionB.setBackgroundColor(getColor(R.color.red));
                    }
                }
            }
        });
    }

    public void optionCClicked(){
        btnOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOptionA.setClickable(false);
                btnOptionA.setEnabled(false);
                btnOptionB.setClickable(false);
                btnOptionB.setEnabled(false);
                btnOptionD.setClickable(false);
                btnOptionD.setEnabled(false);

                if (quizType.equals("Numerical")){
                    if (numericalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionC.getText().toString().trim().toLowerCase())){
                        btnOptionC.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionC.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Verbal")){
                    if (verbalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionC.getText().toString().trim().toLowerCase())){
                        btnOptionC.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionC.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Logical")){
                    if (logicalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionC.getText().toString().trim().toLowerCase())){
                        btnOptionC.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionC.setBackgroundColor(getColor(R.color.red));
                    }
                }
            }
        });
    }

    public void optionDClicked(){
        btnOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOptionA.setClickable(false);
                btnOptionA.setEnabled(false);
                btnOptionB.setClickable(false);
                btnOptionB.setEnabled(false);
                btnOptionC.setClickable(false);
                btnOptionC.setEnabled(false);

                if (quizType.equals("Numerical")){
                    if (numericalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionD.getText().toString().trim().toLowerCase())){
                        btnOptionD.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionD.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Verbal")){
                    if (verbalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionD.getText().toString().trim().toLowerCase())){
                        btnOptionD.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionD.setBackgroundColor(getColor(R.color.red));
                    }
                } else if (quizType.equals("Logical")){
                    if (logicalQuizArrayList.get(currentArrayPosition).getAnswer().trim().toLowerCase().equals(btnOptionD.getText().toString().trim().toLowerCase())){
                        btnOptionD.setBackgroundColor(getColor(R.color.green));
                        currentScore++;
                    } else {
                        btnOptionD.setBackgroundColor(getColor(R.color.red));
                    }
                }
            }
        });
    }


    private void setDataToViews(int currentQuestionPosition, int currentArrayPosition){
        currentQuestionPosition++;
        //set question number to textview
        tvQuestionNumber.setText(currentQuestionPosition + "/5");
        //display questions and options
        if (quizType.equals("Numerical")){
            tvQuestion.setText(numericalQuizArrayList.get(currentArrayPosition).getQuestion());
            ivImage.setImageResource(numericalQuizArrayList.get(currentArrayPosition).getImage());
            btnOptionA.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionA());
            btnOptionB.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionB());
            btnOptionC.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionC());
            btnOptionD.setText(numericalQuizArrayList.get(currentArrayPosition).getOptionD());
        } else if (quizType.equals("Verbal")) {
            tvQuestion.setText(verbalQuizArrayList.get(currentArrayPosition).getQuestion());
            ivImage.setImageResource(verbalQuizArrayList.get(currentArrayPosition).getImage());
            btnOptionA.setText(verbalQuizArrayList.get(currentArrayPosition).getOptionA());
            btnOptionB.setText(verbalQuizArrayList.get(currentArrayPosition).getOptionB());
            btnOptionC.setText(verbalQuizArrayList.get(currentArrayPosition).getOptionC());
            btnOptionD.setText(verbalQuizArrayList.get(currentArrayPosition).getOptionD());
        } else if (quizType.equals("Logical")) {
            tvQuestion.setText(logicalQuizArrayList.get(currentArrayPosition).getQuestion());
            ivImage.setImageResource(logicalQuizArrayList.get(currentArrayPosition).getImage());
            btnOptionA.setText(logicalQuizArrayList.get(currentArrayPosition).getOptionA());
            btnOptionB.setText(logicalQuizArrayList.get(currentArrayPosition).getOptionB());
            btnOptionC.setText(logicalQuizArrayList.get(currentArrayPosition).getOptionC());
            btnOptionD.setText(logicalQuizArrayList.get(currentArrayPosition).getOptionD());
        }
    }

    //displays results screen when user submits the last question
    private void showResultBottomScreen(){
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MockAssessmentQActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.mock_assessment_result, (LinearLayout)findViewById(R.id.llResult));
        TextView tvMockAssessResult = bottomSheetView.findViewById(R.id.tvMockAssessResult);
        Button btnReturnToQuizzes = bottomSheetView.findViewById(R.id.btnReturn);
        Button btnRestart = bottomSheetView.findViewById(R.id.btnRestart);
        tvMockAssessResult.setText(result);

        //When return button is clicked, the user goes back to quiz home
        btnReturnToQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessmentQActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });

        //when restart button is clicked, the same quiz is launched again from question1
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    //displays solutions screen
    private void showSolutionScreen(){
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MockAssessmentQActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.solutions_popup, (LinearLayout)findViewById(R.id.llSolution));
        TextView tvSolution = bottomSheetView.findViewById(R.id.tvSolution);
        Button btnBack = bottomSheetView.findViewById(R.id.btnBack);
        tvSolution.setText(result);

        //displays the corresponding solution in text view
        if (quizType.equals("Numerical")){
            tvSolution.setText(numericalQuizArrayList.get(currentArrayPosition).getSolution());
        } else if (quizType.equals("Verbal")){
            tvSolution.setText(verbalQuizArrayList.get(currentArrayPosition).getSolution());
        } else if (quizType.equals("Logical")){
            tvSolution.setText(logicalQuizArrayList.get(currentArrayPosition).getSolution());
        }

        //hides the solution when back button is clicked
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    //populate array list with data
    private void getNumericalQuestions(ArrayList<MockAssessment> numericalQuizArrayList) {
        numericalQuizArrayList.add(new MockAssessment("If Heathrow Airport pledged in January to reduce cancelled flights by 80% by March, by how many cancelled flights have they failed to reach this target?", R.drawable.numerical1, "4", "0", "14", "18", "4", "Step 1: Take the number of flights cancelled in January and calculate an 80% reduction: \n 30 × (1-0.8) = 6 \n Step 2: Subtract this figure from the March figure:\n 10 - 6 = 4, so the answer is 4"));
        numericalQuizArrayList.add(new MockAssessment("If there were 50,000 people employed in Blackpool in 2021 what is the ratio of employed to unemployed people in that year?", R.drawable.numerical2, "25:1", "12.5:1", "10:1", "8.33:1", "10:1", "50k : 5k \n 50 / 5 = 10 \n so the answer is 10:1"));
        numericalQuizArrayList.add(new MockAssessment("What was the average total percentage decrease in the number of homes sold by Bradfield Homes and Thompson Homes from May to June?", R.drawable.numerical3, "18.18%", "13.26%", "13.04%", "8.33%", "13.04%", "May = 110 + 120 = 230 June = 90 + 110 = 200 230 - 200 = 30 \n 30 / 230 x 100 = 13.04%, so the answer is 13.04%"));
        numericalQuizArrayList.add(new MockAssessment("2,000 CHF was used to purchase USD in Q2 and then sold in Q4. How much will the amount be worth in CHF?", R.drawable.numerical4, "2,117.65", "2,098.03", "2,077.67", "1,981.48", "2,117.65", "Step 1: Calculate how many USD you can buy with CHF 2,000 in Q2: \n 2,000 × 1.08 = USD 2,160 \n Step 2: Calculate how many CHF you can purchase with USD 2,160 in Q4: \n 2,160 ÷ 1.02 = CHF 2,117.65, so the answer is 2,117.65"));
        numericalQuizArrayList.add(new MockAssessment("A Flume, a Rouser and a Lior car are driven around a test track for 450 miles. How much CO2, to the nearest kg, is emitted by the three cars? (1.61 km = 1 mile)?", R.drawable.numerical5, "200kg", "201kg", "202kg", "203kg", "202kg", "Step 1: sum the CO2 emissions for the 3 cars 94 + 86 + 99 = 279 \n Step 2: put into a miles: km ratio 279 x 1.61 = 449.19 g / per mile\n Step 3: convert the emissions from g/km 449.19 x 450 = 202,136 g = 202 kg\n Thus the correct answer is 202 kg"));
    }

    //populate array list with data
    private void getVerbalQuestions(ArrayList<MockAssessment> verbalQuizArrayList) {
        verbalQuizArrayList.add(new MockAssessment("", R.drawable.verbal1, "A", "B", "C", "D", "D", "Work-life balance schemes in the workplace have been applauded by employees and increase morale and motivation at work."));
        verbalQuizArrayList.add(new MockAssessment("", R.drawable.verbal2, "A", "B", "C", "D", "D", "No cuts are required "));
        verbalQuizArrayList.add(new MockAssessment("Please select a pair of words that have a similar relationship", R.drawable.verbal3, "A", "B", "C", "D",  "B", "tree : paper"));
        verbalQuizArrayList.add(new MockAssessment("", R.drawable.verbal4, "A", "B", "C", "D",  "B", "False"));
        verbalQuizArrayList.add(new MockAssessment("Please identify which type of error appears in the sentence below: ", R.drawable.verbal5, "A", "B", "C", "D",  "A", "Grammar"));
    }

    //populate array list with data
    private void getLogicalQuestions(ArrayList<MockAssessment> logicalQuizArrayList) {
        logicalQuizArrayList.add(new MockAssessment("Which of the boxes comes next in the sequence?", R.drawable.logical1, "A", "B", "C", "D", "A", "The arrows change direction from pointing up, to down, to right, then to left with each turn. Circles increase by one with each turn.\n" +
                "In the fifth box the arrow is pointing up and there are five circles, so the next box must have the arrow pointing down, and have six circles."));
        logicalQuizArrayList.add(new MockAssessment("How many triangles will be in the 6th shape?", R.drawable.logical2, "10", "12", "14", "16", "14", "The number of triangles is increasing by 2 as you move along the sequence. If you continue to add 2 until you reach the 6th shape you reach 14, so the answer is 14"));
        logicalQuizArrayList.add(new MockAssessment("Identify the pattern and work out which one of the suggested images would complete the sequence.", R.drawable.logical3, "A", "B", "C", "D",  "B", "The first thing you can identify is that the triangle is alternatively flipping vertically, ruling out C and D. The only difference between A and B is the size of the square.\n" +
                "To maintain a sequential pattern, B must be correct: the square grows in size and then shrinks as it progresses along the sequence."));
        logicalQuizArrayList.add(new MockAssessment("In the grid, one box is missing. You must work out what rules are being applied in the other boxes in order to work out which of boxes A to D will complete the grid.", R.drawable.logical4, "A", "B", "C", "D",  "D", "In the question the key rule is that the number of ‘star’ shapes in the central column must always equal the number of double circle shapes.\n" +
                "✲ = ⌾\n" +
                "If there are no star shapes there should be no circle shapes. If there are three star shapes, there should be three circle shapes. Option D is the only one that abides by this rule."));
        logicalQuizArrayList.add(new MockAssessment("Who can you infer is most likely to have taken the lemon cake?", R.drawable.logical5, "Maggie", "Susan", "Mark", "None",  "Mark", "Given what we have learned, it is reasonable to assume that Mark is the culprit. The facts about Maggie and Susan might help in forming a deductive argument, but in this context they are inconsequential. Mark’s pattern of behaviour indicates that he is guilty."));
    }

    //When menu hamburger button is clicked, launch method
    private void ibMenuClicked() {
        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuScreen();
            }
        });
    }

    //Displays menu screen from xml file and attaches functionalities to each property
    private void showMenuScreen() {
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MockAssessmentQActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu, (LinearLayout)findViewById(R.id.llMenu));
        Button btnClose = bottomSheetView.findViewById(R.id.btnClose);
        Button btnHome = bottomSheetView.findViewById(R.id.btnHome);
        Button btnPersonalityQuizzes = bottomSheetView.findViewById(R.id.btnPersonalityQuizzes);
        Button btnMockAssessments = bottomSheetView.findViewById(R.id.btnMockAssessments);
        Button btnJobSuggestions = bottomSheetView.findViewById(R.id.btnJobSuggestions);
        Button btnResourceVideos = bottomSheetView.findViewById(R.id.btnResourceVideos);

        //When close button is clicked, the menu is hidden
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessmentQActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnPersonalityQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessmentQActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnMockAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessmentQActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnJobSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessmentQActivity.this, JobHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnResourceVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MockAssessmentQActivity.this, ResourceHomeActivity.class);
                startActivity(intent);
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}