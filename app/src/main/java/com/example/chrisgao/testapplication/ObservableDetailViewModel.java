package com.example.chrisgao.testapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ChrisGao on 2015/10/30.
 */
public class ObservableDetailViewModel extends Observable {

    private DetailViewModel detailViewModel;

    public ObservableDetailViewModel(DetailViewModel detailViewModel) {
        this.detailViewModel = detailViewModel;
    }

    public DetailViewModel getDetailViewModel() {
        return detailViewModel;
    }

    public void setDetailViewModel(DetailViewModel detailViewModel) {
        this.detailViewModel = detailViewModel;
    }

    public void setScanQty(int scanQty, boolean isCommit) {
        detailViewModel.setScanQty(scanQty);
        if (isCommit) {
            commit(this.detailViewModel);
        }
    }

    public void setVerifiedQty(int verifiedQty, boolean isCommit) {
        detailViewModel.setVerifiedQty(verifiedQty);
        if (isCommit) {
            commit(this.detailViewModel);
        }
    }

    public void setDetailInfoList(ArrayList<DetailInfo> dtlList) {
        detailViewModel.setDetailInfos(dtlList);
        setChanged();
        ObservableMsg msg = new ObservableMsg("DetailInfo", detailViewModel.getDetailInfos());
        notifyObservers(msg);
    }

    public void commit(DetailViewModel obj) {
        setChanged();
        ObservableMsg msg = new ObservableMsg("VM", detailViewModel);
        notifyObservers(msg);
    }
}

