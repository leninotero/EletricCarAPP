package com.br.leninotero.electriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.br.leninotero.electriccarapp.domain.Carro

class CarRepository(private val context: Context) {

    var isSaved = false
    fun save(carro: Carro) : Boolean {
        try {
            val dbHelper = CarsDbHelper(context).writableDatabase

            val values = ContentValues().apply {
                put(CarrosContract.CarEntry.COLUMN_NAME_PRECO, carro.preco)
                put(CarrosContract.CarEntry.COLUMN_NAME_BATERIA, carro.bateria)
                put(CarrosContract.CarEntry.COLUMN_NAME_POTENCIA, carro.potencia)
                put(CarrosContract.CarEntry.COLUMN_NAME_RECARGA, carro.recarga)
                put(CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }

            val inserted = dbHelper?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values)

            if (inserted != null){
                isSaved = true
            }

        } catch (ex : Exception){
            ex.message?.let {
                Log.e("Erro ao inserir os dados  -> ", it) }
        }
        return isSaved
    }
}