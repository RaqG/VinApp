package br.com.madebygallo.vinapp.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "wine",
    foreignKeys = [
        ForeignKey(
            entity = WineVariety::class,
            parentColumns = ["wine_variety_id"],
            childColumns = ["variety_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = GrapeVariety::class,
            parentColumns = ["grape_variety_id"],
            childColumns = ["grape_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = WineRegion::class,
            parentColumns = ["region_id"],
            childColumns = ["wine_region_id"],
            onDelete = ForeignKey.NO_ACTION
        )],
    indices = [Index(
        value = ["wine_id", "variety_id", "grape_id", "wine_region_id"],
        unique = true
    )]
)
@Parcelize
data class Wine(
    @field:ColumnInfo(name = "wine_name") val wineName: String?,
    @field:ColumnInfo(name = "producer") val producer: String?,
    @field:ColumnInfo(name = "importer") val importer: String,
    @field:ColumnInfo(name = "vintage") val vintage: Int,
    @field:ColumnInfo(name = "alcohol_content") val alcoholContent: Float,
    @field:ColumnInfo(name = "volume") val volume: Int,
    @field:ColumnInfo(name = "is_reserve") val isReserve: Int,
    @field:ColumnInfo(name = "rating") val rating: Float,
    @field:ColumnInfo(name = "wine_image") val wineImage: String?,
    @field:ColumnInfo(name = "barcode") val barcode: String?,
    @field:ColumnInfo(name = "created_date") val createdDate: String?,
    @field:ColumnInfo(name = "updated_date") val updatedDate: String?,
    @field:ColumnInfo(name = "wine_region_id") val regionId: Long?,
    @field:ColumnInfo(name = "grape_id") val grapeVarietyId: Long,
    @field:ColumnInfo(name = "variety_id") val wineVarietyId: Long
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "wine_id")
    var wineId: Long = 0L
}