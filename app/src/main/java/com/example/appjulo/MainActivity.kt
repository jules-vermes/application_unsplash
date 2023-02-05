package com.example.appjulo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.julobox.OnImageClickListener
import com.example.julobox.UnsplashAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity(), OnImageClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var photoList: MutableList<UnsplashPhoto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photoList = mutableListOf()
        recyclerView = findViewById(R.id.recyclerView)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = UnsplashAdapter(this, photoList,this)
        var initiate =0


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = gridLayoutManager.childCount

                val totalItemCount = gridLayoutManager.itemCount
                val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount -10
                    && firstVisibleItemPosition >= 0
                ) {
                    getPhotos()
                    initiate++
                }
            }
        })
        if (initiate==0)
        {
            getPhotos()
        }
    }

    override fun onImageClick(position: Int) {
        val intent = Intent(this, ImageDetailsActivity::class.java)
        intent.putExtra("IMAGE_URI",photoList[position].urls.regular)
        intent.putExtra("IMAGE_DESC",photoList[position].alt_description)
        intent.putExtra("IMAGE_CREATED",photoList[position].created_at)
        intent.putExtra("IMAGE_USER", photoList[position].user.name)
        intent.putExtra("IMAGE_USER_BIO", photoList[position].user.bio)
        intent.putExtra("LIKES",photoList[position].likes)
        startActivity(intent)
    }

    private fun getPhotos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(UnsplashAPI::class.java)
        val call = api.getPhotos("Client-ID w5B8Wi_Z2NMH4Qq08wY1Lq_kh_5CNSu8Zxar1pUlYbc",
            Random.nextInt(0,200), 100)

        call.enqueue(object : Callback<List<UnsplashPhoto>> {
            override fun onResponse(
                call: Call<List<UnsplashPhoto>>,
                response: Response<List<UnsplashPhoto>>
            ) {
                response.body()?.let {
                    photoList.addAll(it)
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<UnsplashPhoto>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to retrieve photos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}