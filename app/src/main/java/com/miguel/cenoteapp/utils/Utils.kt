package com.miguel.cenoteapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.miguel.cenoteapp.R
import java.io.Serializable

class Utils {

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkAvailableFragments(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }

    fun direction(modifier:String?, type:String, street: String, references: String?): Pair<String, Int>? {
        println("MODIFIER: ${modifier}, TYPE: ${type}, Street: ${street}, References: ${references}")
        //turn,
        return when(type){
            "depart" -> { // salir
                when(modifier) {
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Salir y seguir derecho por ${street} (${references})", R.drawable.icon_straight)
                        }
                        else{
                            if (street == ""){
                                Pair("Salir y seguir derecho", R.drawable.icon_straight)
                            } else{
                                Pair("Salir y seguir derecho por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "right"-> { //dobla  a la derecha
                        if (references != null){
                            Pair("Salir y doblar a la derecha por ${street} (${references}", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Salir y doblar a la derecha", R.drawable.icon_right)
                            } else{
                                Pair("Salir y doblar a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("Salir y doblar a la izquierda por ${street} (${references}", R.drawable.icon_left)
                        } else{
                            if (street == ""){
                                Pair("Salir y doblar a la izquierda", R.drawable.icon_left)
                            }else{
                                Pair("Salir y doblar a la izquierda por ${street}", R.drawable.icon_left)
                            }
                        }
                    }
                    "uturn"->{ //"Doblar en U"
                        if (references != null){
                            Pair("Salir y dar vuelta en U por ${street} (${references}", R.drawable.uturn_left_icon)
                        }else if (street == ""){
                            Pair("Salir y dar vuelta en U", R.drawable.uturn_left_icon)
                        }  else{
                            Pair("Salir y dar vuelta en U por ${street}", R.drawable.uturn_left_icon)
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("Salir y dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else{
                            if (street == ""){
                                Pair("Salir y dar ligeramente vuelta a la derecha", R.drawable.turn_slight_right_icon)
                            }else{
                                Pair("Salir y dar vuelta a la derecha por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
                    null -> {
                        if (references != null){
                            Pair("Salir y dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.icon_right)
                        }else{
                            if (street == ""){
                                Pair("Salir y dar ligeramente vuelta a la derecha", R.drawable.icon_right)
                            } else{
                                Pair("Salir y dar vuelta a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    else -> {null}
                }
            }
            "new name"-> {
                when(modifier){
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Seguir derecho por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Seguir derecho", R.drawable.icon_straight)
                            } else{
                                Pair("Seguir derecho por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "right"-> { //dobla  a la derecha
                        if (references != null){
                            Pair("Doblar a la derecha por ${street} (${references})", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Doblar a la derecha", R.drawable.icon_right)
                            } else {
                                Pair("Doblar a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("Doblar a la izquierda por ${street} (${references})", R.drawable.icon_left)
                        } else{
                            if (street == ""){
                                Pair("Doblar a la izquierda", R.drawable.icon_left)
                            } else {
                                Pair("Doblar a la izquierda por ${street}", R.drawable.icon_left)
                            }
                        }
                    }
                    "uturn"->{ //"Doblar en U"
                        if (references != null){
                            Pair("Dar vuelta en U por ${street} (${references})", R.drawable.uturn_left_icon)
                        } else{
                            if (street == ""){
                                Pair("Dar vuelta en U", R.drawable.uturn_left_icon)
                            } else {
                                Pair("Dar vuelta en U por ${street}", R.drawable.uturn_left_icon)
                            }
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("Dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else{
                            if (street == ""){
                                Pair("Dar ligeramente vuelta a la derecha", R.drawable.turn_slight_right_icon)
                            } else {
                                Pair("Dar ligeramente vuelta a la derecha por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
                    null -> {
                        if (references != null){
                            Pair("Salir y dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Salir y dar ligeramente vuelta a la derecha", R.drawable.icon_right)
                            } else {
                                Pair("Salir y dar vuelta a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    else -> {null}
                }
            }
            "exit roundabout" -> {//rotonda de salida
                when(modifier){
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Al salir de la rotonda, siga derecho por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Al salir de la rotonda, siga derecho", R.drawable.icon_straight)
                            } else {
                                Pair("Al salir de la rotonda, sigua derecho por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "right"-> { //dobla  a la derecha
                        if (references != null){
                            Pair("Al salir de la rotonda, doble a la derecha por ${street} (${references})", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Al salir de la rotonda, doble a la derecha", R.drawable.icon_right)
                            } else {
                                Pair("Al salir de la rotonda, doblar a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("Al salir de la rotonda, doble a la izquierda por ${street} (${references})", R.drawable.icon_left)
                        } else{
                            if (street == ""){
                                Pair("Al salir de la rotonda, doble a la izquierda", R.drawable.icon_left)
                            } else {
                                Pair("Al salir de la rotonda, doble a la izquierda por ${street}", R.drawable.icon_left)
                            }
                        }
                    }
                    "uturn"-> { //"Doblar en U"
                        if (references != null){
                            Pair("Al salir de la rotonda, dar vuelta en U por ${street} (${references})", R.drawable.uturn_left_icon)
                        } else {
                            if (street == ""){
                                Pair("Al salir de la rotonda, dar vuelta en U", R.drawable.uturn_left_icon)
                            } else {
                                Pair("Al salir de la rotonda, dar vuelta en U por ${street}", R.drawable.uturn_left_icon)
                            }
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("Al salir de la rotonda, dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else {
                            if (street == ""){
                                Pair("Al salir de la rotonda, dar ligeramente vuelta a la derecha", R.drawable.turn_slight_right_icon)
                            } else {
                                Pair("Al salir de la rotonda, dar vuelta a la derecha por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
                    null -> {
                        if (references != null){
                            Pair("Salir y dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Salir y dar ligeramente vuelta a la derecha", R.drawable.icon_straight)
                            } else {
                                Pair("Salir y dar vuelta a la derecha por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    else -> {null}
                }
            }
            "roundabout"-> {// rotonda
                when(modifier){
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Entre a la rotonda ${street} (${references})", R.drawable.icon_rotonda)
                        } else{
                            if (street == ""){
                                Pair("Entre a la rotonda", R.drawable.icon_rotonda)
                            } else {
                                Pair("Entre a la rotonda ${street}", R.drawable.icon_rotonda)
                            }
                        }
                    }
                    "right"-> { //dobla  a la derecha
                        if (references != null){
                            Pair("En la rotonda, doble a la derecha por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("En la rotonda, doble a la derecha", R.drawable.icon_straight)
                            } else {
                                Pair("En la rotonda, doblar a la derecha por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("En la rotonda, doble a la izquierda por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("En la rotonda, doble a la izquierda", R.drawable.icon_straight)
                            } else {
                                Pair("En rotonda, doble a la izquierda por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("En rotonda, doble ligeramente a la derecha por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else{
                            if (street == ""){
                                Pair("En rotonda, doble ligeramente a la derecha", R.drawable.turn_slight_right_icon)
                            } else {
                                Pair("En rotonda, doble ligeramente a la derecha por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
//                    "uturn"-> { //"Doblar en U"
//                        if (references != null){
//                            Pair("En la rotonda, dar vuelta en U por ${street} (${references})", R.drawable.icon_straight)
//                        } else{
//                            if (street == ""){
//                                Pair("En la rotonda, dar vuelta en U", R.drawable.icon_straight)
//                            } else {
//                                Pair("En la rotonda, dar vuelta en U por ${street}", R.drawable.icon_straight)
//                            }
//                        }
//                    }
//                    "slight right"-> { //"ligeramente a la derecha"
//                        if (references != null){
//                            Pair("Al salir de la rotonda, dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.icon_straight)
//                        } else{
//                            if (street == ""){
//                                Pair("Al salir de la rotonda, dar ligeramente vuelta a la derecha", R.drawable.icon_straight)
//                            } else {
//                                Pair("Al salir de la rotonda, dar vuelta a la derecha por ${street}", R.drawable.icon_straight)
//                            }
//                        }
//                    }
//                    null -> {
//                        if (references != null){
//                            Pair("Salir y dar ligeramente vuelta a la derecha por ${street} (${references})", R.drawable.icon_straight)
//                        } else{
//                            if (street == ""){
//                                Pair("Salir y dar ligeramente vuelta a la derecha", R.drawable.icon_straight)
//                            } else {
//                                Pair("Salir y dar vuelta a la derecha por ${street}", R.drawable.icon_straight)
//                            }
//                        }
//                    }
                    else -> {null}
                }
            }
            "arrive"->{//"Tu destino esta hacia ${}"
                when(modifier){
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Su destino se encuentra en linea recta por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Su destino se encuentra en linea recta", R.drawable.icon_straight)
                            } else {
                                Pair("Su destino se encuentra en linea recta por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "right"-> { //dobla  a la derecha
                        if (references != null){
                            Pair("Su destino se encuentra a la derecha por ${street} (${references})", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Su destino se encuentra a la derecha", R.drawable.icon_right)
                            } else {
                                Pair("Su destino se encuentra a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("Su destino se encuentra a la izquierda por ${street} (${references})", R.drawable.icon_left)
                        } else{
                            if (street == ""){
                                Pair("Su destino se encuentra a la izquierda", R.drawable.icon_left)
                            } else {
                                Pair("Su destino se encuentra a la izquierda por ${street}", R.drawable.icon_left)
                            }
                        }
                    }
                    "uturn"->{ //"Doblar en U"
                        if (references != null){
                            Pair("Su destino se encuentra doblando en U por ${street} (${references})", R.drawable.uturn_left_icon)
                        } else{
                            if (street == ""){
                                Pair("Su destino se encuentra doblando en U", R.drawable.uturn_left_icon)
                            } else {
                                Pair("Su destino se encuentra doblando en U por ${street}", R.drawable.uturn_left_icon)
                            }
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("Su destino se encuentra doblando ligeramente a la derecha por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else{
                            if (street == ""){
                                Pair("Su destino se encuentra doblando ligeramente a la derecha", R.drawable.turn_slight_right_icon)
                            } else {
                                Pair("Su destino se encuentra doblando ligeramente a la derecha por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
                    null -> {
                        if (references != null){
                            Pair("Su destino se encuentra aqui", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Su destino se encuentra aqui", R.drawable.icon_straight)
                            } else {
                                Pair("Su destino se encuentra aqui", R.drawable.icon_straight)
                            }
                        }
                    }
                    else -> {null}
                }
            }
            "continue"-> {//continuar
                when(modifier?.trim()){
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Siga derecho y continue por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Siga derecho y continue", R.drawable.icon_straight)
                            } else {
                                Pair("Siga derecho y continue por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "right" -> { //dobla  a la derecha
                        if (references != null){
                            Pair("Doble a la derecha y continue por ${street} (${references})", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Doble a la derecha y continue", R.drawable.icon_right)
                            } else {
                                Pair("Doble a la derecha y continue por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("Doble a la izquierda y continue por ${street} (${references})", R.drawable.icon_left)
                        } else{
                            if (street == ""){
                                Pair("Doble a la izquierda y continue por", R.drawable.icon_left)
                            } else {
                                Pair("Doble a la izquierda y continue por ${street}", R.drawable.icon_left)
                            }
                        }
                    }
                    "uturn"->{ //"Doblar en U"
                        if (references != null){
                            Pair("De vuelta en U y continue por ${street} (${references})", R.drawable.uturn_left_icon)
                        } else{
                            if (street == ""){
                                Pair("De vuelta en U y continue", R.drawable.uturn_left_icon)
                            } else {
                                Pair("De vuelta en U y continue por ${street}", R.drawable.uturn_left_icon)
                            }
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("Doble ligeramente a la derecha y continue por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else{
                            if (street == ""){
                                Pair("Doble ligeramente a la derecha y continue", R.drawable.turn_slight_right_icon)
                            } else {
                                Pair("Doble ligeramente a la derecha y continue por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
                    null ->{
                        if (references != null){
                        Pair("Siga derecho y continue por ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Siga derecho y continue", R.drawable.icon_straight)
                            } else {
                                Pair("Siga derecho y continue por ${street}", R.drawable.icon_straight)
                            }
                        }
                   }
                    else -> {null}
                }
            }
            "turn"-> {//continuar
                when(modifier?.trim()){
                    "straight"-> { // Seguir derecho
                        if (references != null){
                            Pair("Gira y sigue derecho ${street} (${references})", R.drawable.icon_straight)
                        } else{
                            if (street == ""){
                                Pair("Gira y sigue derecho", R.drawable.icon_straight)
                            } else {
                                Pair("Gira y sigue derecho por ${street}", R.drawable.icon_straight)
                            }
                        }
                    }
                    "right" -> { //dobla  a la derecha
                        if (references != null){
                            Pair("Gira a la derecha por ${street} (${references})", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Gira a la derecha", R.drawable.icon_right)
                            } else {
                                Pair("Gira a la derecha por ${street}", R.drawable.icon_right)
                            }
                        }
                    }
                    "left"-> {// dobla a la izquierda
                        if (references != null){
                            Pair("Gira a la izquierda por ${street} (${references})", R.drawable.icon_left)
                        } else{
                            if (street == ""){
                                Pair("Gira a la izquierda", R.drawable.icon_left)
                            } else {
                                Pair("Gira a la izquierda por ${street}", R.drawable.icon_left)
                            }
                        }
                    }
                    "uturn"->{ //"Doblar en U"
                        if (references != null){
                            Pair("Da vuelta en U por ${street} (${references})", R.drawable.uturn_left_icon)
                        } else{
                            if (street == ""){
                                Pair("Da vuelta en U", R.drawable.uturn_left_icon)
                            } else {
                                Pair("Da vuelta en U por ${street}", R.drawable.uturn_left_icon)
                            }
                        }
                    }
                    "slight right"-> { //"ligeramente a la derecha"
                        if (references != null){
                            Pair("Gira levemente a la derecha por ${street} (${references})", R.drawable.turn_slight_right_icon)
                        } else{
                            if (street == ""){
                                Pair("Gira levemente a la derecha", R.drawable.turn_slight_right_icon)
                            } else {
                                Pair("Gira levemente a la derecha por ${street}", R.drawable.turn_slight_right_icon)
                            }
                        }
                    }
                    null -> {
                        if (references != null){
                            Pair("Gira levemente a la derecha", R.drawable.icon_right)
                        } else{
                            if (street == ""){
                                Pair("Gira levemente a la derecha", R.drawable.icon_right)
                            } else {
                                Pair("Gira levemente a la derecha", R.drawable.icon_right)
                            }
                        }
                    }
                    else -> { null }
                }
            }
            else -> {null}
        }
    }
}