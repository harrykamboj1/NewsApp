package com.androiddevs.mvvmnewsapp.UI

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.Models.Article
import com.androiddevs.mvvmnewsapp.Models.NewsResponse
import com.androiddevs.mvvmnewsapp.Repository.NewsRepository
import com.androiddevs.mvvmnewsapp.UTILITY.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository : NewsRepository
): ViewModel() {
    val breakingNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

    val SearchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1

    init {

        getBreakingNews("in")

    }

    fun getBreakingNews(countryCode : String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)

        breakingNews.postValue(handleBreakingNewsResponse(response))
    }


    fun searchNews(searchQuery:String) = viewModelScope.launch {
        SearchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery,searchNewsPage)
        SearchNews.postValue(handleSearchNews(response))
    }


    private fun handleBreakingNewsResponse(response:Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {resultResponse ->

               return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())

    }


    private fun handleSearchNews(response:Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let {resultResponse ->

                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())

    }

    fun savedArticle(article: Article) = viewModelScope.launch {
        newsRepository.updateorInsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticles(article)
    }

    

}