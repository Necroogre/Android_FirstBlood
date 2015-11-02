package com.example.chrisgao.testapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ChrisGao on 2015/10/29.
 */
public class DetailAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<DetailInfo> mData;

    public DetailAdapter(Context context, List<DetailInfo> detailInfoList) {
        this.mInflater = LayoutInflater.from(context);
        mData = detailInfoList;
    }

    public void setData(List<DetailInfo> detailInfoList) {
        this.mData = detailInfoList;
    }

    public int getCount() {
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

    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.listitem_detail, null);

            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        DetailInfo item = mData.get(position);
        holder.productView.setText(item.getName());
        holder.qtyView.setText(Integer.toString(item.getQty()));
        return view;
    }

    public synchronized void refreshAdapter(ArrayList<DetailInfo> items) {
        this.mData = items;
        notifyDataSetChanged();
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    private int selectItem = -1;

    static class ViewHolder {
        @Bind(R.id.listItem_detail_product)
        TextView productView;
        @Bind(R.id.listItem_detail_qty)
        TextView qtyView;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
