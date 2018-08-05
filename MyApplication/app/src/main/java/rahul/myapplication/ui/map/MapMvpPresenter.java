package rahul.myapplication.ui.map;


import rahul.myapplication.ui.base.MvpPresenter;

public interface MapMvpPresenter<V extends MapMvpView> extends MvpPresenter<V> {

    void onBuyBtnClicked(String orderStandard, String orderType, int pairId, double price, int userId, double volume);
}
