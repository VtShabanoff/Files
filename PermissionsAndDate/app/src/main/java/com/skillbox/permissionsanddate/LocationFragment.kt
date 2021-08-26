package com.skillbox.permissionsanddate

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skillbox.permissionsanddate.adapters.LocationAdapter
import com.skillbox.permissionsanddate.databinding.FragmentLocationBinding
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.random.Random

class LocationFragment : Fragment(R.layout.fragment_location) {

    private val binding by viewBinding(FragmentLocationBinding::bind)

    private val arrayPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private var rationaleDialog: AlertDialog? = null
    private val buttonLocation: FloatingActionButton
        get() = binding.addLocationButton

    private var locationAdapter by AutoClearedValue<LocationAdapter>()

    private var locations: List<MyLocation> = arrayListOf()

    private var selectedDateTimeInstant: Instant? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLocation.setOnClickListener {
            showPermissionLocation()
        }
        initRecycleView()
        locationAdapter.updateMyLocation(locations)
    }

    private fun showPermissionLocation() {
        val isLocationPermissionGranted = ActivityCompat
            .checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (isLocationPermissionGranted) {
            showRequestLocationUpdates()
        } else {
            val needRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            if (needRationale) {
                showRationaleDialog()
            } else {
                permissionRequestLauncher.launch(arrayPermissions)
            }
        }

    }

    private fun showRationaleDialog() {
        rationaleDialog = AlertDialog.Builder(requireContext())
            .setMessage("Необходимо разрешение для отображения информации о локации")
            .setPositiveButton("OK") { _, _ -> permissionRequestLauncher.launch(arrayPermissions) }
            .setNegativeButton("CANCEL", null)
            .show()
    }

    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value == true }) {
                showRequestLocationUpdates()
            } else {
                Toast.makeText(requireContext(),
                    "Нельзя отобразить локацию без разрешения",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }

    @SuppressLint("MissingPermission")
    private fun showRequestLocationUpdates(){
        val clientLocation = LocationServices
            .getFusedLocationProviderClient(requireContext())
        val locationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 10000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                createListLocation("""
                    Шир = ${locationResult.lastLocation.latitude}
                    Дол = ${locationResult.lastLocation.longitude}
                """.trimIndent())
                locationAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        super.onItemRangeInserted(positionStart, itemCount)
                        binding.locationList.smoothScrollToPosition(0)
                    }
                })
            }
        }

        clientLocation.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun initRecycleView() {
        locationAdapter = LocationAdapter { position ->
            initTimeDatePiker(position)
        }

        with(binding.locationList) {
            adapter = locationAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            itemAnimator = ScaleInAnimator()
        }
    }

    private fun createListLocation(location: String){
        val newListLocation = MyLocation(
            id = Random.nextLong(),
            location = location,
            createdAt = selectedDateTimeInstant ?: Instant.now(),
            avatarLink = "https://zamanilka.ru/wp-content/uploads/2020/12/vertikal-visokoe-kachestvo-1080x2340-1.jpg"
        )
        locations = listOf(newListLocation) + locations
        locationAdapter.updateMyLocation(locations)
        selectedDateTimeInstant = null
    }


    private fun initTimeDatePiker(position: Int) {

        val currentDateTime = LocalDateTime.now()
        DatePickerDialog(
            requireContext(),
            {_, year, month, dayOfMonth ->
                TimePickerDialog(
                    requireContext(),
                    {_, hourOfDay, minute ->
                        val zonedDateTime =
                            LocalDateTime.of(year, month+1, dayOfMonth, hourOfDay, minute)
                                .atZone(ZoneId.systemDefault())
                        Toast.makeText(requireContext(), "time $zonedDateTime", Toast.LENGTH_LONG).show()
                        selectedDateTimeInstant = zonedDateTime.toInstant()
                    },
                    currentDateTime.hour,
                    currentDateTime.minute,
                    true
                ).show()
            },
            currentDateTime.year,
            currentDateTime.month.value - 1,
            currentDateTime.dayOfMonth
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rationaleDialog?.dismiss()
        rationaleDialog = null
    }
}