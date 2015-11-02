package com.example.chrisgao.testapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;

public class MainActivity extends AppCompatActivity {

    Button btn;
    MyAdapter adapter;
    ListView lv;
    private List<Header> mData=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        btn=(Button)findViewById(R.id.button);
        adapter = new MyAdapter(this);
        final List<String> dataList=new ArrayList<String>();
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText tx = (EditText) findViewById(R.id.editText);
                lv = (ListView) findViewById(R.id.listView);

                final Handler handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        List<Header> headerList = (List<Header>)msg.obj;
                        for (int i=0;i<headerList.size();i++){
                            mData.add(headerList.get(i));
                        }
                        lv.setAdapter(adapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Header info = (Header)(parent.getItemAtPosition(position));
                                Intent intent= new Intent(MainActivity.this,DetailActivity.class);
                                intent.putExtra("code",info.Code);
                                startActivity(intent);
                            }

                        });
                    }
                };

                Runnable runnable = new Runnable(){
                    @Override
                    public void run() {
                        List<Header> headerList=new ArrayList<Header>();
                        try {
                            //result=GetHttpResponse("http://192.168.0.222:3000/order");
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://192.168.0.222:3000/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            OrderService service = retrofit.create(OrderService.class);
                            headerList = service.getOrder().execute().body();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Message msg = Message.obtain();
                        msg.obj=headerList;
                        handler.sendMessage(msg);
                    }
                };
                new Thread(runnable).start();

            }
        });
    }

    public interface OrderService {
        @GET("order")
        Call<List<Header>> getOrder();
    }

    public class Header {
        public String id;
        public String Code;
    }


    public final class ViewHolder {
        public ImageView img;
        public TextView infoText;
        public Button viewBtn;
        public boolean visible;
        public Header header;
    }

    public class MyAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }
        public int getCount(){
            return mData.size();
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return mData.get(arg0);
        }
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }
        public View getView(final int position, View convertView, ViewGroup parent){
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listitem_main, null);
                holder.infoText = (TextView) convertView.findViewById(R.id.item_Info);
                holder.viewBtn = (Button)convertView.findViewById(R.id.item_Button);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.infoText.setText(mData.get(position).Code);
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    lv.setAdapter(adapter);
                }
            });
            holder.header = mData.get(position);
            if (position == selectItem) {
                convertView.setBackgroundColor(Color.RED);
            }
            else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }


            return convertView;
        }
        public  void setSelectItem(int selectItem) {
            this.selectItem = selectItem;
        }
        private int  selectItem=-1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
