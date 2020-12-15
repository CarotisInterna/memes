package ru.popova.memes.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.popova.memes.R
import ru.popova.memes.activity.LoginActivity
import ru.popova.memes.adapter.MemeListAdapter
import ru.popova.memes.task.LocalMemesLoadingTask
import ru.popova.memes.task.LogoutTask
import ru.popova.memes.util.Failure
import ru.popova.memes.util.PreferencesService
import ru.popova.memes.util.Success
import ru.popova.memes.util.favBtnClickListener


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
        val userInfo = PreferencesService(requireContext()).getUserInfo()
        profileName.text = userInfo.username
        profileDesc.text = userInfo.userDescription
        val menuBtn: ImageView = view.findViewById(R.id.menu_btn)
        menuBtn.setOnClickListener {
            PopupMenu(requireContext(), it).apply {
                setOnMenuItemClickListener { item ->
                    when (item?.itemId) {
                        R.id.logout -> logoutFromApp()
                        R.id.about -> {
                            true
                        }
                        else -> false
                    }
                }
                inflate(R.menu.profile_items)
                show()
            }
        }
        val recycler: RecyclerView = view.findViewById(R.id.local_memes)
        val list = when (val taskResult = LocalMemesLoadingTask().execute().get()) {
            is Success -> taskResult.value
            is Failure -> emptyList()
        }

        recycler.adapter = MemeListAdapter(requireContext(), list, favBtnClickListener)
    }

    private fun logoutFromApp(): Boolean {
        val builder = AlertDialog.Builder(requireContext(), R.style.Profile_AlertDialog)
        builder.setMessage(R.string.logout_message)
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .setPositiveButton(R.string.logout) { _, _ -> logout() }
        val logoutDialog = builder.create()
        logoutDialog.show()
        return true
    }

    private fun logout() {
        when (val logoutResult = LogoutTask().execute().get()) {
            is Success -> {
                PreferencesService(requireContext()).clearAuthInfo()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            is Failure -> {
                Toast.makeText(
                    requireContext(),
                    logoutResult.reason.errorMessage,
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }
}