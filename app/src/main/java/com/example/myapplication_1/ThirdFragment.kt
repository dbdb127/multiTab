package com.example.myapplication_1

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class ThirdFragment : Fragment() {
    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val PERM_FLAG = 99

    private lateinit var mMap: GoogleMap
//    private lateinit var binding: ActivityMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_third, container, false)
        val supportMapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        supportMapFragment!!.getMapAsync { googleMap ->
            googleMap.setOnMapClickListener { latLng ->
                val markerOptions = MarkerOptions()
                markerOptions.position(latLng)
                googleMap.clear()
//                googleMap.addMarker()
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
                googleMap.addMarker(markerOptions)
                googleMap.addCircle(CircleOptions()
                    .center(latLng)
                    .radius(3000.0)
                    .strokeColor(Color.rgb(64,167,255))
                    .fillColor(Color.rgb(254,189,189))
                )
            }
        }
        return view
    }
}
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
//        setupdateLocationListener()
//    lateinit var fusedLocationClient: FusedLocationProviderClient
//    lateinit var locationCallback: LocationCallback

//    fun setupdateLocationListener(){
//        val locationRequest = LocationRequest.create()
//        locationRequest.run{
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//            interval = 1000
//        }
//
//        locationCallback = object : LocationCallback () {
//            override fun onLocationResult(locationResult: LocationResult?) {
//                locationResult?.let{
//                    for ((i, location) in it.locations.withIndex()){
//                        Log.d("location", "$i ${location.latitude}, ${location.longitude}")
//                        setLastLocation(location)
//                    }
//
//                }
//
//            }
//        }
//
//        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
//    }
//
//    fun setLastLocation(location: Location){
//        val myLocation = LatLng(location.latitude, location.longitude)
//        val marker = MarkerOptions()
//            .position(myLocation)
//            .title("You are here!")
//        val cameraOption = CameraPosition.Builder()
//            .target(myLocation)
//            .zoom(15.0f)
//            .build()
//        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
//
//        mMap.clear()
//
//        mMap.addMarker(marker)
//        mMap.moveCamera(camera)
//    }




