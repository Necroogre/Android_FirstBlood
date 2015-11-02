package com.example.chrisgao.testapplication;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ChrisGao on 2015/10/30.
 */
public class Presenter {

    @Bind(R.id.scanButton)
    public Button btn_LoadData;
    @Bind(R.id.listView2)
    public ListView listView;
    @Bind(R.id.codeText)
    public TextView codeText;
    @Bind(R.id.scanQtyText)
    public TextView scanQtyText;
    @Bind(R.id.progressBar)
    public ProgressBar progressBar;

    private Activity act;
    private DetailAdapter adapter;
    private ObservableDetailViewModel broadCaster;

    public Presenter(Activity act, ObservableDetailViewModel broadCaster) {
        this.act = act;
        this.broadCaster = broadCaster;
        ButterKnife.bind(this, act);

        adapter = new DetailAdapter(act, new ArrayList<DetailInfo>());
        listView.setAdapter(adapter);

        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object data) {
                Log.i("Presenter: on update", ((ObservableMsg) data).EventName);
                if (!(data instanceof ObservableMsg)) {
                    return;
                }
                ObservableMsg msg = (data instanceof ObservableMsg) ? (ObservableMsg) data : null;
                switch (msg.EventName) {
                    case "DetailInfo":
                        RenderList((ArrayList<DetailInfo>) (msg.Data));
                        break;
                    case "VM":
                        LightRender((DetailViewModel) (msg.Data));
                        break;
                    default:
                        break;
                }
            }
        };

        this.broadCaster.addObserver(observer);
    }

    private void RenderList(ArrayList<DetailInfo> data) {
        adapter.refreshAdapter(data);
    }
    int max = 10;
    public void LightRender(DetailViewModel vm) {
        codeText.setText(vm.getOrderCode());
        scanQtyText.setText(Integer.toString(vm.getScanQty()));

        if (max == 10) {
            progressBar.setMax(max);
        }
        if (vm.getScanQty() == (max / 2)) {
            max *= 2;
            progressBar.setMax(max);
        }
        Log.i("VerifiedQty", Integer.toString(vm.getVerifiedQty()));
        Log.i("ScanQty", Integer.toString(vm.getScanQty()));
        progressBar.setProgress(vm.getVerifiedQty());
        progressBar.setSecondaryProgress(vm.getScanQty());
    }
}
