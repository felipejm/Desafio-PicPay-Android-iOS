package br.com.joffer.mango.infra.utils

import android.content.Context
import br.com.joffer.mango.App
import com.google.gson.GsonBuilder

object PreferenceHelper {

    private const val PREFERENCE_KEY = "app_preferences"

    fun write(key: String, data: Any) {
        val context = App.instance?.applicationContext!!
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

        val gson = GsonBuilder().create()
        val listString = gson.toJson(data)
        preference.edit().putString(key, listString).apply()
    }

    fun <T> read(key: String, clazz: Class<T>): T? {
        val context = App.instance?.applicationContext!!
        val preference = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)

        val gson = GsonBuilder().create()

        val json = preference.all[key] as? String
        return if(json == null){
            null
        }else{
            gson.fromJson(json, clazz)
        }
    }
}