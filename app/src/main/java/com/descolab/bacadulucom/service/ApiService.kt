package com.descolab.bacadulucom.service

import com.descolab.bacadulucom.BuildConfig
import com.descolab.bacadulucom.service.response.ArticlesItem
import com.descolab.bacadulucom.service.responsee.ResponseArticle
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface ApiService {

    @GET("top-headlines?country=id&apiKey=${BuildConfig.API_KEY}")
    fun getTopHeadline(): Call<ResponseArticle<ArrayList<ArticlesItem>>>



}
