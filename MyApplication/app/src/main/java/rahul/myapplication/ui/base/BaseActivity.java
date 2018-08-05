package rahul.myapplication.ui.base;


import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import rahul.myapplication.R;
import rahul.myapplication.ui.utils.NetworkUtils;
import rahul.myapplication.ui.utils.UiUtils;


public abstract class BaseActivity extends AppCompatActivity implements MvpView,BaseFragment.Callbacks {

    private AlertDialog progressDialog;
    private PermissionCallback callback;

    private static final int PERMISSION_CALLBACK_CONSTANT = 102;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showProgressBar() {
        hideProgressBar();
        progressDialog= new UiUtils().showProgressBar(this);
        progressDialog.show();
    }


    @Override
    public void hideProgressBar() {
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.cancel();
        }
    }

    @Override
    public void showToast(String message,int length) {
        if (message != null) {
            Toast.makeText(this, message, length).show();
    }
    }

    @Override
    public void showToast(int resId,int length) {
        showToast(getString(resId),length);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }


    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }


    public boolean checkForPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(this, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    public void requestForPermission(String permission,String dialogText,PermissionCallback context) {
        this.callback=context;
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            showRequestDialog(permission,dialogText);
            Log.e("status","show rationale");
        } else {
            Log.e("status","requested permission");
            ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_CALLBACK_CONSTANT);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                callback.onPermissionDenied(permission);
            } else {
                if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
                    callback.onPermissionGranted(permission);
                } else {
                    callback.onPermanentPermissionDenied(permission);
                }
            }
        }
    }

    public void showAlwaysDeniedDialog(String dialogText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DefaultAlertDialogTheme);
        builder.setTitle(getString(R.string.permission_needed))
                .setMessage(dialogText)
                .setPositiveButton(getString(R.string.please_wait), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                callback.onSettingsSelected();
            }
        }).setNegativeButton(getString(R.string.not_now), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();

        builder.show();
    }


    public void showRequestDialog(String permission,String dialogText) {
        final String permissions=permission;
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DefaultAlertDialogTheme);
        builder.setTitle(getString(R.string.permission_needed))
        .setMessage(dialogText)
        .setPositiveButton(getString(R.string.grant), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permissions}, PERMISSION_CALLBACK_CONSTANT);
            }
        }).setNegativeButton(getString(R.string.deny), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();

        builder.show();
    }


    public void setUpToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
