package com.br.leninotero.electriccarapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.br.leninotero.electriccarapp.ui.CarFragment
import com.br.leninotero.electriccarapp.ui.FavoriteFragment

class TabAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> CarFragment()
           1 -> FavoriteFragment()
           else -> CarFragment()
       }

    }

    override fun getItemCount(): Int {
        return 2
    }
}