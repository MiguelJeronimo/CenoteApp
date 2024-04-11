package com.miguel.mapsboxexmaple.Views

import android.app.Dialog
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.carousel.CarouselSnapHelper
import com.miguel.cenoteapp.Views.components.LinearLayoutCustom
import com.miguel.cenoteapp.databinding.ButtonSheetsBinding
import com.miguel.cenoteapp.recyclerview.AdapterRecyclerViewNavigation
import com.miguel.cenoteapp.utils.Fomulas
import com.miguel.mapsboxexmaple.ViewModels.ViewModelMap
import com.miguel.mapsboxexmaple.recyclerview.AdapterRecyclerViewCenotes
import com.miguel.mapsboxexmaple.recyclerview.ItemsNavigation
import com.miguel.mapsboxexmaple.recyclerview.ItemsRecyclerView

class ModalBottomSheets(
    private val nameCenote: String,
    private val cenoteLocations: ArrayList<String>?,
    private val locationUser: Location?,
    private val latitude: Double,
    private val longitude: Double,
) : BottomSheetDialogFragment() {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private lateinit var binding: ButtonSheetsBinding
    //recyclerview
    private lateinit var adapterRecyclerViewCenotes: AdapterRecyclerViewCenotes
    private var listCenotes = ArrayList<ItemsRecyclerView>()
    var formulas = Fomulas()
    private lateinit var linearLayoutCustom: LinearLayoutCustom
    //recyclerview Naigation
    private lateinit var adapterRecyclerViewNavigation: AdapterRecyclerViewNavigation
    private var listNavigation = ArrayList<ItemsNavigation>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    //private lateinit var binding: FragmentYourBottomSheetBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener { dialogInterface ->
            linearLayoutCustom = LinearLayoutCustom(context, binding)
            val viewModel = ViewModelProvider(this)[ViewModelMap::class.java]

            val recyclerViewNavigation = binding.recyclerViewNavigation
            linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            adapterRecyclerViewNavigation = AdapterRecyclerViewNavigation(itemsNavigation = listNavigation)
            recyclerViewNavigation.layoutManager = linearLayoutManager
            recyclerViewNavigation.setHasFixedSize(true)
            recyclerViewNavigation.adapter = adapterRecyclerViewNavigation

            val recyclerview = binding.recyclerViewCenotes
            CarouselSnapHelper().attachToRecyclerView(recyclerview)
            adapterRecyclerViewCenotes = AdapterRecyclerViewCenotes(items_cenotes = listCenotes)
            recyclerview.adapter = adapterRecyclerViewCenotes
            binding.cenoteName.text = nameCenote

            if (locationUser != null){
                viewModel.route(
                    locationUser.latitude,
                    locationUser.longitude,
                    latitude,
                    longitude
                )
            }
            viewModel.route.observe(this, Observer {
                if (it != null){
                    binding.summary.text = it.summary
                    binding.distances.text = "Distancia aproximada: ${formulas.meterToKiloMeters(it.distances!!)} Km."
                    binding.time.text = "Tiempo aprox: ${formulas.secondsToHours(it.duration!!)}"
                    it.navigation?.forEach {
                        listNavigation.add(
                            ItemsNavigation(
                                it.description.toString(),
                                it.icon,
                                it.distances
                            )
                        )
                    }
                    println("DATA: ${listNavigation}")
                    recyclerViewNavigation.post {
                        adapterRecyclerViewNavigation.notifyDataSetChanged()
                    }
                }
            })
            cenoteLocations?.forEach {
                listCenotes.add(ItemsRecyclerView(
                    it
                ))
            }
            recyclerview.post {
                adapterRecyclerViewCenotes.notifyDataSetChanged()
            }

            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                setupFullHeight(it) // Establece la altura inicial a mitad de pantalla
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }
    private fun setupHalfHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        // Ajusta la altura según tus necesidades (por ejemplo, la mitad de la pantalla)
        bottomSheet.layoutParams = layoutParams
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ButtonSheetsBinding.inflate(inflater, container, false)
        // Infla tu diseño personalizado aquí
        return binding.root
    }
}
