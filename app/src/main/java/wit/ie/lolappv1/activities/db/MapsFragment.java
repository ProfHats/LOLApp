package wit.ie.lolappv1.activities.db;


import android.content.IntentSender;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import wit.ie.lolappv1.R;
import wit.ie.lolappv1.activities.Map;
import wit.ie.lolappv1.models.Server;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends MapFragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener,
        LocationListener
{
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private LocationRequest mLocationRequest;
    private List<Server> serverList;

    private long UPDATE_INTERVAL = 30000; /* 30 secs */
    private long FASTEST_INTERVAL = 1000; /* 5 secs */

    /** Define a request code to send to Google Play services This code is
     * returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private final int[] MAP_TYPES = {
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE
    };

    private int curMapTypeIndex = 1;

    public MapsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance() {
        MapsFragment fragment = new MapsFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

       // TextView titleBar = (TextView) getActivity().findViewById(R.id.recentAddedBarTextView);
       // titleBar.setText("Coffee Map");

        mGoogleApiClient = new GoogleApiClient.Builder( getActivity() )
                .addConnectionCallbacks( this )
                .addOnConnectionFailedListener( this )
                .addApi( LocationServices.API )
                .build();

        initListeners();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(Bundle dataBundle)
    {
// Display the connection status
        try {
       //     mCurrentLocation = LocationServices.FusedLocationApi .getLastLocation(mGoogleApiClient);
        }
        catch(SecurityException se) {
            Toast.makeText(getActivity(),"Check Your Permissions",Toast.LENGTH_SHORT).show();
        }
        if (mCurrentLocation != null) {
            Toast.makeText(getActivity(), "GPS location was found!", Toast.LENGTH_SHORT).show();
//LatLng latLng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
        }
        else {
            Toast.makeText(getActivity(), "Current location was null, Setting Default Values!", Toast.LENGTH_SHORT).show();
            mCurrentLocation = new Location("Waterford City Default");
            mCurrentLocation.setLatitude(52.2462);
            mCurrentLocation.setLongitude(-7.1402);
        }

        initCamera(mCurrentLocation);
    }

    @Override
    public void onConnectionSuspended(int i) {
        if (i == CAUSE_SERVICE_DISCONNECTED) {
            Toast.makeText(getActivity(), "Disconnected. Please re-connect.", Toast.LENGTH_SHORT).show();
        }
        else if (i == CAUSE_NETWORK_LOST) {
            Toast.makeText(getActivity(), "Network lost. Please re-connect.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

/*
        if (connectionResult.hasResolution()) {
            try {
// Start an Activity that tries to resolve the error connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
            }
            catch (IntentSender.SendIntentException e) {
// Log the error e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Sorry. Location services not available to you", Toast.LENGTH_LONG).show();
        }
        */
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
    private void initListeners() {
     /*
        getMap().setOnMarkerClickListener(this);
        getMap().setOnMapLongClickListener(this);
        getMap().setOnInfoWindowClickListener(this);
        getMap().setOnMapClickListener(this);
   */
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
    }

    private void initCamera( Location location ) {
        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( location.getLatitude(), location.getLongitude() ) )
                .zoom( 14f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

      /*
        getMap().setMapType(MAP_TYPES[curMapTypeIndex]);
        getMap().setMyLocationEnabled(true);
        getMap().getUiSettings().setMapToolbarEnabled(true);
        getMap().getUiSettings().setCompassEnabled(true);
        getMap().getUiSettings().setMyLocationButtonEnabled(true);
        getMap().getUiSettings().setAllGesturesEnabled(true);
        getMap().setTrafficEnabled(true);
        getMap().setBuildingsEnabled(true);
        getMap().getUiSettings().setZoomControlsEnabled(true);
        getMap().animateCamera(CameraUpdateFactory.newCameraPosition(position), null);
        */
    }
}
