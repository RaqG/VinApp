package br.com.madebygallo.vinapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.madebygallo.vinapp.utils.getDateNow
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by RaqG on 15/07/2020.
 */

@Entity(tableName = "wine_region")
@Parcelize
data class WineRegion(
    @field:ColumnInfo(name = "country") val country: String,
    @field:ColumnInfo(name = "created_dat") val createdDate: String?,
    @field:ColumnInfo(name = "updated_date") val updatedDate: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "region_id")
    var regionId: Long = 0L

    companion object {

        fun populateWineRegion(): List<WineRegion> {
            val regions: MutableList<WineRegion> = mutableListOf()
            val locales = Locale.getAvailableLocales()
            locales.forEach { locale ->
                val country = locale.displayCountry
                if (country.trim().isNotEmpty() && !regions.any { it.country == country })
                    regions.add(WineRegion(country = country, createdDate = getDateNow(), updatedDate = null))
            }
            return regions.toList()
        }
    }
}