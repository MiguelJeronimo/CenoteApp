package com.miguel.cenoteapp.utils

class Fomulas {

    fun meterToKiloMeters(meters: Float?): String {
        val kilometers = meters!! /1000
        if (kilometers >= 1){
            return "${kilometers} Km."
        } else {
            return "${kilometers*1000} m."
        }
    }

    fun secondsToHours(seconds: Float): String {
        val minute = seconds/60
        val hour = (minute/60).toInt()
        val minutes = ((minute/60) - hour) * 60
        return "${hour} Horas: ${minutes.toInt()} Mins."
    }

}