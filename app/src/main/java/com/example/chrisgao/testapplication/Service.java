package com.example.chrisgao.testapplication;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.UUID;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ChrisGao on 2015/10/30.
 */
public class Service {
    private ObservableDetailViewModel broadCaster;

    public Service(ObservableDetailViewModel broadCaster){
        this.broadCaster = broadCaster;
    }

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<DataInfo> list = (List<DataInfo>)msg.obj;
            DataInfo res=list.get(0);
            ArrayList<DetailInfo> detailInfos=broadCaster.getDetailViewModel().getDetailInfos();
            Boolean flag=false;
            for (DetailInfo detailInfo : detailInfos) {
                if (detailInfo.getName().equals(res.getName())){
                    detailInfo.setQty(detailInfo.getQty()+res.getQty());
                    flag=true;
                }
            }
            if (!flag){
                DetailInfo detailInfo=new DetailInfo(UUID.randomUUID().toString(),res.getName(),res.getQty());
                detailInfos.add(detailInfo);
            }

            broadCaster.setDetailInfoList(detailInfos);
            broadCaster.setVerifiedQty(broadCaster.getDetailViewModel().getVerifiedQty() + 1, true);
        }
    };


    public void scan(String code){
        DetailViewModel vm= broadCaster.getDetailViewModel();
        broadCaster.setScanQty(vm.getScanQty() + 1, true);
        verifyDetail(code);
    }


    public void verifyDetail(final String code){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                List<DataInfo> list = new ArrayList<DataInfo>();
                try {
                    //result=GetHttpResponse("http://192.168.0.222:3000/order");
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.0.222:3000/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    OrderService service = retrofit.create(OrderService.class);
                    list = service.getOrder(code).execute().body();
                    Log.i("Service: verifyDetail", code);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message msg = Message.obtain();
                msg.obj = list;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(msg);
            }
        };
        new Thread(runnable).start();
    }


    public interface OrderService {
        @GET("{code}")
        Call<List<DataInfo>> getOrder(@Path("code") String code);
    }
}
