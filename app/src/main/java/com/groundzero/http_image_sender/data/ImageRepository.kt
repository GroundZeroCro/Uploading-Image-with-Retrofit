package com.groundzero.http_image_sender.data

import android.net.Uri
import com.groundzero.http_image_sender.utils.Logger
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ImageRepository {

    private val api: ImageApi = NetworkClient.getRetrofit().create(ImageApi::class.java)

    fun sendImage(filePath: String) {

        val call = api.uploadImage(getMultipartBody(filePath), getBodyDescription())
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Logger.up("Success: " + response.message())
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Logger.up("Error: " + t.message.toString())
            }
        })
    }

    private fun getBodyDescription(): RequestBody =
        RequestBody.create(MediaType.parse("text/plain"), "image-type")

    private fun getMultipartBody(filePath: String): MultipartBody.Part {
        val file = File(filePath)
        Logger.up(filePath)
        val requestBody = RequestBody.create(MediaType.parse("image/*"), file)
        return MultipartBody.Part.createFormData("file", file.name, requestBody)
    }

    companion object {
        const val PICK_IMAGE: Int = 1
    }
}