package com.example.fitness.utils

import java.math.BigDecimal
import kotlin.math.pow

class BMICalcUtil {
    fun calculateBMIMetric(heightCm: Double, weightKg: Double): Double {
        val heightInMeters = heightCm / 100 // Convert height from centimeters to meters
        val bmi = weightKg / (heightInMeters * heightInMeters)
        // round to 2 decimal
        return BigDecimal(bmi).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
    }

    fun classifyBMI(bmi: Double): String {
        return if (bmi < 18.5) {
            BMI_CATEGORY_UNDERWEIGHT
        } else if (bmi >= 18.5 && bmi < 25) {
            BMI_CATEGORY_HEALTHY
        } else if (bmi >= 25 && bmi < 30) {
            BMI_CATEGORY_OVERWEIGHT
        } else {
            BMI_CATEGORY_OBESE
        }
    }

    companion object {
        val instance = BMICalcUtil()
        private const val CENTIMETERS_IN_METER = 100
        const val BMI_CATEGORY_UNDERWEIGHT = "Niedowaga"
        const val BMI_CATEGORY_HEALTHY = "Zdrowa waga"
        const val BMI_CATEGORY_OVERWEIGHT = "Nadwaga"
        const val BMI_CATEGORY_OBESE = "Otyłość"
    }
}