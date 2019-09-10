package com.groundzero.http_image_sender.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.groundzero.http_image_sender.R
import com.groundzero.http_image_sender.data.ImageRepository.Companion.PICK_IMAGE
import com.groundzero.http_image_sender.utils.FileUtils
import com.groundzero.http_image_sender.utils.Logger
import com.groundzero.http_image_sender.utils.Permissions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.MainView {

    private val presenter: MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Permissions(this).read()
        select_image.setOnClickListener { selectImage() }
    }

    override fun selectImage() {
        val imageIntent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(imageIntent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            PICK_IMAGE -> {
                val validUri = presenter.onSelectImage(FileUtils.getPath(baseContext, data!!.data))
                if (!validUri) showToast(getString(R.string.invalid_uri))
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}