package br.com.madebygallo.vinapp.model

import android.os.Build
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import br.com.madebygallo.vinapp.utils.getDateNow
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat.getDateInstance
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by RaqG on 14/07/2020.
 */

@Entity(
    tableName = "grape_variety",
    indices = [Index(value = ["grape_variety_name"], unique = true)]
)
@Parcelize
data class GrapeVariety(
    @field:ColumnInfo(name = "grape_variety_name") val grapeVarietyName: String,
    @field:ColumnInfo(name = "grape_variety_image") val grapeVarietyImage: String,
    @field:ColumnInfo(name = "created_date") val createdDate: String?,
    @field:ColumnInfo(name = "updated_date") val updatedDate: String?
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "grape_variety_id")
    var grapeVarietyId: Long = 0L

    companion object {
        fun populateGrapeVariety(): List<GrapeVariety> {
            return arrayListOf(
                GrapeVariety("Cabernet Sauvignon", "ic_grape_cabernet_sauvignon", getDateNow(), null),
                GrapeVariety("Carménère", "ic_grape_carmenere", getDateNow(), null),
                GrapeVariety("Chardonnay", "ic_grape_chardonnay", getDateNow(), null),
                GrapeVariety("Malbec", "ic_grape_malbec", getDateNow(), null),
                GrapeVariety("Merlot", "ic_grape_merlot", getDateNow(), null),
                GrapeVariety("Pinot Noir", "ic_grape_pinot_noir", getDateNow(), null),
                GrapeVariety("Sauvignon Blanc", "ic_grape_sauvignon_blanc", getDateNow(), null),
                GrapeVariety("Syrah", "ic_grape_syrah", getDateNow(), null),
                GrapeVariety("Tannat", "ic_grape_tannat", getDateNow(), null),
                GrapeVariety("Tempranillo", "ic_grape_tempranillo", getDateNow(), null)
            )
        }
    }
}