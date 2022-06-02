package com.skoove.shared.commundata

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken
import timber.log.Timber

/**
 * function that converts data from json to object
 * @param classMapper the class of T.
 * @param <T> the type of the desired object.
 * @return an object of type T from the string.
 */
fun <T> String.fromJsonToObject(classMapper: Class<T>): T? {
    return try {
        Gson().fromJson(this, classMapper)
    } catch (e: JsonParseException) {
        Timber.e(e)
        null
    }
}


/**
 * inline function to convert json string to a TypeToken generic type
 * can be used in two ways like in the example below
 * val case1 = Gson().fromJsonToObjectType<SharedResponse<List<FuelPriceEntity>>>(result.data.toString())
 * val case2 : SharedResponse<List<FuelPriceEntity>> = Gson().fromJsonToObjectType(result.data.toString())
 */
inline fun <reified T> Gson.fromJsonToObjectType(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)