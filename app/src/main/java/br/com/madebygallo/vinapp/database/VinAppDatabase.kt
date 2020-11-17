package br.com.madebygallo.vinapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.madebygallo.vinapp.data.local.GrapeVarietyDao
import br.com.madebygallo.vinapp.data.local.RegionDao
import br.com.madebygallo.vinapp.data.local.WineDao
import br.com.madebygallo.vinapp.data.local.WineVarietyDao
import br.com.madebygallo.vinapp.model.GrapeVariety
import br.com.madebygallo.vinapp.model.Wine
import br.com.madebygallo.vinapp.model.WineRegion
import br.com.madebygallo.vinapp.model.WineVariety
import br.com.madebygallo.vinapp.utils.ConstantsUtil.DB_NAME


/**
 * Created by RaqG on 14/07/2020.
 */

@Database(
    entities = [Wine::class, WineVariety::class, GrapeVariety::class, WineRegion::class],
    version = 1,
    exportSchema = false
)
abstract class VinAppDatabase : RoomDatabase() {

    abstract fun wineDao(): WineDao
    abstract fun wineVarietyDao(): WineVarietyDao
    abstract fun grapeVarietyDao(): GrapeVarietyDao
    abstract fun regionDao(): RegionDao

    companion object {
        @Volatile
        private var INSTANCE: VinAppDatabase? = null

        fun getInstance(context: Context): VinAppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): VinAppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                VinAppDatabase::class.java, DB_NAME
            )
                .build()
        }
    }
}