package br.com.madebygallo.vinapp.data

import androidx.lifecycle.LiveData
import br.com.madebygallo.vinapp.database.VinAppDatabase
import br.com.madebygallo.vinapp.model.GrapeVariety
import br.com.madebygallo.vinapp.model.Wine
import br.com.madebygallo.vinapp.model.WineRegion
import br.com.madebygallo.vinapp.model.WineVariety

/**
 * Created by RaqG on 14/07/2020.
 */

class VinAppRepository(
    private val vinAppDatabase: VinAppDatabase
) {
    suspend fun getWines(): List<Wine> {
        return vinAppDatabase.wineDao().loadAllWines()
    }

    suspend fun getWinesVariety(): List<WineVariety> {
        return vinAppDatabase.wineVarietyDao().loadAllWinesVariety()
    }

    suspend fun getGrapesVariety(): List<GrapeVariety> {
        return vinAppDatabase.grapeVarietyDao().loadAllGrapesVariety()
    }

    suspend fun getWinesRegion(): List<WineRegion> {
        return vinAppDatabase.regionDao().loadAllRegions()
    }

    suspend fun populateGrapeVariety(){
        vinAppDatabase.grapeVarietyDao().insertAll(GrapeVariety.populateGrapeVariety())
    }

    suspend fun populateWineVariety(){
        vinAppDatabase.wineVarietyDao().insertAll(WineVariety.populateWineVariety())
    }
}