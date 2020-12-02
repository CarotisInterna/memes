package ru.popova.memes.activity.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.popova.memes.R
import ru.popova.memes.adapter.MemeListAdapter
import ru.popova.memes.task.TaskManager
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success


class MemeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meme_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val refresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh)
        refresh.setOnRefreshListener {
            load(view)
            refresh.isRefreshing = false
        }
        load(view)
    }

    private fun load(view: View) {
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar_load_memes)
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            val task = TaskManager.memeTask.execute()
            val list = when (val result = task.get()) {
                is Success -> result.value
                is Failure -> emptyList()
            }

            val memeListAdapter = MemeListAdapter(
                view.context,
                list
            )
            val recyclerView: RecyclerView = view.findViewById(R.id.memes_list_recycler_view)
            val errorMessageView: TextView = view.findViewById(R.id.error_retreiving_memes)
            recyclerView.adapter = memeListAdapter
            if (list.isEmpty()) {
                recyclerView.visibility = View.GONE
                errorMessageView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                errorMessageView.visibility = View.GONE
            }
            progressBar.visibility = View.GONE
        }, 300)

    }

}