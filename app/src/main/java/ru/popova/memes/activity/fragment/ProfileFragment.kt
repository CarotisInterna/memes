package ru.popova.memes.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.popova.memes.R
import ru.popova.memes.adapter.MemeListAdapter
import ru.popova.memes.task.LocalMemesLoadingTask
import ru.popova.memes.util.Failure
import ru.popova.memes.util.PreferencesService
import ru.popova.memes.util.Success


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profilePic: ImageView = view.findViewById(R.id.profile_pic)
        Glide.with(this).load(R.drawable.surf_edu)
            .into(profilePic)
        val profileName: TextView = view.findViewById(R.id.profile_name)
        val profileDesc: TextView = view.findViewById(R.id.profile_desc)
        val userInfo = PreferencesService(this.context!!).getUserInfo()
        profileName.text = userInfo.username
        profileDesc.text = userInfo.userDescription

        val recycler: RecyclerView = view.findViewById(R.id.local_memes)
        val list = when (val taskResult = LocalMemesLoadingTask().execute().get()) {
            is Success -> taskResult.value
            is Failure -> emptyList()
        }

        recycler.adapter = MemeListAdapter(this.context!!, list)
        recycler.adapter?.notifyDataSetChanged()
    }
}