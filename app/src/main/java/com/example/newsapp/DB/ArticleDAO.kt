package com.androiddevs.mvvmnewsapp.DB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.mvvmnewsapp.Models.Article

@Dao
interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateorInsert(article: Article):Long

    @Query("SELECT * FROM articles")
     fun getAllArticles():LiveData<List<Article>>

     @Delete
    suspend fun deleteArticle(article: Article)
}