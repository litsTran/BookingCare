package com.example.bookingcare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Nhikhoa extends Fragment {
    private Button btn1,btn2;
    private ImageButton imgbt1,imgbt2;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nhikhoa, container, false);
        btn1 = view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment bt1 = new DatlichI();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, bt1).commit();
            }
        });
        btn2 = view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment bt2 = new DatlichJ();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container,bt2).commit();
            }
        });
        imgbt1 = view.findViewById(R.id.imgbt1);
        imgbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment i1 = new BacsiI();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, i1).commit();
            }
        });
        imgbt2 = view.findViewById(R.id.imgbt2);
        imgbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment i2 = new BacsiJ();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.fragment_container, i2).commit();
            }
        });
        return view;
    }
}