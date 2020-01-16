package com.gnetop.sdk.customroom.db

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gnetop.sdk.customroom.model.FavouriteShoe
import com.gnetop.sdk.customroom.model.Shoe
import com.gnetop.sdk.customroom.model.User


/**
 * 创建数据库
 */
@Database(
    entities = [User::class, Shoe::class, FavouriteShoe::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    // 得到UserDao
    abstract fun userDao(): UserDao

    // 得到ShoeDao
    abstract fun shoeDao(): ShoeDao

    // 得到FavouriteShoeDao
    abstract fun favouriteShoeDao(): FavouriteDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context)
                    .also {
                        instance = it
                    }
            }
        }

        private fun buildDataBase(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, "CustomJetPackRoom")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        // 读取鞋的集合
                        /*val request = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)*/
                    }
                })
                .allowMainThreadQueries()
                .addMigrations(migration)
                .build()
        }

        /**
         * 数据库版本 1->2 user表格新增了age列
         */
        private var migration: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE User ADD COLUMN age integer")
            }
        }
    }


}