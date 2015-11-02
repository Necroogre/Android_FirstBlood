package com.example.chrisgao.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    Presenter presenter;
    private ObservableDetailViewModel observableDetailViewModel;
    private Service svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = this.getIntent();
        String code = intent.getStringExtra("code");

        DetailViewModel detailViewModel = new DetailViewModel(code, 0, 0,  new ArrayList<DetailInfo>());

        Init(detailViewModel);

        ButterKnife.bind(this);


        scanCodeList.add("00011234");
        scanCodeList.add("00021234");
        scanCodeList.add("10011234");
        scanCodeList.add("10021234");
        scanCodeList.add("20011234");
        scanCodeList.add("20021234");

    }

    private void Init(DetailViewModel detailViewModel) {
        observableDetailViewModel = new ObservableDetailViewModel(detailViewModel);
        presenter = new Presenter(this, observableDetailViewModel);
        svc = new Service(observableDetailViewModel);
        observableDetailViewModel.commit(detailViewModel);
    }


    int i = 0;
    ArrayList<String> scanCodeList=new ArrayList<String>();

    @OnClick(R.id.scanButton)
    void Scan() {
        Log.i("info", "scan");
        int index=scanCodeList.size()-1;
        if (i>index){
            i=0;
        }
        String scanCode=scanCodeList.get(i);
        i++;

        svc.scan(scanCode);
    }

}
