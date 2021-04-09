package com.app.kucukbakkalapp.data.room

import androidx.room.*

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket ORDER BY id DESC")
    suspend fun getAllBasket() : List<Basket>

    @Query("SELECT * FROM basket where id= :id")
    suspend fun getBasketItem(id : String) : Basket?

    @Insert
    suspend fun addBasket(basket: Basket)

    @Update
    suspend fun updateBasket(basket: Basket)

    @Delete
    suspend fun deleteBasket(basket: Basket)

    @Query("DELETE FROM Basket")
    suspend fun clearTable()
}