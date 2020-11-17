package br.com.madebygallo.vinapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.madebygallo.vinapp.model.WineRegion

/**
 * Created by RaqG on 15/07/2020.
 */

@Dao
interface RegionDao {
    @Query("SELECT * FROM wine_region")
    suspend fun loadAllRegions(): List<WineRegion>

    @Query("SELECT * FROM wine_region WHERE region_id IN (:regionIds)")
    fun loadAllByRegionIds(vararg regionIds: Int): List<WineRegion>

    @Query("SELECT * FROM wine_region where country LIKE :country LIMIT 1")
    fun loadOneByCountry(country: String): WineRegion

    @Query("SELECT * FROM wine_region where region_id LIKE :regionId LIMIT 1")
    fun loadOneByRegionId(regionId: Int): WineRegion

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWineRegion(wineRegion: WineRegion): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(repos: List<WineRegion>): List<Long>

    @Update
    fun updateWineRegion(wineRegion: WineRegion)

    @Delete
    suspend fun deleteWineRegion(wineRegion: WineRegion)

    @Transaction
    suspend fun upsert(wineRegion: WineRegion) {
        val id = insertWineRegion(wineRegion)
        if (id == -1L) {
            updateWineRegion(wineRegion)
        }
    }
}