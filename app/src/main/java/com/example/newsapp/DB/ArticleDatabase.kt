package com.androiddevs.mvvmnewsapp.DB

import android.content.Context
import androidx.room.*

import com.androiddevs.mvvmnewsapp.Models.Article


@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class ArticleDatabase : RoomDatabase() {


    abstract fun getArticleDao(): ArticleDAO

    companion object {

        @Volatile
        private var instance : ArticleDatabase? = null
        private val LOCK  = Any()

        operator fun invoke(context: Context)  = instance ?: synchronized(LOCK){

            instance ?: createDatabase(context).also {
                instance = it
            }

        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()


    }

}