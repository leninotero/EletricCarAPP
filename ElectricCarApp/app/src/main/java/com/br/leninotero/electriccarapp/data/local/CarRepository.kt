package com.br.leninotero.electriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.br.leninotero.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_BATERIA
import com.br.leninotero.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import com.br.leninotero.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import com.br.leninotero.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import com.br.leninotero.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_RECARGA
import com.br.leninotero.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import com.br.leninotero.electriccarapp.domain.Carro

class CarRepository(private val context: Context) {

    fun save(carro: Carro) : Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(COLUMN_NAME_CAR_ID, carro.id)
                put(COLUMN_NAME_PRECO, carro.preco)
                put(COLUMN_NAME_BATERIA, carro.bateria)
                put(COLUMN_NAME_POTENCIA, carro.potencia)
                put(COLUMN_NAME_RECARGA, carro.recarga)
                put(COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }

            val inserted = db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values)

            if (inserted != null){
                isSaved = true
            }

        } catch (ex : Exception){
            ex.message?.let {
                Log.e("Erro ao inserir os dados  -> ", it) }
        }
        return isSaved
    }

    fun findCarById(id : Int) : Carro {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        //colunas a serem exibidos no resultado da query
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val filter = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())

        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME,     //Nome da tabela
            columns,                                // colunas a serem exibidas
            filter,                                 // where (filtro)
            filterValues,                           // valor do where, substituindo o parâmetro ?
            null,
            null,
            null
        )

        var itemId : Long = 0
        var preco = ""
        var bateria = ""
        var potencia = ""
        var recarga = ""
        var urlPhoto = ""

        with(cursor){
            while (moveToNext()){
                itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())

                preco = getString((getColumnIndexOrThrow(COLUMN_NAME_PRECO)))
                Log.d("preco -> ", preco)

                bateria = getString((getColumnIndexOrThrow(COLUMN_NAME_BATERIA)))
                Log.d("bateria -> ", bateria)

                potencia = getString((getColumnIndexOrThrow(COLUMN_NAME_POTENCIA)))
                Log.d("potencia -> ", potencia)

                recarga = getString((getColumnIndexOrThrow(COLUMN_NAME_RECARGA)))
                Log.d("recarga -> ", recarga)

                urlPhoto = getString((getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO)))
                Log.d("urlPhoto -> ", urlPhoto)

            }
        }
        cursor.close()
        return Carro(
            id = itemId.toInt(),
            preco = preco,
            bateria = bateria,
            potencia = potencia,
            recarga = recarga,
            urlPhoto = urlPhoto,
            isFavorite = true
        )
    }

    fun saveIfNotExist(carro: Carro){
        val car = findCarById(carro.id)
        if (car.id == ID_WHEN_NO_CAR){
            save(carro)
        }
    }

    fun getAll() : List<Carro> {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        //colunas a serem exibidos no resultado da query
        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val carros = mutableListOf<Carro>()

        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())

                val preco = getString((getColumnIndexOrThrow(COLUMN_NAME_PRECO)))
                Log.d("preco -> ", preco)

                val bateria = getString((getColumnIndexOrThrow(COLUMN_NAME_BATERIA)))
                Log.d("bateria -> ", bateria)

                val potencia = getString((getColumnIndexOrThrow(COLUMN_NAME_POTENCIA)))
                Log.d("potencia -> ", potencia)

                val recarga = getString((getColumnIndexOrThrow(COLUMN_NAME_RECARGA)))
                Log.d("recarga -> ", recarga)

                val urlPhoto = getString((getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO)))
                Log.d("urlPhoto -> ", urlPhoto)

                carros.add(
                    Carro(
                        id = itemId.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto,
                        isFavorite = true
                    )
                )
            }
        }
        cursor.close()
        return carros
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}