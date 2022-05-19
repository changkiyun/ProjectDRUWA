package com.example.projectdruwa;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

//MatchingList Fragment 처리 YCK
public class MatchingList extends Fragment {
    RecyclerView matchList;
    MatchListAdapter adapter;
    View search;

    Context context;
    OnTapItemSelectedListener listener;

    Dialog filterDialog;

    public void onAttach(Context context){
        super.onAttach(context);

        this.context=context;

        if(context instanceof OnTapItemSelectedListener){
            listener = (OnTapItemSelectedListener) context;
        }
    }

    public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.maching_list, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        matchList = rootView.findViewById(R.id.matchListRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        matchList.setLayoutManager(layoutManager);

        adapter = new MatchListAdapter();

        adapter.addItem(new MatchingItem("매칭1", "2022년 5월 18일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭2", "2022년 5월 18일", "서대문구 남가좌동"));
        adapter.addItem(new MatchingItem("매칭3", "2022년 5월 18일", "서대문구 연희동"));
        adapter.addItem(new MatchingItem("매칭1", "2022년 5월 18일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭2", "2022년 5월 18일", "서대문구 남가좌동"));
        adapter.addItem(new MatchingItem("매칭3", "2022년 5월 18일", "서대문구 연희동"));
        adapter.addItem(new MatchingItem("매칭1", "2022년 5월 18일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭2", "2022년 5월 18일", "서대문구 남가좌동"));
        adapter.addItem(new MatchingItem("매칭3", "2022년 5월 18일", "서대문구 연희동"));

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

        //CJW : 상단 햄버거 버튼 클릭시 다이얼로그 창 필터링열기
        filterDialog= new Dialog(getContext());
        filterDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        filterDialog.setContentView(R.layout.filtering);             // xml 레이아웃 파일과 연결

        ImageButton filtering = rootView.findViewById(R.id.button2);
        filtering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterDialogShow(); // 다이얼로그 띄우기
            }
        });

        //CJW : 하단 + 버튼 클릭시 인텐드 넘겨서 rounding_create 열기
        /*CJW : 필터링 다이얼로그 안의 기능 구현하는 함수.
            일단 적용, 취소버튼 전부 다이얼로그를 종료하도록 연결하였음.*/
        //TODO CJW 1 : 적용버튼 누르면 DB에 스피너에서 설정한 정보대로 저장하고, 그것을 토대로 검색

        FloatingActionButton roundingCreate;
        roundingCreate = rootView.findViewById(R.id.roundingCreateButton);
        roundingCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend = new Intent(getContext(),RoundingCreate.class);
                startActivity(intend);
            }
        });

    }

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
}
