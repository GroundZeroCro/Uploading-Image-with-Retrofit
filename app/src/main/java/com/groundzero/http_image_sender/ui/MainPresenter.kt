package com.groundzero.http_image_sender.ui

import com.groundzero.http_image_sender.data.ImageRepository

class MainPresenter : MainContract.MainPresenter {

    private val imageRepository: ImageRepository by lazy {
        ImageRepository()
    }

    override fun onSelectImage(filePath: String?): Boolean {
        if (filePath != null) {
            imageRepository.sendImage(filePath)
            return true
        }
        return false
    }
}