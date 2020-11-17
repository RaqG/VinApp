package br.com.madebygallo.vinapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.madebygallo.vinapp.model.Wine
import br.com.madebygallo.vinapp.model.WineVariety

/**
 * Created by RaqG on 14/07/2020.
 */

@Dao
interface WineVarietyDao {
    @Query("SELECT * FROM wine_variety")
    suspend fun loadAllWinesVariety(): List<WineVariety>

    @Query("SELECT * FROM wine_variety WHERE wine_variety_id IN (:wineVarietyIds)")
    fun loadAllByWineVarietyId(vararg wineVarietyIds: Int): List<WineVariety>

    @Query("SELECT * FROM wine_variety where wine_variety_name LIKE :wineVarietyName LIMIT 1")
    fun loadOneByWineVarietyName(wineVarietyName: String): WineVariety

    @Query("SELECT * FROM wine_variety where wine_variety_id LIKE :wineVarietyId LIMIT 1")
    fun loadOneByWineVarietyId(wineVarietyId: Int): WineVariety

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWineVariety(wineVariety: WineVariety): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(repos: List<WineVariety>) : List<Long>

    @Update
    fun updateWineVariety(wineVariety: WineVariety)

    @Delete
    suspend fun deleteWineVariety(wineVariety: WineVariety)

    @Transaction
    suspend fun upsert(wineVariety: WineVariety) {
        val id = insertWineVariety(wineVariety)
        if (id == -1L) {
            updateWineVariety(wineVariety)
        }
    }
}