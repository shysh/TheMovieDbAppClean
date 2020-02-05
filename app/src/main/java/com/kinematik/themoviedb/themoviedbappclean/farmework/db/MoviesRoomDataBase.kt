package com.kinematik.themoviedb.themoviedbappclean.farmework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.dao.MoviesDao
import com.kinematik.themoviedb.themoviedbappclean.farmework.db.entity.MovieDBEntity

@Database(
    entities = [MovieDBEntity::class],
    version = 1, exportSchema = false
)
abstract class MoviesRoomDataBase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    /*companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: MoviesRoomDataBase? = null

        fun getInstance(context: Context): MoviesRoomDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MoviesRoomDataBase {
            return Room.databaseBuilder(context, MoviesRoomDataBase::class.java, "movie-db.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .build()
        }
    }*/

}