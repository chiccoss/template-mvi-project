package fr.sohayb.marvelapp.app.api.query

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Data(
    @SerialName("vehicle_template_id") val vehicleId: Int,
    @SerialName("vehicle_model_id") val modelId: Int?,
    @SerialName("plate") val registration: String
)