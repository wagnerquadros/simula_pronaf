package com.wagner.simulapronaf.domain.utils

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

private val moedaBR: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
private val df: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

fun Double.toBRL(): String = moedaBR.format(this)
fun Float.toBRL(): String = moedaBR.format(this)
fun LocalDate.br(): String = this.format(df)