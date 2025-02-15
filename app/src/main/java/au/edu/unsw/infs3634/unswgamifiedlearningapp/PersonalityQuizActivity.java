package au.edu.unsw.infs3634.unswgamifiedlearningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class PersonalityQuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNumber, tvQuizTitle;
    private String quizType, personalityDesc, personalityType, readMoreLink;
    private Button btnNext, btnResults;
    Button ibMenu;
    private SeekBar seekbar;
    private ArrayList<PersonalityQuiz> opennessPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> conscientiousnessPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> extraversionPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> agreeablenessPersonalityQuizArrayList;
    private ArrayList<PersonalityQuiz> neuroticismPersonalityQuizArrayList;
    int currentScore = 0, currentQuestionPosition = 0, seekBarProgress = 3, currentArrayPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalityquiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        btnNext = findViewById(R.id.btnNextQuestion);
        btnResults = findViewById(R.id.btnViewResults);
        seekbar = findViewById(R.id.seekBar);
        opennessPersonalityQuizArrayList = new ArrayList<>();
        conscientiousnessPersonalityQuizArrayList = new ArrayList<>();
        extraversionPersonalityQuizArrayList = new ArrayList<>();
        agreeablenessPersonalityQuizArrayList = new ArrayList<>();
        neuroticismPersonalityQuizArrayList = new ArrayList<>();
        ibMenu = findViewById(R.id.ibMenu);

        //When menu button clicked, launch this method
        ibMenuClicked();

        //calls the intent and passes respective quiz type
        Intent intent = getIntent();
        quizType = intent.getStringExtra("receiveQuiz");

        //Calling the corresponding array list and populating data
        if (quizType.equals("Openness")){
            tvQuizTitle.setText("How OPEN are you?");
            getOpennessQuizQuestions(opennessPersonalityQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        } else if (quizType.equals("Conscientiousness")) {
            tvQuizTitle.setText("How CONSCIENTIOUS are you?");
            getConscientiousnessQuizQuestions(conscientiousnessPersonalityQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        } else if (quizType.equals("Extraversion")) {
            tvQuizTitle.setText("How EXTRAVERTED are you?");
            getExtraversionQuizQuestions(extraversionPersonalityQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        } else if (quizType.equals("Agreeableness")) {
            tvQuizTitle.setText("How AGREEABLE are you?");
            getAgreeablenessQuizQuestions(agreeablenessPersonalityQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        } else if (quizType.equals("Neuroticism")) {
            tvQuizTitle.setText("How NEUROTIC are you?");
            getNeuroticismQuizQuestions(neuroticismPersonalityQuizArrayList);
            setDataToViews(currentQuestionPosition,currentArrayPosition);
        }

        //Calls method when buttons are clicked
        seekBarChanged();
        nextButtonClicked();
    }

    //when user changes the seekbar
    //Source: https://www.homeandlearn.co.uk/android/android_seekbar.html
    private void seekBarChanged (){
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //set int value as corresponding progress
                seekBarProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    //Launches when next button is clicked
    private void nextButtonClicked (){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updates the current score but adding user input
                currentScore = currentScore + seekBarProgress;
                if (currentArrayPosition == 9){
                    //when user reaches last question, next button is hidden and results button shown
                    btnNext.setVisibility(View.GONE);
                    btnResults.setVisibility(View.VISIBLE);

                    btnResults.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            //when results button is clicked, sets score and their personality type
                            if (quizType.equals("Openness")){
                                if (currentScore >= 25 ){
                                    personalityDesc = "You are more likely to be curious, have a wide range of interests and a strong sense of independence.";
                                    personalityType ="High Openness";
                                    //READ MORE FUNCTION: links to further information on Big 5 Personalities
                                    readMoreLink ="https://www.crystalknows.com/big-five/openness";
                                } else {
                                    personalityDesc = "You are more likely to be practical, conventional and prefer to stick to a routine.";
                                    personalityType ="Low Openness";
                                    readMoreLink ="https://www.crystalknows.com/big-five/openness";
                                }
                            } else if (quizType.equals("Conscientiousness")){
                                if (currentScore >= 25 ){
                                    personalityDesc = "You are more likely to be hard-working, dependable or organised.";
                                    personalityType ="High Conscientiousness";
                                    readMoreLink ="https://www.crystalknows.com/big-five/conscientiousness";

                                } else {
                                    personalityDesc = "You are more likely to be impulsive, careless or disorganised.";
                                    personalityType ="Low Conscientiousness";
                                    readMoreLink ="https://www.crystalknows.com/big-five/conscientiousness";

                                }
                            } else if (quizType.equals("Extraversion")){
                                if (currentScore >= 25 ){
                                    personalityDesc = "You are more likely to be outgoing, social and enjoys seeking adventure.";
                                    personalityType ="High Extraversion";
                                    readMoreLink ="https://www.crystalknows.com/big-five/extroversion";

                                } else {
                                    personalityDesc = "You are more likely to be quiet, reserved or withdrawn in social situations.";
                                    personalityType ="Low Extraversion";
                                    readMoreLink ="https://www.crystalknows.com/big-five/extroversion";

                                }
                            } else if (quizType.equals("Agreeableness")){
                                if (currentScore >= 25 ){
                                    personalityDesc = " You are more likely to be helpful, trusting and empathetic.";
                                    personalityType ="High Agreeableness";
                                    readMoreLink ="https://www.crystalknows.com/big-five/agreeableness";

                                } else {
                                    personalityDesc = "You are more likely to be critical, uncooperative or suspicious.";
                                    personalityType ="Low Agreeableness";
                                    readMoreLink ="https://www.crystalknows.com/big-five/agreeableness";

                                }
                            } else if (quizType.equals("Neuroticism")){
                                if (currentScore >= 25 ){
                                    personalityDesc = "You are more likely to be anxious, unhappy or prone to negative emotions.";
                                    personalityType ="High Neuroticism";
                                    readMoreLink ="https://www.crystalknows.com/big-five/neuroticism";

                                } else {
                                    personalityDesc = "You are more likely to be calm, even-tempered and secure.";
                                    personalityType ="Low Neuroticism";
                                    readMoreLink ="https://www.crystalknows.com/big-five/neuroticism";

                                }
                            }
                            //shows the results bottom screen
                            showResultBottomScreen();
                        }
                    });
                } else {
                    //if not up to the last question in the quiz, go to next question
                    currentQuestionPosition++;
                    currentArrayPosition++;
                    setDataToViews(currentArrayPosition, currentArrayPosition);
                    //set the progress bar back to neutral
                    //Source: https://stackoverflow.com/questions/23844736/resetting-a-seekbar-in-android
                    seekbar.setProgress(3);
                }
            }
        });
    }

    //shpws the results in bottom screen
    private void showResultBottomScreen(){
        //Instantiate new bottom sheet dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PersonalityQuizActivity.this);
        //Inflate the view so that users are able to view it
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.personality_result, (LinearLayout)findViewById(R.id.llResult));
        TextView tvPersonalityResult = bottomSheetView.findViewById(R.id.tvMockAssessResult);
        tvPersonalityResult.setText(personalityDesc);

        TextView tvReadMore = bottomSheetView.findViewById(R.id.tvReadMore);
        //when read more button clicked, open browser
        tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(readMoreLink));
                startActivity(intent);
            }
        });

        TextView tvMockAssessType =bottomSheetView.findViewById(R.id.tvMockAssessType);
        tvMockAssessType.setText(personalityType);

        //sends user to job recommendations screen
        Button btnRecommend = bottomSheetView.findViewById(R.id.btnRecommend);
        btnRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalityQuizActivity.this, JobHomeActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });

        //returns to main quiz screen
        Button btnReturnToQuizzes = bottomSheetView.findViewById(R.id.btnReturnToQuizzes);
        btnReturnToQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuizActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });
        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


    private void setDataToViews(int currentQuestionPosition, int currentArrayPosition){
        currentQuestionPosition++;
        tvQuestionNumber.setText(currentQuestionPosition + "/10");
        if (quizType.equals("Openness")){
            tvQuestion.setText(opennessPersonalityQuizArrayList.get(currentArrayPosition).getQuestion());
        } else if (quizType.equals("Conscientiousness")) {
            tvQuestion.setText(conscientiousnessPersonalityQuizArrayList.get(currentArrayPosition).getQuestion());
        } else if (quizType.equals("Extraversion")) {
            tvQuestion.setText(extraversionPersonalityQuizArrayList.get(currentArrayPosition).getQuestion());
        } else if (quizType.equals("Agreeableness")) {
            tvQuestion.setText(agreeablenessPersonalityQuizArrayList.get(currentArrayPosition).getQuestion());
        } else if (quizType.equals("Neuroticism")) {
            tvQuestion.setText(neuroticismPersonalityQuizArrayList.get(currentArrayPosition).getQuestion());
        }

    }

    //populate array list with data
    private void getOpennessQuizQuestions(ArrayList<PersonalityQuiz> opennessPersonalityQuizArrayList) {
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I'm interested in learning about the history and politics of other countries."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I would spend my time reading a book of poetry."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I enjoy looking at maps of different places."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I would enjoy creating a work of art, such as a novel, a song, or a painting."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I like people who have unconventional views."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("If I had the opportunity, I would like to attend a classical music concert."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I wouldn't be very bored by a book about the history of science and technology. "));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("People have often told me that I have a good imagination."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("I think of myself as a somewhat eccentric person."));
        opennessPersonalityQuizArrayList.add(new PersonalityQuiz("Sometimes I like to just watch the wind as it blows through the trees."));
    }

    //populate array list with data
    private void getConscientiousnessQuizQuestions(ArrayList<PersonalityQuiz> conscientiousnessPersonalityQuizArrayList) {
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I clean my office or home quite frequently."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("When working, I often set ambitious goals for myself."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I often check my work over repeatedly to find any mistakes."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I plan ahead and organize things, to avoid scrambling at the last minute."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I often push myself very hard when trying to achieve a goal."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("When working on something, I pay attention to small details."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I always try to be accurate in my work, even at the expense of time."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I don’t allow my impulses to govern my behavior."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("People often call me a perfectionist."));
        conscientiousnessPersonalityQuizArrayList.add(new PersonalityQuiz("I prefer to stick to a plan, rather than do whatever comes to mind."));
    }

    //populate array list with data
    private void getExtraversionQuizQuestions(ArrayList<PersonalityQuiz> extraversionPersonalityQuizArrayList) {
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("I feel reasonably satisfied with myself overall."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("I am energetic nearly all the time."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("I think that most people like some aspects of my personality."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("In social situations, I'm usually the one who makes the first move."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("I enjoy having lots of people around to talk with."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("On most days, I feel cheerful and optimistic."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("When I'm in a group of people, I'm often the one who speaks on behalf of the group."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("I prefer jobs that involve active social interaction to those that involve working alone."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("The first thing that I always do in a new place is to make friends."));
        extraversionPersonalityQuizArrayList.add(new PersonalityQuiz("Less people are more upbeat and dynamic than I generally am."));
    }

    //populate array list with data
    private void getAgreeablenessQuizQuestions(ArrayList<PersonalityQuiz> agreeablenessPersonalityQuizArrayList) {
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("I rarely hold a grudge, even against people who have badly wronged me."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("People think of me as someone who is patient."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("My attitude toward people who have treated me badly is \"forgive and forget\"."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("I generally accept people’s faults without complaining about them."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("I am usually quite flexible in my opinions when people disagree with me."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("I rarely feel anger, even when people treat me quite badly."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("I tend to be lenient in judging other people."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("Most people tend to get angry more quickly than I do."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("I find it easy to fully forgive someone who has done something mean to me."));
        agreeablenessPersonalityQuizArrayList.add(new PersonalityQuiz("Even when people make a lot of mistakes, I rarely say anything negative."));
    }

    //populate array list with data
    private void getNeuroticismQuizQuestions(ArrayList<PersonalityQuiz> neuroticismPersonalityQuizArrayList) {
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("I would feel afraid if I had to travel in bad weather conditions."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("I sometimes can't help worrying about little things."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("When I suffer from a painful experience, I need someone to make me feel comfortable."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("I worry a lot more than most people do."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("When someone I know well is unhappy, I can almost feel that person's pain myself."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("When it comes to physical danger, I am very fearful."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("Whenever I feel worried about something, I want to share my concern with another person."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("I feel strong emotions when someone close to me is going away for a long time."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("In an emergency I would feel like panicking."));
        neuroticismPersonalityQuizArrayList.add(new PersonalityQuiz("I get very anxious when waiting to hear about an important decision."));
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
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PersonalityQuizActivity.this);
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
                Intent intent = new Intent(PersonalityQuizActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnPersonalityQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuizActivity.this, PersonalityHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnMockAssessments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuizActivity.this, MockAssessHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnJobSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuizActivity.this, JobHomeActivity.class);
                startActivity(intent);
            }
        });

        //When button is clicked, it rediverts users to desired destination
        btnResourceVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalityQuizActivity.this, ResourceHomeActivity.class);
                startActivity(intent);
            }
        });

        //Cancels bottom sheet dialog when user clicks another part of the screen
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
}