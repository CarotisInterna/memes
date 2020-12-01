package ru.popova.memes.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.popova.memes.R
import ru.popova.memes.adapter.MemeListAdapter
import ru.popova.memes.dto.MemeDto
import java.util.concurrent.ThreadLocalRandom


class MemeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meme_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.memes_list_recycler_view)
        recyclerView.adapter = MemeListAdapter(
            this.context!!,
            generateSequence(0) { it + 1 }
                .take(10)
                .map {
                    MemeDto(
                        it.toString(),
                        "Meme $it",
                        "Meme meme $it",
                        ThreadLocalRandom.current().nextBoolean(),
                        it,
                        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.minasjr.com.br%2Fwp-content%2Fthemes%2Fminasjr%2Fimages%2Fplaceholders%2F&psig=AOvVaw1gk4JNUnDVHwRQB2Hq4sWY&ust=1606935880868000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCOiR0uW8re0CFQAAAAAdAAAAABAD"
                    )
                }.toList()
        )
    }
}