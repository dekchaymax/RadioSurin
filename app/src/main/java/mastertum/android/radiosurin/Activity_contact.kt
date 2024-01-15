package mastertum.android.radiosurin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Activity_contact : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment!!.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        // Add a marker in Sydney and move the camera
        val radiobutton = LatLng(14.893205, 103.493354)
        googleMap.addMarker(
            MarkerOptions().position(radiobutton).title("สวท.สุรินทร์ Tel : 0-4451-1720")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(radiobutton))
        googleMap.setMinZoomPreference(19F)
    }

}



