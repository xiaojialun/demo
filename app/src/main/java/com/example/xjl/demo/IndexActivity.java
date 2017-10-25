package com.example.xjl.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjl on 2017/10/19.
 */

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mtitle,text_message,text_friends,text_trends;
    private FrameLayout frameLayout;
    private ImageView userimage;
    private FragmentTransaction transantion;
    private List<FirstFragment> fragmentlist=new ArrayList<FirstFragment>();
    private String fragmentText[]=new String[]{
      "fristFragment","secondFragment","thirdFragment"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);
        text_message=(TextView)findViewById(R.id.text_message);
        mtitle=(TextView)findViewById(R.id.mtitle);
        initView();
        initEvent();
    }

    private void initView() {
        transantion=getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragmentText.length;i++){
            FirstFragment f=new FirstFragment();
            Bundle bundle=new Bundle();
            bundle.putString(FirstFragment.FRAGTEXT,fragmentText[i]);
            f.setArguments(bundle);
            fragmentlist.add(f);
            transantion.add(R.id.fragment_container,f);
            transantion.hide(f);

        }
        transantion.commit();
        transantion.show(fragmentlist.get(0));
        text_message.setSelected(true);

    }

    private void initEvent() {

        text_friends=(TextView)findViewById(R.id.text_friends);
        text_trends=(TextView)findViewById(R.id.text_trends);
        userimage=(ImageView)findViewById(R.id.userimage);
        text_message.setSelected(true);

        text_message.setOnClickListener(this);
        text_friends.setOnClickListener(this);
        text_trends.setOnClickListener(this);
        userimage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transantion=getSupportFragmentManager().beginTransaction();
        transantion.commit();
        switch (view.getId()){
            case R.id.text_message:
                hideAllFragment(transantion);
                selected();
                transantion.show(fragmentlist.get(0));
                text_message.setSelected(true);
                mtitle.setText("消息");
                break;
            case R.id.text_friends:
                hideAllFragment(transantion);
                selected();
                transantion.show(fragmentlist.get(1));
                text_friends.setSelected(true);
                mtitle.setText("联系人");
                break;
            case R.id.text_trends:
                hideAllFragment(transantion);
                selected();
                transantion.show(fragmentlist.get(2));
                text_trends.setSelected(true);
                mtitle.setText("动态");
                break;
            case R.id.userimage:
                break;
        }
    }

    public void hideAllFragment(FragmentTransaction transaction){
       for(int i=0;i<fragmentlist.size();i++){
           transaction.hide(fragmentlist.get(i));
       }
    }
    public void selected(){
        text_message.setSelected(false);
        text_friends.setSelected(false);
        text_trends.setSelected(false);
    }


}
