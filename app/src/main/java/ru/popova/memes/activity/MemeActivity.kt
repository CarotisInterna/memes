package ru.popova.memes.activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.popova.memes.R
import ru.popova.memes.dto.MemeDto

class MemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme)
        supportActionBar?.hide()

        val closeBtn: ImageButton = findViewById(R.id.close_btn)
        closeBtn.setOnClickListener {
            this@MemeActivity.finish()
        }

        val memeDto = intent?.getSerializableExtra("meme") as MemeDto

        val titleText: TextView = findViewById(R.id.meme_title)
        val descText: TextView = findViewById(R.id.meme_desc)
        val imageView: ImageView = findViewById(R.id.meme_image)
        val dateText: TextView = findViewById(R.id.meme_date)
        val favBtn: ImageButton = findViewById(R.id.meme_fav_btn)

        Glide.with(this).load(memeDto.photoUrl).into(imageView)
        titleText.text = memeDto.title
        descText.text = memeDto.description
        dateText.text = memeDto.createdDate.toString()
        favBtn.setImageResource(if (memeDto.isFavorite) R.drawable.fav_selected else R.drawable.fav)
        favBtn.setOnClickListener {
            //TODO("change meme fav")
        }


    }
}