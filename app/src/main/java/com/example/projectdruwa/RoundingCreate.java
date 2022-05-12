package com.example.projectdruwa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class RoundingCreate extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rounding_create);
        setTitle("라운딩 매칭");

        //todo 2 : value값에 spinner 구현 추가
        //todo 1 : finish로 끝낼것이 아니라 위에서 선택한 정보들을 DB로넘겨주며 방을 생성하는 기능 추가 필요
        Button createRoom = findViewById(R.id.createRoom);
        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
