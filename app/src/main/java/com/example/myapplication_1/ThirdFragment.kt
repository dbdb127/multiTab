package com.example.myapplication_1

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication_1.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED as PERMISSION_GRANTED1
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.widget.Toast




class ThirdFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters

    private val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val PERM_FLAG = 99

    private lateinit var mMap: GoogleMap
//    private lateinit var binding: ActivityMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if (isPermitted()){
            startProcess()
        }
        else {
            ActivityCompat.requestPermissions(requireContext() as Activity, permissions, PERM_FLAG)
        }

//        binding = ActivityMapsBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    fun isPermitted() : Boolean {
        for(perm in permissions){
            if(ContextCompat.checkSelfPermission(requireContext(), perm) != PERMISSION_GRANTED){
                return false
            }
        }

        return true
    }

    fun startProcess(){
        (activity?.supportFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment?)?.let {
            it.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap
        }
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("here"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
//        setupdateLocationListener()
    }
//    lateinit var fusedLocationClient: FusedLocationProviderClient
//    lateinit var locationCallback: LocationCallback

//    @SuppressLint("MissingPermission")
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            PERM_FLAG -> {
                var check = true
                for (grant in grantResults){
                    if(grant != PERMISSION_GRANTED){
                        check = false
                        break
                    }
                }
                if (check) {
                    startProcess()
                }
                else{
                    activity?.finish()
                }
            }
        }
    }
}



