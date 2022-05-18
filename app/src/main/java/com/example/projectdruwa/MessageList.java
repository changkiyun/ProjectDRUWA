package com.example.projectdruwa;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//MatchingList Fragment 처리 YCK
public class MessageList extends Fragment {
    RecyclerView messageList;
    MatchListAdapter adapter;

    Context context;
    OnTapItemSelectedListener listener;

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

        adapter = new MatchListAdapter();

        adapter.addItem(new MatchingItem("매칭1", "2022년 5월 18일", "서대문구 홍은동"));
        adapter.addItem(new MatchingItem("매칭3", "2022년 5월 18일", "서대문구 연희동"));


        messageList.setAdapter(adapter);

        //매칭리스트 리스너
        adapter.setOnItemClickListener(new OnTapItemSelectedListener() {
            @Override
            public void onItemClick(MatchListAdapter.ViewHolder holder, View view, int position) {
                MatchingItem item = adapter.getItem(position);
                Toast.makeText(getContext(), position + "번째 매칭중인 대화방 선택됨", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
