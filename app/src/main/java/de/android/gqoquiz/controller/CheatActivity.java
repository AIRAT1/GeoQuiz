package de.android.gqoquiz.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.android.gqoquiz.BuildConfig;
import de.android.gqoquiz.R;

public class CheatActivity extends AppCompatActivity {
    public static final String EXTRA_ANSWER_IS_TRUE = BuildConfig.APPLICATION_ID + ".answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = BuildConfig.APPLICATION_ID + ".answer_shown";
    public static final String IS_CHEATER = "is_cheater";
    private boolean answerIsTrue;
    private TextView answerTextView;
    private Button showAnswer;
    private boolean isCheater = false;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        answerTextView = (TextView)findViewById(R.id.answer_text_view);
        showAnswer = (Button)findViewById(R.id.show_answer_button);
        if (savedInstanceState != null) {
            isCheater = savedInstanceState.getBoolean(IS_CHEATER, false);
        }
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerIsTrue) {
                    answerTextView.setText(R.string.true_button);
                }else {
                    answerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                isCheater = true;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_CHEATER, isCheater);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
