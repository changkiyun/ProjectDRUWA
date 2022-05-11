package com.example.projectdruwa;

import android.os.Bundle;
import android.view.MenuItem;
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
                        Toast.makeText(getApplicationContext(), "매칭리스트 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, matchingList).commit();

                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "참여리스트 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, matchingList).commit();

                        return true;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "유저정보 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation).commit();

                        return true;
                }

                return false;
            }
        });

    }
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
