package com.br.leninotero.electriccarapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.br.leninotero.electriccarapp.R
import com.br.leninotero.electriccarapp.data.local.CarRepository
import com.br.leninotero.electriccarapp.domain.Carro
import com.br.leninotero.electriccarapp.ui.adapter.CarAdapter

class FavoriteFragment : Fragment() {

    lateinit var listaCarrosFavoritos : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
    }

    private fun getCarsOnLocalDb(): List<Carro>{
        val repository = CarRepository(requireContext())
        var carList = repository.getAll()
        return carList
    }

    fun setupView(view: View){
        view.apply {
            listaCarrosFavoritos = findViewById(R.id.rv_lista_carros_favoritos)
        }
    }

    fun setupList(){
        val cars = getCarsOnLocalDb()
        val carroAdapter = CarAdapter(cars, isFavoriteScreen = true)
        listaCarrosFavoritos.apply {
            isVisible = true
            adapter = carroAdapter
        }
        carroAdapter.carItemListener = { carro ->
//            CarRepository(requireContext()).saveIfNotExist(carro)
        }
    }
}