package com.miguel.cenoteapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miguel.cenoteapp.databinding.ActivityMainBinding
import com.miguel.mapsboxexmaple.ViewModels.ViewModelMap
import com.miguel.mapsboxexmaple.Views.ModalBottomSheets
import com.miguel.mapsboxexmaple.utils.LocalizationUser
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

class MainActivity : AppCompatActivity(), MapListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var markets: Marker
    lateinit var locationButton: FloatingActionButton
    lateinit var modalBottomSheet: ModalBottomSheets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
//        }

        val viewModelMap = ViewModelProvider(this)[ViewModelMap::class.java]
        val mapView = binding.root.findViewById<MapView>(R.id.mapa)
        mapView.addMapListener(this)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(GeoPoint(20.983617, -89.619942))
        mapView.invalidate()
        //verificar si el permiso esta activo
        val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                lifecycleScope.launch {
                    val result = LocalizationUser().getUserLocation(applicationContext)
                    if (result != null){
                        //showMarker(location = result, mapView)
                        println("${result.latitude},${ result.longitude}")
                        viewModelMap.getPoistionUser(result)
                    }
                }
            }
        }
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            println("pidiendo permisos nuevamente")
        }
        viewModelMap.positionUser.observe(this, Observer {
            if (it != null){
                println("viuewmodel activop")
                println("Data: ${it.latitude},${ it.longitude}")
                mapView.controller.setCenter(GeoPoint(it.latitude,it.longitude)) // Coordenadas de París
                showMarker(
                    location = it,
                    it.latitude,
                    it.longitude,
                    "Yo",
                    null,
                    "Mi ubicacion actual",
                    mapView,
                    viewModelMap
                )
            }
        })

        val routeOverlay = Polyline(mapView)
        viewModelMap.route.observe(this, Observer {
            if (it != null){
                val routePoints = ArrayList<GeoPoint>()
                routePoints.clear()
                it.points?.forEach { geoPoint->
                    routePoints.add(geoPoint)
                }
                routeOverlay.setPoints(routePoints)
                routeOverlay.color = Color.GREEN // Color de la línea de la ruta
                routeOverlay.width = 5f // Ancho de la línea
                mapView.overlays.add(routeOverlay)
                mapView.invalidate()
            } else{
                Toast.makeText(this, "Verifica tu conexion a internet :)", Toast.LENGTH_SHORT).show()
            }
        })

        viewModelMap.cenotes.observe(this, Observer {
            if (it != null){
                it.data.forEach {
                    showMarker(
                        null,
                        it.ubication.latitude,
                        it.ubication.longitude,
                        it.name,
                        it.dataSource.img,
                        "Aqui vamo a pescar chavales, ostias tio!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                        mapView,
                        viewModelMap)
                }
            }

        })

        mapView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val touchedPoint = mapView.projection.fromPixels(event.x.toInt(), event.y.toInt())
                val latitude = touchedPoint.latitude
                val longitude = touchedPoint.longitude
                // Ahora tienes las coordenadas (latitud y longitud)
                Log.d("Coordenadas", "Latitud: $latitude, Longitud: $longitude")
            }
            false
        }

        mapView.invalidate() // Actualiza el mapa
        locationButton = binding.location
        locationButton.setOnClickListener {view->
            lifecycleScope.launch {
                val result = LocalizationUser().getUserLocation(applicationContext)
                println(result)
                if (result != null){
                    viewModelMap.getPoistionUser(result)
                }else{
                    viewModelMap.getPoistionUser(null)
                }
            }
        }


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showMarker(
        location: Location?,
        latitude: Double?,
        longitud: Double?,
        name: String,
        cenoteLocations: ArrayList<String>?,
        snippet: String,
        mapView: MapView,
        viewModelMap: ViewModelMap,
    ) {
        if (location != null) {
            val market = Marker(mapView)
            market.title = "Yo"
            market.position = GeoPoint(location.latitude, location.longitude)
            market.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            val icon = resources.getDrawable(R.drawable.user_icon)
            market.icon = icon
            mapView.overlays.add(market)
        } else {
            markets = Marker(mapView)
            markets.position = GeoPoint(latitude!!, longitud!!)
            markets.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            mapView.overlays.add(markets)
            markets.setOnMarkerClickListener { marker, mapView ->
                val latitude = marker.position.latitude
                val longitude = marker.position.longitude
                val locationUser = viewModelMap.positionUser.value
                modalBottomSheet = ModalBottomSheets(name, cenoteLocations)
                modalBottomSheet.show(supportFragmentManager, ModalBottomSheets.TAG)
                if (locationUser != null) {
                    viewModelMap.route(
                        locationUser.latitude,
                        locationUser.longitude,
                        latitude,
                        longitude
                    )
                }
                markets.title = name
                markets.snippet = snippet
                false
            }
        }
    }
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.root.findViewById<MapView>(R.id.mapa).onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.root.findViewById<MapView>(R.id.mapa).onResume()
    }
    override fun onScroll(event: ScrollEvent?): Boolean {
        Log.e("TAG", "onCreate:la ${event?.source?.getMapCenter()?.latitude}")
        Log.e("TAG", "onCreate:lo ${event?.source?.getMapCenter()?.longitude}")
        Log.e("TAG", "onScroll   x: ${event?.x}  y: ${event?.y}", )
        return false
    }

    override fun onZoom(event: ZoomEvent?): Boolean {
        Log.e("TAG", "onCreate:la ${event?.source?.getMapCenter()?.latitude}")
        Log.e("TAG", "onCreate:lo ${event?.source?.getMapCenter()?.longitude}")
//        Log.e("TAG", "onScroll   x: ${event?.zoomLevel}  y: ${event?.y}", )
        return false
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}