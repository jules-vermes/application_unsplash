package com.example.appjulo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ImageDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_detail)

        val imageView = findViewById<ImageView>(R.id.detail_image_view)
        val textView1 = findViewById<TextView>(R.id.image_description)
        val textView2 = findViewById<TextView>(R.id.image_user)
        val textView3=findViewById<TextView>(R.id.image_user_bio)
        val textView4 = findViewById<TextView>(R.id.image_created)
        val textView5= findViewById<TextView>(R.id.likes)
        val imageUri = intent.getStringExtra("IMAGE_URI")
        val imageDesc = intent.getStringExtra("IMAGE_DESC")
        val imageCreated = intent.getStringExtra("IMAGE_CREATED")
        val imageUser = intent.getStringExtra("IMAGE_USER")
        val imageUserBio = intent.getStringExtra("IMAGE_USER_BIO")
        val imageLikes = intent.getIntExtra("LIKES",0)
        Picasso.get().load(imageUri).into(imageView)
        val realDate=convertTimestamp(imageCreated)


        textView1.text="Description de l'image: \n"+imageDesc
        textView2.text="Nom de l'artiste: \n"+ imageUser
        textView3.text="Bio de l'artiste: \n"+imageUserBio
        textView4.text="Crée le : \n"+realDate
        textView5.text="Nombre de likes : \n"+imageLikes.toString()
    }



    fun convertTimestamp(timestamp: String?): String?{
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val date = formatter.parse(timestamp)
        val timeFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US)
        timeFormatter.timeZone = TimeZone.getTimeZone("UTC+1")
        return timeFormatter.format(date)
    }
}

