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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.popova.memes.R
import ru.popova.memes.adapter.MemeListAdapter
import ru.popova.memes.task.CheckDbIsEmptyTask
import ru.popova.memes.task.MemeFromApiLoadingTask
import ru.popova.memes.task.MemeFromDbLoadingTask
import ru.popova.memes.task.MemeLoadingTask
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import ru.popova.memes.util.favBtnClickListener


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
            load(view, MemeFromApiLoadingTask())
            refresh.isRefreshing = false
        }
        val memeLoadingTask = when {
            CheckDbIsEmptyTask().execute().get() -> MemeFromApiLoadingTask()
            else -> MemeFromDbLoadingTask()
        }

        load(view, memeLoadingTask)
    }

    private fun load(view: View, memeLoadingTask: MemeLoadingTask) {
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar_load_memes)
        progressBar.visibility = View.VISIBLE
        Handler().postDelayed({
            val recyclerView: RecyclerView = view.findViewById(R.id.memes_list_recycler_view)
            val errorMessageView: TextView = view.findViewById(R.id.error_retreiving_memes)
            val list = when (val result = memeLoadingTask.execute().get()) {
                is Success -> result.value
                is Failure -> emptyList()
            }
            errorMessageView.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            recyclerView.adapter =
                MemeListAdapter(requireContext(), list, favBtnClickListener)
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            progressBar.visibility = View.GONE
        }, 300)

    }

}