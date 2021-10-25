package ru.alinadorozhkina.yadictionaryapp.navigation

import android.accessibilityservice.AccessibilityService
import com.github.terrakok.cicerone.Screen

interface IScreens {
    //fun toFavourites(): Screen
    fun toMain(): Screen
   // fun toSettings(): Screen
}