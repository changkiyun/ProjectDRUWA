package com.example.projectdruwa;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//MessageList Fragment
public class MessageList extends Fragment {
    Context context;
    RecyclerView messageList;
    MatchListAdapter adapter;
    OnTapItemSelectedListener listener;

    //매너평가 다이얼로그 변수
    Dialog manner_dlg;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.message_list, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        messageList = rootView.findViewById(R.id.messageListRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        messageList.setLayoutManager(layoutManager);

        //RecyclerView에 아이템 추가
        adapter = new MatchListAdapter();
        adapter.addItem(new MatchingItem("매칭1", "5월 18일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭3", "5월 18일", "서대문구 연희동"));

        messageList.setAdapter(adapter);

        //매칭리스트 리스너
        adapter.setOnItemClickListener(new OnTapItemSelectedListener() {
            @Override
            public void onItemClick(MatchListAdapter.ViewHolder holder, View view, int position) {
                MatchingItem item = adapter.getItem(position);

                //매너평가 다이얼로그 출력
                manner_dlg = new Dialog(getContext());
                manner_dlg.setContentView(R.layout.manner_score);
                showdlg();
            }
        });

    }

    //매너평가 다이얼로그 메소드
    public void showdlg() {
        manner_dlg.show();

        //취소버튼 리스너
        Button noBtn = manner_dlg.findViewById(R.id.manner_cancel);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                manner_dlg.dismiss(); // 다이얼로그 닫기
            }
        });
        
        //확인버튼 리스너
        manner_dlg.findViewById(R.id.manner_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "매너평가완료", Toast.LENGTH_SHORT).show();
                manner_dlg.dismiss(); // 다이얼로그 닫기
            }
        });
    }
}