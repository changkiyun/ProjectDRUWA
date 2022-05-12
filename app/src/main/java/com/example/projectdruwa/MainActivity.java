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

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener {
    private static final String TAG = "MainActivity";

    // matchingList, userInformation Fragment 변수 YCK
    MatchingList matchingList;
    UserInformation userInformation;

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
        matchingList = new MatchingList();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, matchingList).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "매칭리스트 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, matchingList).commit();

                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "참여리스트 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, matchingList).commit();

                        return true;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "유저정보 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation).commit();

                        return true;
                }

                return false;
            }
        });

        //CJW : 하단 + 버튼 클릭시 인텐드 넘겨서 rounding_create 열기
        Button roundingCreate;
        roundingCreate = findViewById(R.id.roundingCreateButton);
        roundingCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend = new Intent(MainActivity.this,RoundingCreate.class);
                startActivity(intend);
            }
        });

        //CJW : 지금 왠지 모르겠는데 프래그먼트 첫페이지에서 만든 이미지버튼에 링크하면 아예 오류나고안열림 버튼하나 더맹글어서 연결하니 제대로열림
        //CJW : 상단 전구 버튼 클릭시 다이얼로그 창 필터링열기
        filterDialog= new Dialog(MainActivity.this);
        filterDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        filterDialog.setContentView(R.layout.filtering);             // xml 레이아웃 파일과 연결

        Button filtering = findViewById(R.id.TestBut);
        filtering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialogShow(); // 다이얼로그 띄우기
            }
        });
    }
    /*CJW : 필터링 다이얼로그 안의 기능 구현하는 함수.
            일단 적용, 취소버튼 전부 다이얼로그를 종료하도록 연결하였음.*/
    //TODO CJW 1 : 적용버튼 누르면 DB에 스피너에서 설정한 정보대로 저장하고, 그것을 토대로 검색


    public void filterDialogShow(){
        // 취소
        filterDialog.show();
        Button cancelFiltering = filterDialog.findViewById(R.id.cancelFiltering);
        cancelFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.dismiss();
            }
        });
        // 적용
        Button applyFiltering = filterDialog.findViewById(R.id.applyFiltering);
        applyFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.dismiss();
            }
        });
    }
        //매너평가 화면 출력
        // 오류나서 주석처리 YCK
//       btn1 = (ImageButton) findViewById(R.id.button);

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//    }
    //Fragment 전환 코드 YCK
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
    }

}
