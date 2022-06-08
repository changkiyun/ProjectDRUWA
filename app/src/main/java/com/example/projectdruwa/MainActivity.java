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

    // Fragment 변수
    MatchingList matchingList;
    MessageList messageList;
    UserInformation userInformation;
    MatchHome matchHome;

    //네비게이션바 변수
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment 전환 코드
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
                    case R.id.matchTab: //매칭리스트 탭을 호출
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, matchHome)
                                .commit();
                        return true;

                    case R.id.msgTab: //참여리스트(채팅) 탭을 호출
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, messageList)
                                .commit();
                        return true;

                    case R.id.feedbackTab: //피드백 탭을 호출
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation)
                                //TODO: 피드백 레이아웃 미구현으로인해 임시로 내 정보탭을 연결함
                                .commit();
                        return true;

                    case R.id.infoTab: //내 정보 탭을 호출
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation)
                                .commit();
                        return true;
                }
                return false;
            }
        });
    }

    //Fragment 전환 코드
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.matchTab);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.msgTab);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.feedbackTab);
        } else if (position == 3) {
            bottomNavigation.setSelectedItemId(R.id.infoTab);
        }
    }

    //매칭홈 리스트출력 버튼 클릭 이벤트
    public void change_match(int i){
        if (i == 0) //매칭 홈화면 복귀
            getSupportFragmentManager().beginTransaction().replace(R.id.container, matchHome).commit();
        else if (i == 1) //일반 매칭화면 전환
            getSupportFragmentManager().beginTransaction().replace(R.id.container, matchingList).commit();
        else if (i == 2) { // 강사 매칭 화면 전환
            //TODO: 강사매칭 레이아웃 구현 필요
        }
    }
}