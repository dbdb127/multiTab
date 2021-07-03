package com.example.myapplication_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.myapplication_1.databinding.ActivityMapsBinding
import com.google.android.gms.location.*
import com.google.android.gms.location.R
import com.google.android.gms.maps.model.CameraPosition
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED as PERMISSION_GRANTED1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ThirdFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters

    val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    val PERM_FLAG = 99

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        if (isPermitted()){
            startProcess()
        }
        else {
            ActivityCompat.requestPermissions(context!! as Activity, permissions, PERM_FLAG)
        }

        binding = ActivityMapsBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        return binding.root
//        return (binding.root)
        return inflater.inflate(com.example.myapplication_1.R.layout.fragment_third, container, false)
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
//      val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val mapFragment = childFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
//        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        // GET MAP ASYNC, 안드로이드에게 위치 정보 요청
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        if (googleMap != null) {
            mMap = googleMap
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        setupdateLocationListener()
    }
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    fun setupdateLocationListener(){
        val locationRequest = LocationRequest.create()
        locationRequest.run{
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = 1000
        }

        locationCallback = object : LocationCallback () {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.let{
                    for ((i, location) in it.locations.withIndex()){
                        Log.d("location", "$i ${location.latitude}, ${location.longitude}")
                        setLastLocation(location)
                    }

                }

            }
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
    }

    fun setLastLocation(location: Location){
        val myLocation = LatLng(location.latitude, location.longitude)
        val marker = MarkerOptions()
            .position(myLocation)
            .title("You are here!")
        val cameraOption = CameraPosition.Builder()
            .target(myLocation)
            .zoom(15.0f)
            .build()
        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)

        mMap.clear()

        mMap.addMarker(marker)
        mMap.moveCamera(camera)
    }

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