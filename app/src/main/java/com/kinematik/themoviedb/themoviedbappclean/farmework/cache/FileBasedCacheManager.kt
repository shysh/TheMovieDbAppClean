package com.kinematik.themoviedb.themoviedbappclean.farmework.cache

import android.content.Context
import com.kinematik.themoviedb.themoviedbappclean.farmework.cache.entity.UserCacheEntity
import java.io.*

class FileBasedCacheManager {

    companion object {

        const val STORE_FILE_NAME = "UserStore"

        @Volatile
        private var cacheDirPath: String? =null

        fun getInstance(context: Context): String {
            return cacheDirPath ?: synchronized(this) {
                cacheDirPath ?: getCacheDir(context).also { cacheDirPath = it }
            }
        }

        private fun getCacheDir(context: Context): String {
            return context.cacheDir.absolutePath
        }
    }

    suspend fun getUserData(): UserCacheEntity? {
        return getSessionData()
    }

    suspend fun saveUserData(user: UserCacheEntity): Boolean {
        return saveSessionData(user)
    }

    suspend fun removeUserData(): Boolean {
        return clearSessionData()
    }


    private fun getSessionData(): UserCacheEntity? {
        var sessionData: UserCacheEntity? = null
        var objectIn: ObjectInputStream? = null
        try {

            val fileIn = FileInputStream(getFilePath(STORE_FILE_NAME))
            objectIn = ObjectInputStream(fileIn)
            sessionData = objectIn.readObject() as UserCacheEntity
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return sessionData
    }

    private fun saveSessionData(sessionData: UserCacheEntity?): Boolean {
        var objectOut: ObjectOutputStream? = null
        try {
            val fileOut = FileOutputStream(getFilePath(STORE_FILE_NAME))
            objectOut = ObjectOutputStream(fileOut)
            objectOut.writeObject(sessionData)
            fileOut.fd.sync()
            return true
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return false
    }

    private fun clearSessionData(): Boolean {

        val file = File(getFilePath(STORE_FILE_NAME))
        return if (file.exists() && file.canWrite()) file.delete() else false
    }

    private fun getFilePath(fileName: String): String {
        return "$cacheDirPath/$fileName"
    }
}