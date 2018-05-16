package com.arclights.wikivoyageparser.factory

import com.arclights.wikivoyageparser.Climate
import com.arclights.wikivoyageparser.Degree
import com.arclights.wikivoyageparser.MeasurementUnit
import com.arclights.wikivoyageparser.MeasurementUnit.IMPERIAL
import com.arclights.wikivoyageparser.MeasurementUnit.METRIC
import com.arclights.wikivoyageparser.Precipitation
import java.time.Month.APRIL
import java.time.Month.AUGUST
import java.time.Month.DECEMBER
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.MARCH
import java.time.Month.MAY
import java.time.Month.NOVEMBER
import java.time.Month.OCTOBER
import java.time.Month.SEPTEMBER


private val monthMappings = mapOf(
        "jan" to JANUARY,
        "feb" to FEBRUARY,
        "mar" to MARCH,
        "apr" to APRIL,
        "may" to MAY,
        "jun" to JUNE,
        "jul" to JULY,
        "aug" to AUGUST,
        "sep" to SEPTEMBER,
        "oct" to OCTOBER,
        "nov" to NOVEMBER,
        "dec" to DECEMBER
)

object ClimateFactory {
    fun getClimate(parts: List<String>): Climate {
        val mappings = parts
                .map { it.split("=").map { it.trim() } }
                .map { if (it.size > 1) it[0] to it[1] else "name" to it[0] }
                .toMap()
        val unit = when (mappings["units"]) {
            "Imperial" -> IMPERIAL
            "Metric" -> METRIC
            else -> METRIC
        }
        return Climate(
                unit = unit,
                dailyHighs = monthMappings
                        .map { it.value to mappings["${it.key}high"]?.toDouble()?.toDegree(unit) }
                        .mapNotNull { (key, nullable) -> nullable?.let { key to it } }
                        .toMap(),
                nightlyLows = monthMappings
                        .map { it.value to mappings["${it.key}low"]?.toDouble()?.toDegree(unit) }
                        .mapNotNull { (key, nullable) -> nullable?.let { key to it } }
                        .toMap(),
                precipitation = monthMappings
                        .map { it.value to mappings["${it.key}precip"]?.toDouble()?.toPrecipitation(unit) }
                        .mapNotNull { (key, nullable) -> nullable?.let { key to it } }
                        .toMap(),
                description = mappings["description"]!!
        )
    }
}

private fun Double.toDegree(unit: MeasurementUnit) =
        when (unit) {
            METRIC -> Degree(
                    celsius = this,
                    fahrenheit = celsius2Fahrenheit(this)
            )
            IMPERIAL -> Degree(
                    celsius = fahrenheit2Celsius(this),
                    fahrenheit = this
            )
        }

private fun celsius2Fahrenheit(celsius: Double) = celsius * 1.8 + 32

private fun fahrenheit2Celsius(fahrenheit: Double) = (fahrenheit - 32) / 1.8

private fun Double.toPrecipitation(unit: MeasurementUnit) =
        when (unit) {
            METRIC -> Precipitation(
                    millimeter = this,
                    inches = millimeters2Inches(this)
            )
            IMPERIAL -> Precipitation(
                    millimeter = inches2Millimeters(this),
                    inches = this
            )
        }

private fun millimeters2Inches(millimeters: Double) = millimeters / 25.4

private fun inches2Millimeters(inches: Double) = inches * 25.4