package com.gnetop.sdk.customroom.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gnetop.sdk.customroom.model.Shoe

@Dao
interface ShoeDao {

    /**
     * 增加一双鞋子
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)

    /**
     * 增加很多鞋子
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoes(shoes: List<Shoe>)

    /**
     * 删除一双鞋子
     */
    @Delete
    fun onDeleteShoe(shoe: Shoe)

    /**
     * 删除多双鞋子
     */
    @Delete
    fun onDeleteShoes(shoes: List<Shoe>)

    /**
     * 更新鞋子
     */
    @Update
    fun onUpdateShoe(shoe: Shoe)

    /**
     * 更新鞋子
     */
    @Update
    fun onUpdateShoes(shoes: Array<Shoe>)

    /**
     * 根据ID查询鞋子
     */
    @Query("SELECT * FROM shoe WHERE id=:id")
    fun findShoeById(id: Long): Shoe

    /**
     *  查询多个 通过品牌查询多款鞋
     */
    @Query("SELECT * FROM shoe WHERE id=:brand")
    fun findShoesByBrand(brand: String): List<Shoe>

    /**
     * 根据鞋名查询鞋子
     */
    @Query("SELECT * FROM shoe WHERE shoe_name like :name ORDER BY shoe_brand")
    fun findShoesByName(name: String): List<Shoe>

    /**
     * 配合LiveData 返回所有的鞋子
     */
    @Query("SELECT * FROM shoe")
    fun findAllShoes(): LiveData<List<Shoe>>

    /**
     * 配合LiveData 根据ID查询鞋子
     */
    @Query("SELECT * FROM shoe WHERE id=:id")
    fun findShoeByIdLD(id: Long): LiveData<Shoe>

    /**
     * 根据收藏结合 查询用户喜欢的鞋的集合 内联查询
     */
    @Query(
        " SELECT shoe.id,shoe.shoe_name,shoe.shoe_description,shoe.shoe_price,shoe.shoe_brand," +
                "shoe.shoe_imgUrl  FROM shoe  INNER JOIN fav_shoe ON fav_shoe.shoe_id = shoe.id " +
                " WHERE fav_shoe.user_id = :userId"
    )
    fun findShoeByUserId(userId: Long): LiveData<List<Shoe>>

}