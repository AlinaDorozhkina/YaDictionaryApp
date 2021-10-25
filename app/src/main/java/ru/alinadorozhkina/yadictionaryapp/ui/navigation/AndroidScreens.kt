package ru.alinadorozhkina.yadictionaryapp.ui.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.alinadorozhkina.yadictionaryapp.navigation.IScreens
import ru.alinadorozhkina.yadictionaryapp.ui.main.MainFragment

class AndroidScreens: IScreens {
//    override fun toFavourites(): Screen {
//        TODO("Not yet implemented")
//    }

    override fun toMain() = FragmentScreen {MainFragment.newInstance()}

//    override fun toSettings(): Screen {
//        TODO("Not yet implemented")
//    }
}