package com.tz.fooddelivery.domain.use_cases

import com.tz.fooddelivery.domain.models.MealItem
import com.tz.fooddelivery.domain.repository.MealsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMealsUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val getTranslatedTextUseCase: GetTranslatedTextUseCase
){
    suspend fun getDishes(): List<MealItem>? {
        val dishes = mealsRepository.getDishes()
        return dishes?.map { dishItem ->
            val translatedTitle = getTranslatedTextUseCase(dishItem.title)
            val translatedDescription = getTranslatedTextUseCase(dishItem.description)
            dishItem.copy(
                title = translatedTitle,
                description = translatedDescription
            )
        }
    }

    suspend fun getDishesByCategory(category: String): List<MealItem>? {
        val dishes = mealsRepository.getDishesByCategory(convertRussianToEnglishText(category))

        return dishes?.map { dishItem ->
            val translatedTitle = getTranslatedTextUseCase(dishItem.title)
            dishItem.copy(title = translatedTitle)
        }
    }

    private suspend fun convertRussianToEnglishText(text: String): String =
        getTranslatedTextUseCase.getEnglishText(text)

}