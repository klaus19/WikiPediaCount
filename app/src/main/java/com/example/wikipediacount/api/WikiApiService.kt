package com.example.wikipediacount.api

import com.example.wikipediacount.model.Model
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {



    @GET("api.php")
    fun hitCount(@Query("action") action:String,
                 @Query("format") format:String,
                 @Query("list") list:String,
                 @Query("srsearch")srsearch:String):
            Observable<Model.Result>

    companion object{
         fun create():WikiApiService{
             val retrofit =Retrofit.Builder()
                 .addCallAdapterFactory(
                     RxJava2CallAdapterFactory.create()
                 )
                 .addConverterFactory(
                     MoshiConverterFactory.create())
                 .baseUrl("https://en.wikipedia.org/w/")
                 .build()

             return  retrofit.create(WikiApiService::class.java)
         }



    }

}