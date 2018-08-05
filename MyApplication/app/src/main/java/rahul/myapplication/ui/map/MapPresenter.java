package rahul.myapplication.ui.map;


import rahul.myapplication.ui.base.BasePresenter;

public class MapPresenter<V extends MapMvpView> extends BasePresenter<V> implements MapMvpPresenter<V> {

    @Override
    public void onBuyBtnClicked(String orderStandard, String orderType, int pairId, double price, int userId,double volume) {

    }


}
