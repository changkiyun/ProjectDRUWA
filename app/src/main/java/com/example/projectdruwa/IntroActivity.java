package com.example.projectdruwa;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

//인트로를 실행시키는 클래스
public class IntroActivity extends AppCompatActivity {
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_activity);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
            },2000 //2초후 메인액티비티로 넘어감 (시간설정)
        );
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}