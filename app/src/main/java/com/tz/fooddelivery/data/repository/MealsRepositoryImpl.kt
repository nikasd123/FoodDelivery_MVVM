package com.tz.fooddelivery.data.repository

import com.tz.fooddelivery.data.local.dao.CategoriesDao
import com.tz.fooddelivery.data.local.dao.MealsDao
import com.tz.fooddelivery.data.local.entities.toDomain
import com.tz.fooddelivery.data.remote.api.MealsApi
import com.tz.fooddelivery.data.remote.dto.toDomain
import com.tz.fooddelivery.domain.models.Category
import com.tz.fooddelivery.domain.models.MealItem
import com.tz.fooddelivery.domain.models.toEntity
import com.tz.fooddelivery.domain.repository.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val mealsApi: MealsApi,
    private val mealsDao: MealsDao,
    private val categoriesDao: CategoriesDao
): MealsRepository {
    override suspend fun getDishes(): List<MealItem>? = withContext(Dispatchers.IO){
        return@withContext try {
            val dishesFromApi = mealsApi.getMeals().meals?.map { it.toDomain() }
            mealsDao.insertAll(dishesFromApi?.map { it.toEntity() } ?: emptyList())
            dishesFromApi
        } catch (e: Exception){
            mealsDao.getAllDishes().map { it.toDomain() }
        }
    }

    override suspend fun getCategories(): List<Category>? = withContext(Dispatchers.IO){
        try {
            val categoriesFromApi = mealsApi.getCategories().categories?.map { it.toDomain() }
            categoriesDao.insertAll(categoriesFromApi?.map { it.toEntity() } ?: emptyList())
            categoriesFromApi
        } catch (e: Exception){
            categoriesDao.getAllCategories().map { it.toDomain() }
        }
    }

    override suspend fun getDishesByCategory(category: String): List<MealItem>? = withContext(Dispatchers.IO){
        try {
            val dishesFromApi = mealsApi.getMealsByCategory(category).meals?.map { it.toDomain() }
            mealsDao.insertAll(dishesFromApi?.map { it.toEntity() } ?: emptyList())
            dishesFromApi
        } catch (e: Exception){
            mealsDao.getDishesByCategory(category).map { it.toDomain() }
        }
    }

}