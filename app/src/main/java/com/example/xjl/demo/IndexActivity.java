package com.example.xjl.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xjl on 2017/10/19.
 */

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mtitle,text_message,text_friends,text_trends;
    private List<TextView> TvList=new ArrayList<TextView>();
    private FrameLayout frameLayout;
    private ImageView userimage;
    private FragmentManager manager;
    private FragmentTransaction transantion;
    private List<FirstFragment> fragmentlist=new ArrayList<FirstFragment>();
    private FirstFragment f1,f2,f3;
    private String fragmentText[]=new String[]{
      "fristFragment","secondFragment","thirdFragment"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initDatas();
        initView();
        initEvent();
        upload();
    }

    private void initDatas() {
        if(f1==null){
            Bundle bundle = new Bundle();
            bundle.putString(FirstFragment.FRAGTEXT, fragmentText[0]);
            f1=new FirstFragment();
            f1.setArguments(bundle);
            fragmentlist.add(f1);
        }
        if(f2==null){
            Bundle bundle = new Bundle();
            bundle.putString(FirstFragment.FRAGTEXT, fragmentText[1]);
            f2=new FirstFragment();
            f2.setArguments(bundle);
            fragmentlist.add(f2);
        }
        if(f3==null){
            Bundle bundle = new Bundle();
            bundle.putString(FirstFragment.FRAGTEXT, fragmentText[2]);
            f3=new FirstFragment();
            f3.setArguments(bundle);
            fragmentlist.add(f3);
        }
    }

    private void upload() {
        //绝对路径是主页上的文件或目录在硬盘上真正的路径，相对路径是相对于当前文件的路径
//        File userimg=new File();
    }

    private void initView() {
        text_friends=(TextView)findViewById(R.id.text_friends);
        text_trends=(TextView)findViewById(R.id.text_trends);
        userimage=(ImageView)findViewById(R.id.userimage);
        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);
        text_message=(TextView)findViewById(R.id.text_message);
        mtitle=(TextView)findViewById(R.id.mtitle);
        TvList.add(mtitle);
        TvList.add(text_friends);
        TvList.add(text_trends);
        TvList.add(text_message);

        manager=getSupportFragmentManager();
        transantion=manager.beginTransaction();
        for(int i=0;i<fragmentlist.size();i++){
            String tag=i+"";
            transantion.add(frameLayout.getId(),fragmentlist.get(i),tag);
        }
        for(int i=1;i<fragmentlist.size();i++){
            transantion.hide(fragmentlist.get(i));
        }
        transantion.commit();
        selected(R.id.text_message);
    }

    private void initEvent() {
        text_message.setOnClickListener(this);
        text_friends.setOnClickListener(this);
        text_trends.setOnClickListener(this);
        userimage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        transantion=getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.text_message:
                selected(R.id.text_message);
                hideAllFragment(transantion);
                transantion.show(fragmentlist.get(0));
                break;
            case R.id.text_friends:
                selected(R.id.text_friends);
                hideAllFragment(transantion);
                transantion.show(fragmentlist.get(1));
                break;
            case R.id.text_trends:
                hideAllFragment(transantion);
                selected(R.id.text_trends);
                transantion.show(fragmentlist.get(2));
                break;
            case R.id.userimage:
                break;
        }
        transantion.commit();
    }

    public void hideAllFragment(FragmentTransaction transaction){
       for(int i=0;i<fragmentlist.size();i++){
           String tag=i+"";
           transaction.hide(getSupportFragmentManager().findFragmentByTag(tag));
       }
    }
    public void selected(int Id){
       if(Id==R.id.text_message){
           text_message.setSelected(true);
           text_friends.setSelected(false);
           text_trends.setSelected(false);
           mtitle.setText("消息");
       }else if(Id==R.id.text_friends){
           text_message.setSelected(false);
           text_friends.setSelected(true);
           text_trends.setSelected(false);
           mtitle.setText("联系人");
       }else if(Id==R.id.text_trends){
           text_message.setSelected(false);
           text_friends.setSelected(false);
           text_trends.setSelected(true);
           mtitle.setText("动态");
       }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle=new Bundle();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
