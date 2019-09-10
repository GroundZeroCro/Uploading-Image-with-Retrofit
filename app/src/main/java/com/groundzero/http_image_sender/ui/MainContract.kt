package com.groundzero.http_image_sender.ui

interface MainContract {
    interface MainView {
        fun selectImage()
    }

    interface MainPresenter {
        fun onSelectImage(filePath: String?): Boolean
    }
}