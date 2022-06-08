package com.example.projectdruwa;

import android.view.View;

//네비게이션바 클릭 리스너
public interface OnTapItemSelectedListener {
    public void onItemClick(MatchListAdapter.ViewHolder holder, View view, int position);
}