package com.gnetop.sdk.customroom.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gnetop.sdk.customroom.model.FavouriteShoe

/**
 * 喜欢的鞋子的Dao
 */
@Dao
interface FavouriteDao {


    /**
     *  查询用户下面的FavouriteShoe
     */
    @Query("SELECT * From fav_shoe WHERE user_id = :userId")
    fun findFavouriteShoesByUserId(userId: String): LiveData<List<FavouriteShoe>>

    /**
     * 查询单个FavouriteShoe
     */
    @Query("SELECT * FROM fav_shoe WHERE user_id = :userId  AND shoe_id =:shoeId")
    fun findFavouriteShoeByUserIdAndShoeId(userId: Long, shoeId: Long): LiveData<FavouriteShoe?>

    /**
     * 插入单个FavouriteShoe
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteShoe(favouriteShoe: FavouriteShoe)

    /**
     * 删除单个FavouriteShoe
     */
    @Delete
    fun deleteFavouriteShoe(favouriteShoe: FavouriteShoe)

    /**
     *  删除多个FavouriteShoe
     */
    @Delete
    fun deleteFavouriteShoes(favouriteShoes: List<FavouriteShoe>)
}