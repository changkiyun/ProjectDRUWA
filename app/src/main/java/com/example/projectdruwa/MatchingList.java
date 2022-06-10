package com.example.projectdruwa;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//MatchingList Fragment
public class MatchingList extends Fragment {

    Context context;
    View search;
    ImageButton backbtn;
    RecyclerView matchList;
    OnTapItemSelectedListener listener;
    MatchListAdapter adapter;
    MainActivity mainactivity;

    //필터링 다이얼로그 변수
    Dialog filterDialog;

    public void onAttach(Context context){
        super.onAttach(context);
        this.context=context;
        mainactivity=(MainActivity)getActivity();

        if(context instanceof OnTapItemSelectedListener){
            listener = (OnTapItemSelectedListener) context;
        }
    }

    public void onDetach(){
        super.onDetach();
        mainactivity=null;

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.matching_list, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        matchList = rootView.findViewById(R.id.matchListRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        matchList.setLayoutManager(layoutManager);

        //RecyclerView에 아이템을 추가
        adapter = new MatchListAdapter();
        adapter.addItem(new MatchingItem("매칭1", "3월 18일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭2", "3월 21일", "서대문구 남가좌동"));
        adapter.addItem(new MatchingItem("매칭3", "4월 4일", "서대문구 연희동"));
        adapter.addItem(new MatchingItem("매칭4", "4월 16일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭5", "5월 18일", "서대문구 남가좌동"));
        adapter.addItem(new MatchingItem("매칭6", "5월 19일", "서대문구 연희동"));
        adapter.addItem(new MatchingItem("매칭7", "5월 26일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭8", "6월 1일", "서대문구 남가좌동"));
        adapter.addItem(new MatchingItem("매칭9", "6월 10일", "서대문구 연희동"));

        matchList.setAdapter(adapter);

        //매칭리스트 리스너
        adapter.setOnItemClickListener(new OnTapItemSelectedListener() {
            @Override
            public void onItemClick(MatchListAdapter.ViewHolder holder, View view, int position) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());

                search = View.inflate(getContext(), R.layout.search_filtering_dialog,null);
                dlg.setView(search);
                dlg.setNegativeButton("닫기", null);
                dlg.setPositiveButton("참여", null);
                dlg.show();

                MatchingItem item = adapter.getItem(position);
                Toast.makeText(getContext(), position + "번째 매칭 선택됨", Toast.LENGTH_SHORT).show();
            }
        });

        //상단 햄버거 버튼 클릭시 필터링 다이얼로그 출력
        filterDialog= new Dialog(getContext());
        filterDialog.setContentView(R.layout.filtering);

        ImageButton filtering = rootView.findViewById(R.id.filterBtn);
        filtering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialogShow(); // 다이얼로그 실행
                //다이얼로그 주변 반투명 없애기, 상단 위치조정
                filterDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                filterDialog.getWindow().setGravity(Gravity.TOP);
            }
        });

        //레이아웃 하단에 FloatingActionButton을 클릭 시 라운딩 등록 화면 출럭
        //TODO : 적용버튼 누르면 DB에 스피너에서 설정한 정보대로 저장하고, 그것을 토대로 검색
        FloatingActionButton roundingCreate;
        roundingCreate = rootView.findViewById(R.id.roundingCreateButton);
        roundingCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend = new Intent(getContext(),RoundingCreate.class);
                startActivity(intend);
            }
        });

        //뒤로가기 버튼 클릭시 매칭 선택화면으로 돌아가는 기능 코드
        backbtn = rootView.findViewById(R.id.backBtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainactivity.change_match(0);
            }
        });
    }

    //필터링 다이얼로그에서 취소,적용을 구현할 메소드
    public void filterDialogShow(){
        // 필터링 취소
        filterDialog.show();
        Button cancelFiltering = filterDialog.findViewById(R.id.cancelFiltering);
        cancelFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.dismiss();
            }
        });
        // 필터링 적용
        Button applyFiltering = filterDialog.findViewById(R.id.applyFiltering);
        applyFiltering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialog.dismiss();
            }
        });
    }
}