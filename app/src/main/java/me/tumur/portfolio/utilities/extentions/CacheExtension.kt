package me.tumur.portfolio.utilities.extentions

import org.threeten.bp.LocalDateTime

fun Any.isCacheInActive(lastCached: Int): Boolean {
    val today = LocalDateTime.now().hour
    return (lastCached == 0 || today - lastCached <= 0)
}