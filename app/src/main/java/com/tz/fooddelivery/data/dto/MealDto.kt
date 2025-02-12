package com.tz.fooddelivery.data.dto

import com.google.gson.annotations.SerializedName
import com.tz.fooddelivery.domain.models.DishItem

data class DishItemDto(
    @SerializedName("idMeal") val idMeal: String?,
    @SerializedName("strMealThumb") val dishImage: String?,
    @SerializedName("strMeal") val dishTitle: String?,
    @SerializedName("strCategory") val dishCategory: String?,
    @SerializedName("strIngredient1") val strIngredient1: String?,
    @SerializedName("strIngredient2") val strIngredient2: String?,
    @SerializedName("strIngredient3") val strIngredient3: String?,
    @SerializedName("strIngredient4") val strIngredient4: String?,
    @SerializedName("strIngredient5") val strIngredient5: String?,
    @SerializedName("strIngredient6") val strIngredient6: String?,
    @SerializedName("strIngredient7") val strIngredient7: String?,
    @SerializedName("strIngredient8") val strIngredient8: String?,
    @SerializedName("strIngredient9") val strIngredient9: String?,
    @SerializedName("strIngredient10") val strIngredient10: String?,
    @SerializedName("strIngredient11") val strIngredient11: String?,
    @SerializedName("strIngredient12") val strIngredient12: String?,
    @SerializedName("strIngredient13") val strIngredient13: String?
)

internal fun DishItemDto.toDomain(): DishItem =
    DishItem(
        id = idMeal ?: "",
        image = dishImage ?: "",
        title = dishTitle ?: "",
        category = dishCategory ?: "",
        description = listOfNotNull(
            strIngredient1,
            strIngredient2,
            strIngredient3,
            strIngredient4,
            strIngredient5,
            strIngredient6,
            strIngredient7,
            strIngredient8,
            strIngredient9,
            strIngredient10,
            strIngredient11,
            strIngredient12,
            strIngredient13
        ).joinToString(", ")
    )
