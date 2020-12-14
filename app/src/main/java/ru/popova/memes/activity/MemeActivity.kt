package ru.popova.memes.activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ru.popova.memes.MemeModel
import ru.popova.memes.R
import ru.popova.memes.task.SaveMemeTask
import ru.popova.memes.util.MEME
import ru.popova.memes.util.toDate

class MemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme)
        supportActionBar?.hide()

        val closeBtn: ImageButton = findViewById(R.id.close_btn)
        closeBtn.setOnClickListener {
            this@MemeActivity.finish()
        }

        val meme = intent?.getSerializableExtra(MEME) as MemeModel

        val titleText: TextView = findViewById(R.id.meme_title)
        val descText: TextView = findViewById(R.id.meme_desc)
        val imageView: ImageView = findViewById(R.id.meme_image)
        val dateText: TextView = findViewById(R.id.meme_date)
        val favBtn: ImageButton = findViewById(R.id.meme_fav_btn)

        Glide.with(this).load(meme.photoUrl).into(imageView)
        titleText.text = meme.title
        descText.text = meme.description
        dateText.text = toDate(meme.createdDate)
        favBtn.setImageResource(getIconResource(meme))
        favBtn.setOnClickListener {
            meme.isFavorite = !meme.isFavorite
            (it as ImageButton).setImageResource(getIconResource(meme))
            SaveMemeTask().execute(meme)
        }


    }

    private fun getIconResource(meme: MemeModel) =
        if (meme.isFavorite) R.drawable.fav_selected else R.drawable.fav
}