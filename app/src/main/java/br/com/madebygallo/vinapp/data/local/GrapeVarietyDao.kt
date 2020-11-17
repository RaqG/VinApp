package br.com.madebygallo.vinapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.madebygallo.vinapp.model.GrapeVariety

/**
 * Created by RaqG on 14/07/2020.
 */

@Dao
interface GrapeVarietyDao {
    @Query("SELECT * FROM grape_variety")
    suspend fun loadAllGrapesVariety(): List<GrapeVariety>

    @Query("SELECT * FROM grape_variety WHERE grape_variety_id IN (:varietyIds)")
    fun loadAllByGrapeVarietyId(vararg varietyIds: Int): List<GrapeVariety>

    @Query("SELECT * FROM grape_variety where grape_variety_name LIKE :varietyName LIMIT 1")
    fun loadOneByGrapeVarietyName(varietyName: String): GrapeVariety

    @Query("SELECT * FROM grape_variety where grape_variety_id LIKE :varietyId LIMIT 1")
    fun loadOneBGrapeVarietyId(varietyId: Int): GrapeVariety

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrapeVariety(grapeVariety: GrapeVariety): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(repos: List<GrapeVariety>): List<Long>

    @Update
    fun updateGrapeVariety(grapeVariety: GrapeVariety)

    @Delete
    suspend fun deleteGrapeVariety(grapeVariety: GrapeVariety)

    @Transaction
    suspend fun upsert(grapeVariety: GrapeVariety) {
        val id = insertGrapeVariety(grapeVariety)
        if (id == -1L) {
            updateGrapeVariety(grapeVariety)
        }
    }
}