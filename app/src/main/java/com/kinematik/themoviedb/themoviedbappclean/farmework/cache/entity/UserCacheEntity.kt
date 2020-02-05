package com.kinematik.themoviedb.themoviedbappclean.farmework.cache.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserCacheEntity(@SerializedName("firstName") val firstName:String?,
                      @SerializedName("lastName") val lastName:String?,
                      @SerializedName("email") val email:String?,
                      @SerializedName("imageUrl") val imageUrl:String? = null):Serializable {
}