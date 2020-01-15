package com.gnetop.sdk.customroom.model

import androidx.room.*

/**
 * 用户表
 */
@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "user_account") val account: String,//账户
    @ColumnInfo(name = "user_pass") val password: String,//密码
    @ColumnInfo(name = "user_name") val name: String,//名称
    @Embedded val address: Address,//地址
    @Ignore val state: Int //状态只是临时用，所以不需要存储在数据库中
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0

}


/**
 * 地址表
 */
data class Address(val street: String, val state: Int, val city: String, val postCode: String)

/**
 * 喜欢的球鞋表
 */
@Entity(
    tableName = "fav_shoe",
    foreignKeys = [ForeignKey(
        entity = Shoe::class, parentColumns = ["id"],
        childColumns = ["shoe_id"]
    ), ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"])],
    indices = [Index("shoe_id")]
)
data class FavouriteShoe(
    @ColumnInfo(name = "shoe_id") val shoeID: Long,//外键 鞋子的ID
    @ColumnInfo(name = "user_id") val userID: Long, //外键 用户的ID
    @ColumnInfo(name = "fav_date") val favDate: String //创建日期
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
}

/**
 * 鞋表
 */
@Entity(tableName = "shoe")
data class Shoe(
    @ColumnInfo(name = "shoe_name") val shoeName: String,//鞋名
    @ColumnInfo(name = "shoe_description") val shoeDesc: String,//鞋描述
    @ColumnInfo(name = "shoe_price") val shoePrice: Double,//价格
    @ColumnInfo(name = "shoe_brand") val shoeBrand: String,//品牌
    @ColumnInfo(name = "shoe_imgUrl") val shoeUrl: String//图片
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
}