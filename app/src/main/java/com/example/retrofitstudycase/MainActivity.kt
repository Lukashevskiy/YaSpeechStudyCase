package com.example.retrofitstudycase

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentManager
import com.example.retrofitstudycase.placeholder.PlaceholderContent
import kotlinx.coroutines.delay
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private var fileName: String = ""

    private var recordButton: Button? = null
    private var recorder: MediaRecorder? = null

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)


    private val yaSpeechApi: YaSpeechApi by lazy { retrofit.create() }
    private val fragment: ItemFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as ItemFragment
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl("https://stt.api.cloud.yandex.net/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        findViewById<Button>(R.id.ansButton).setOnClickListener {
            yaSpeechApi.recognize(
                fId = "b1gm9uknsdls90cl8bdg",
                voice = RequestBody.create(
                    MediaType.parse("audio/*"),
                    this.assets.open("test_audio2.ogg").readBytes()
                ),
                auth = "Bearer " + "t1.9euelZqQm5nJmZOJk5eLkM-UjMbGiu3rnpWai5iWjcqPkZqNjMyJm8yRkJvl9PduNw9g-e9aMDTJ3fT3LmYMYPnvWjA0yQ.mZRFKIcswpBqcBRNN-zb-IVyaP8LFH2YRhFyw6KgfaBME9mNOsgckZ4zipxKNJlV1dq7U8nfkiSj_XV9zMi4CQ"
            ).enqueue(object: Callback<YaSpeechResponseDto>{
                override fun onResponse(call: Call<YaSpeechResponseDto>, response: Response<YaSpeechResponseDto>) {
                    Log.d("LOOOOL", response.toString())
                    if (response.code() == 200 && response.body() != null){
                        Toast.makeText(this@MainActivity, response.body()!!.result, Toast.LENGTH_LONG).show()
                        supportFragmentManager.beginTransaction().apply{
                            fragment.addAns(response.body()!!.result.toString())
                            fragment.addAns(" - Все говорят: \n\""+ response.body()!!.result.toString() + "\"\n А ты купи слона!")
                        }.commit()
                    }
                }

                override fun onFailure(call: Call<YaSpeechResponseDto>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}