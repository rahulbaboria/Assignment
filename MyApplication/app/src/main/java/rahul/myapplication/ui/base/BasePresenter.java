package rahul.myapplication.ui.base;



public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView=mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView=null;
    }

    public V getMvpView() {
        return mMvpView;
    }



}
