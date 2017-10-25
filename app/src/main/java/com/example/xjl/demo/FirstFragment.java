package com.example.xjl.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by xjl on 2017/10/19.
 */

public class FirstFragment extends Fragment {
    private String FragmentText="test";
    public static final String FRAGTEXT="FragmentText";
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_fragment,container,false);
        tv=view.findViewById(R.id.text_fragment);
        if(getArguments()!=null){
            FragmentText=getArguments().getString(FRAGTEXT);
            tv.setText(FragmentText);
        }else{
            tv.setText("error");
        }
        return view;
    }
}
