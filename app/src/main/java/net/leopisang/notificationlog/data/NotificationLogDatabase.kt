package net.leopisang.notificationlog.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.leopisang.notificationlog.data.access.NotificationIconDAO
import net.leopisang.notificationlog.data.access.NotificationInfoDAO
import net.leopisang.notificationlog.data.entity.NotificationIcon
import net.leopisang.notificationlog.data.entity.NotificationInfo
import kotlin.random.Random


/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 01:54.
 */
@Database(entities = [NotificationInfo::class, NotificationIcon::class], version = 1)
abstract class NotificationLogDatabase : RoomDatabase() {
    abstract fun notificationInfoDao(): NotificationInfoDAO
    abstract fun notificationIconDao(): NotificationIconDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotificationLogDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): NotificationLogDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotificationLogDatabase::class.java,
                    "word_database"
                )
//                    .addCallback(NotificationLogTestCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


    private class NotificationLogTestCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var infoDao = database.notificationInfoDao()
                    var iconDao = database.notificationIconDao()

                    val icon1 = NotificationIcon("net.leopisang.tester", Random.Default.nextBytes(51))
                    iconDao.insertAll(icon1)

                    for (i in 1..5) {
                        val infoItem = NotificationInfo(0, "net.leopisang.tester", "PisanGG Tester"
                            , "Notif $i", "Test message notification number $i", 1617390602607 + (i*100))
                        infoDao.insertAll(infoItem)
                    }

                }
            }
        }
    }


}