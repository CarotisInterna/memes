package ru.popova.memes.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import ru.popova.memes.R


class AddImageDialogFragment(private val addImageOnClickListener: View.OnClickListener) :
    DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_add_image, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromGalleryBtn: Button = view.findViewById(R.id.from_gallery_btn)
        fromGalleryBtn.setOnClickListener {
            addImageOnClickListener.onClick(it)
            dialog?.dismiss()
        }
        val takePicBtn: Button = view.findViewById(R.id.take_pic_btn)
        takePicBtn.setOnClickListener {
            addImageOnClickListener.onClick(it)
            dialog?.dismiss()
        }
    }
}