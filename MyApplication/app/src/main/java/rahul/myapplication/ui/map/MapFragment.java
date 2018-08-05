package rahul.myapplication.ui.map;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rahul.myapplication.R;
import rahul.myapplication.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment implements MapMvpView,  OnMapReadyCallback {


    private Unbinder unbinder;
    private AppCompatActivity activity;
    private MapMvpPresenter<MapMvpView> presenter;
    private Marker delhi;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (AppCompatActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager!=null){
            FragmentManager fm = getChildFragmentManager();
            SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map,supportMapFragment ).commit();
            supportMapFragment.getMapAsync(this);
        }

    }

    @Override
    protected void setUp(View view) {
        presenter=new MapPresenter<>();
        presenter.onAttach(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = new LatLng(28.704059,77.102490);

        delhi = googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker()).draggable(true));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                delhi = marker;
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                delhi = marker;
            }
        });
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker1) {
                LinearLayout view=  (LinearLayout) activity.getLayoutInflater().inflate(R.layout.info_window, null);
                TextView textView = view.findViewById(R.id.tv_latlng);
                textView.setText(delhi.getPosition().toString());
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

    }
}
