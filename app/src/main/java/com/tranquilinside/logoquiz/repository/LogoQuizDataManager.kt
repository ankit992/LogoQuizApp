package com.tranquilinside.logoquiz.repository

import android.content.Context
import com.google.gson.Gson
import com.tranquilinside.logoquiz.entities.LogoQuizItem
import java.io.IOException
import com.google.gson.reflect.TypeToken


class LogoQuizDataManager(private val context: Context) : LogoQuizDataRepository {
    override fun getRandomLogoQuizData(): LogoQuizItem? {
        val json: String?
        try {
                json = context.readJsonAsset("logo.json")
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
        val listOfItems : List<LogoQuizItem> = Gson().fromJson(json, object : TypeToken<List<LogoQuizItem>>() {}.type)
        val randomPosition = (listOfItems.indices).random()
        return listOfItems[randomPosition]

    }

    @Throws(IOException::class)
    fun Context.readJsonAsset(fileName: String): String {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }
}