package rahul.myapplication.ui.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import rahul.myapplication.R;
import rahul.myapplication.ui.base.BaseActivity;


public class HomeActivity extends BaseActivity implements HomeMvpView {

    HomeMvpPresenter<HomeMvpView> presenter;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new HomePresenter<>();
        presenter.onAttach(HomeActivity.this);
        setupViewPager();
    }


    @Override
    public void openSignUpActivity() {

    }


    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }


    public void setupViewPager() {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
