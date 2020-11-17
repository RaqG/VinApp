package br.com.madebygallo.vinapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.madebygallo.vinapp.model.Wine

/**
 * Created by RaqG on 14/07/2020.
 */

@Dao
interface WineDao {

    @Query("SELECT * FROM wine")
    suspend fun loadAllWines(): List<Wine>

    @Query("SELECT * FROM wine WHERE wine_id IN (:wineIds)")
    fun loadAllByWineId(vararg wineIds: Int): List<Wine>

    @Query("SELECT * FROM wine WHERE wine_name LIKE :wineName LIMIT 1")
    fun loadOneByWineName(wineName: String): Wine

    @Query("SELECT * FROM wine WHERE wine_id LIKE :wineId LIMIT 1")
    fun loadOneByWineId(wineId: Int): Wine

    @Query("SELECT * FROM wine WHERE barcode LIKE :barcode LIMIT 1")
    fun loadOneByBarcode(barcode: String): Wine

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWine(wine: Wine): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(repos: List<Wine>)

    @Update
    suspend fun updateWine(wine: Wine)

    @Delete
    suspend fun deleteWine(wine: Wine)

    @Transaction
    suspend fun upsert(wine: Wine) {
        val id = insertWine(wine)
        if (id == -1L) {
            updateWine(wine)
        }
    }
}