package com.charanajayworks.pubgguide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuestionsActivity extends AppCompatActivity {
    EditText questionsEditText;
    Button askButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_layout);

        questionsEditText = findViewById(R.id.questionEditText);
        askButton = findViewById(R.id.askButton);

        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "charanajayworks@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "PUBG Query");
                    intent.putExtra(Intent.EXTRA_TEXT, questionsEditText.getText().toString());
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }
            }
        });

    }
}
