package com.example.projectdruwa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//user_information Fragment 처리 YCK
public class UserInformation extends Fragment {

    Button btn_edit;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Create Fragment 창기
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user_information, container, false);

        initUI(rootView);

        return rootView;


    }

    private void initUI(ViewGroup rootView){


            btn_edit = rootView.findViewById(R.id.btn_edit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), ProfileEdit.class);
                    startActivity(intent);
                }
                });

        }
    }


