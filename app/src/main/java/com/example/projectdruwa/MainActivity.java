package com.example.projectdruwa;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // matchingList, userInformation Fragment 변수 YCK
    MatchingList matchingList;
    MessageList messageList;
    UserInformation userInformation;
    MatchHome matchHome;

    //네비게이션바 변수 YCK
    BottomNavigationView bottomNavigation;

    //CJW : 상단 필터링 이미지 버튼 용 다이얼로그 변수
    Dialog filterDialog;

    //매너평가 화면출력
    ImageButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment 전환 코드 YCK
        userInformation = new UserInformation();
        messageList = new MessageList();
        matchingList = new MatchingList();
        matchHome = new MatchHome();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, matchHome).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "매칭리스트 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, matchHome).commit();

                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "참여리스트 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, messageList).commit();

                        return true;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "유저정보 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation).commit();

                        return true;

                    case R.id.tab4:
                        Toast.makeText(getApplicationContext(), "피드백 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation).commit();

                        return true;
                }

                return false;
            }
        });
    }
    //aa
    //Fragment 전환 코드 YCK
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab4);
        } else if (position == 3) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
    }

    //매칭홈 리스트출력 버튼 클릭 이벤트
    public void change_match(int i){
        if (i == 0) //매칭 홈화면 복귀
            getSupportFragmentManager().beginTransaction().replace(R.id.container, matchHome).commit();
        if (i == 1) //일반 매칭화면 전환
            getSupportFragmentManager().beginTransaction().replace(R.id.container, matchingList).commit();
        if (i == 2) { // 강사 매칭 화면 전환
            //TODO: 강사매칭 화면 연결 필요
        }
    }
}
