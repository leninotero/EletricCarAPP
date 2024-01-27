package com.br.leninotero.electriccarapp.data.local

import android.provider.BaseColumns

object CarrosContract {

    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car"
        const val COLUMN_NAME_PRECO = "preco"
        const val COLUMN_NAME_BATERIA = "bateria"
        const val COLUMN_NAME_POTENCIA = "potencia"
        const val COLUMN_NAME_RECARGA = "recarga"
        const val COLUMN_NAME_URL_PHOTO = "ur_photo"
    }


    const val TABLE_CAR =
        "CREATE TABLE ${CarEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMEY KEY," +
                "${CarEntry.COLUMN_NAME_PRECO} TEXT," +
                "${CarEntry.COLUMN_NAME_BATERIA} TEXT," +
                "${CarEntry.COLUMN_NAME_POTENCIA} TEXT," +
                "${CarEntry.COLUMN_NAME_RECARGA} TEXT" +
                "${CarEntry.COLUMN_NAME_URL_PHOTO} TEXT)"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXIST ${CarEntry.TABLE_NAME}"
}