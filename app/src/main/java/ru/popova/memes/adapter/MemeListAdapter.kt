package ru.popova.memes.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.popova.memes.MemeModel
import ru.popova.memes.R
import ru.popova.memes.activity.MemeActivity

class MemeViewHolder(
    itemView: View,
    val memeImage: ImageView = itemView.findViewById(R.id.meme_image),
    val title: TextView = itemView.findViewById(R.id.meme_title),
    val favButton: ImageButton = itemView.findViewById(R.id.favButton),
    val shareButton: ImageButton = itemView.findViewById(R.id.shareButton)
) : RecyclerView.ViewHolder(itemView)


class MemeListAdapter(
    private val ctx: Context,
    private val list: List<MemeModel>
) :
    RecyclerView.Adapter<MemeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        return MemeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_meme, parent, false)!!
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        val value: MemeModel = list[position]
        Glide.with(ctx).load(value.photoUrl).into(holder.memeImage)
        holder.title.text = value.title
        val imageResource = if (value.isFavorite) R.drawable.fav_selected else R.drawable.fav
        holder.favButton.setImageResource(imageResource)
        holder.itemView.setOnClickListener {
            val intent = Intent(ctx, MemeActivity::class.java)
            intent.putExtra("meme", value)
            ctx.startActivity(intent)
        }
    }
}