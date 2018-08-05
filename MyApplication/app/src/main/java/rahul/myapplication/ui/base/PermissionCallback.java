package rahul.myapplication.ui.base;



public interface PermissionCallback {

    void onPermissionGranted(String permission);

    void onPermissionDenied(String permission);

    void onPermanentPermissionDenied(String permission);

    void onSettingsSelected();
}
