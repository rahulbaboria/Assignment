package rahul.myapplication.ui.spash;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import butterknife.ButterKnife;
import rahul.myapplication.R;
import rahul.myapplication.ui.base.BaseActivity;
import rahul.myapplication.ui.home.HomeActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {
    private SplashPresenter<SplashMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.SplashTheme);
        setContentView(R.layout.splash);
        ButterKnife.bind(this);  presenter = new SplashPresenter<>();
        presenter.onAttach(SplashActivity.this);
        openNextActivity();
    }


    @Override
    public void openNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    finish();
                    Intent intent=new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);

            }
        }, 1000);
    }


}
