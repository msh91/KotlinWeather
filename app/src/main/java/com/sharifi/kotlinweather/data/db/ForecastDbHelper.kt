package com.sharifi.kotlinweather.data.db

import android.database.sqlite.SQLiteDatabase
import com.sharifi.kotlinweather.App
import org.jetbrains.anko.db.*

/**
 * Created by sharifi on 8/4/17.
 */
class ForecastDbHelper() : ManagedSQLiteOpenHelper(
        App.instance,
        ForecastDbHelper.DB_NAME,
        null,
        ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        with(CityForecastTable) {
            db.createTable(
                    NAME,
                    true,
                    ID to INTEGER + PRIMARY_KEY,
                    CITY to TEXT,
                    COUNTRY to TEXT
            )
        }
        with(DayForecastTable) {
            db.createTable(
                    NAME,
                    true,
                    ID to INTEGER + PRIMARY_KEY,
                    DATE to INTEGER,
                    DESCRIPTION to TEXT,
                    HIGH to INTEGER,
                    LOW to INTEGER,
                    ICON_URL to TEXT,
                    CITY_ID to INTEGER
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }
}