package com.example.projectdruwa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//매칭리스트 홈 Fragment
public class MatchHome extends Fragment {

    //매칭 선택 버튼 변수
    Button matchbtn1;
    Button matchbtn2;

    //메인액티비티를 가져오기 위한 변수
    MainActivity mainactivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainactivity=(MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainactivity=null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Create Fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.matching_home, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView){
        matchbtn1 = rootView.findViewById(R.id.match_btn1);
        matchbtn2 = rootView.findViewById(R.id.match_btn2);

        //버튼 클릭시 각각 해당하는 Fragment를 불러오는 리스터 (1 : 일반 매칭, 2 : 강사 매칭)
        matchbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity.change_match(1);
            }
        });

        matchbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity.change_match(2);
            }
        });
    }
}