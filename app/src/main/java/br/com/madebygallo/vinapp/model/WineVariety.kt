package br.com.madebygallo.vinapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import br.com.madebygallo.vinapp.utils.getDateNow
import kotlinx.android.parcel.Parcelize

/**
 * Created by RaqG on 14/07/2020.
 */

@Entity(tableName = "wine_variety", indices = [Index(value = ["wine_variety_name"], unique = true)])
@Parcelize
data class WineVariety(
    @field:ColumnInfo(name = "wine_variety_name") val wineVarietyName: String,
    @field:ColumnInfo(name = "wine_variety_image") val wineVarietyImage: String,
    @field:ColumnInfo(name = "created_dat") val createdDate: String?,
    @field:ColumnInfo(name = "updated_date") val updatedDate: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "wine_variety_id")
    var wineVarietyId: Long = 0L

    companion object {
        fun populateWineVariety(): List<WineVariety> {
            return arrayListOf(
                WineVariety("Tinto", "wine_red_color", getDateNow(), null),
                WineVariety("Rosé", "wine_rose_color", getDateNow(), null),
                WineVariety("Branco", "wine_white_color", getDateNow(), null),
                WineVariety("Espumante", "wine_sparkling_color", getDateNow(), null),
                WineVariety("Outro", "wine_other_color", getDateNow(), null)
            )
        }
    }
}