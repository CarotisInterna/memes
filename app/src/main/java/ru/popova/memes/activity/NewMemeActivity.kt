package ru.popova.memes.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import ru.popova.memes.MemeModel
import ru.popova.memes.R
import ru.popova.memes.activity.fragment.AddImageDialogFragment
import ru.popova.memes.task.SaveMemeTask
import ru.popova.memes.util.Failure
import ru.popova.memes.util.PICTURE_URL
import ru.popova.memes.util.Success
import ru.popova.memes.util.isValid
import java.util.*


class NewMemeActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descEditText: EditText
    private lateinit var createBtn: Button
    private lateinit var newMemePictureView: ImageView
    private lateinit var deletePictureBtn: ImageButton
    private lateinit var addImageButton: FloatingActionButton

    private var pictureUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_meme)
        supportActionBar?.hide()

        val closeBtn: ImageButton = findViewById(R.id.close_btn)
        closeBtn.setOnClickListener {
            this@NewMemeActivity.finish()
        }

        createBtn = findViewById(R.id.create_btn)
        createBtn.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = identifyCreateBtnState()

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit
        }

        titleEditText = findViewById<TextInputLayout?>(R.id.new_meme_title).editText!!
        titleEditText.addTextChangedListener(textWatcher)
        descEditText = findViewById<TextInputLayout?>(R.id.new_meme_desc).editText!!
        descEditText.addTextChangedListener(textWatcher)

        createBtn.setOnClickListener { onClickCreate() }

        addImageButton = findViewById(R.id.add_image_btn)
        newMemePictureView = findViewById(R.id.new_meme_pic)
        deletePictureBtn = findViewById(R.id.delete_new_meme_pic_btn)

        deletePictureBtn.setOnClickListener { onClickDeletePicture() }

        addImageButton.setOnClickListener {
            val addImageFragment = AddImageDialogFragment { loadFromGalleryBtnClick() }
            addImageFragment.show(
                supportFragmentManager,
                AddImageDialogFragment::class.java.toString()
            )
        }

    }

    private fun onClickDeletePicture() {
        newMemePictureView.visibility = View.GONE
        deletePictureBtn.visibility = View.GONE
        pictureUrl = null
        addImageButton.isEnabled = true
        newMemePictureView.setImageResource(android.R.color.transparent)
        identifyCreateBtnState()
    }

    private fun loadFromGalleryBtnClick() {
        pictureUrl = PICTURE_URL
        Glide.with(this).load(pictureUrl).into(newMemePictureView)

        addImageButton.isEnabled = false
        newMemePictureView.visibility = View.VISIBLE
        deletePictureBtn.visibility = View.VISIBLE

        identifyCreateBtnState()
    }

    private fun onClickCreate() {
        val meme = MemeModel(
            UUID.randomUUID().toString(),
            titleEditText.editableText.toString(),
            descEditText.editableText.toString(),
            false,
            Calendar.getInstance().timeInMillis,
            pictureUrl!!,
            true
        )
        when (SaveMemeTask().execute(meme).get()) {
            is Success -> {
                Toast.makeText(
                    this@NewMemeActivity,
                    getString(R.string.meme_saved),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
            is Failure -> {
                Toast.makeText(
                    this@NewMemeActivity,
                    getString(R.string.meme_not_saved),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun identifyCreateBtnState() {
        createBtn.isEnabled =
            titleEditText.isValid() && descEditText.isValid() && pictureUrl != null
    }
}