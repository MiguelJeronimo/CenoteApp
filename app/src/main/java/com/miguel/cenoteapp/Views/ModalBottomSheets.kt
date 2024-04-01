package com.miguel.mapsboxexmaple.Views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.miguel.cenoteapp.databinding.ButtonSheetsBinding
import com.miguel.mapsboxexmaple.recyclerview.AdapterRecyclerViewCenotes
import com.miguel.mapsboxexmaple.recyclerview.ItemsRecyclerView


class ModalBottomSheets(
    private val nameCenote: String,
    private val cenoteLocations: ArrayList<String>?,
) : BottomSheetDialogFragment() {
    var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private lateinit var binding: ButtonSheetsBinding
    //recyclerview
    private lateinit var adapterRecyclerViewCenotes: AdapterRecyclerViewCenotes
    private var listCenotes = ArrayList<ItemsRecyclerView>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    //private lateinit var binding: FragmentYourBottomSheetBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener { dialogInterface ->
            val recyclerview = binding.recyclerViewCenotes
            CarouselSnapHelper().attachToRecyclerView(recyclerview)
            adapterRecyclerViewCenotes = AdapterRecyclerViewCenotes(items_cenotes = listCenotes)
            recyclerview.adapter = adapterRecyclerViewCenotes
            binding.cenoteName.text = nameCenote

            cenoteLocations?.forEach {
                println(it)
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