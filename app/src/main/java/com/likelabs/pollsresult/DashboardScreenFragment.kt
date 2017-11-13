package com.likelabs.pollsresult

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dashboard_screen.*
import java.util.*

class DashboardScreenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_dashboard_screen, container, false)
    }

    override fun onStart() {
        super.onStart()
        val location = getLocation()
        val gcd = Geocoder(activity, Locale.getDefault())
        val addresses = gcd.getFromLocation(location.latitude, location.longitude, 1)
        if (addresses.size > 0) {
            Utils.showLog("::> " + addresses[0].locality)
            val sb = StringBuilder()
            sb.append(getString(R.string.country_name))
            sb.append(addresses[0].countryName)
            sb.append("\n")
            sb.append(getString(R.string.locality_name))
            sb.append(addresses[0].locality)
            tv_locality.text = sb.toString()
        } else {
            // do your stuff
        }
    }

    @Suppress("SENSELESS_COMPARISON", "UNREACHABLE_CODE", "UNNECESSARY_NOT_NULL_ASSERTION")
    @SuppressLint("MissingPermission")
    fun getLocation(): Location {
        val locationManager: LocationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager != null) {
            val lastKnownLocationGPS = locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER) as Location
            Utils.showLog("lastKnownLocation : " + lastKnownLocationGPS)
            if (lastKnownLocationGPS != null) {
                return lastKnownLocationGPS
            } else {
                val loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                System.out.println("1::" + loc)
                System.out.println("2::" + loc.latitude)
                return loc
            }
        } else {
            return null!!
        }
    }
}
