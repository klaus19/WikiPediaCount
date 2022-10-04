package com.example.wikipediacount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wikipediacount.api.WikiApiService
import com.example.wikipediacount.databinding.ActivityMainBinding
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val wikiApiService by lazy {
        WikiApiService.create()
    }

    var disposable:Disposable?=null

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnSearch.setOnClickListener {
                btnSearch(editSearch.text.toString())
            }
        }


    }

    private fun btnSearch(srsearch:String) {
        disposable = wikiApiService.hitCount("query","json","search",srsearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {result -> binding.txtSearchResult.text = "${result.query.searchInfo.totalHits}result found"},
                {error -> Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()}
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}