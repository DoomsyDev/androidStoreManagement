//package ipca.example.storemanagement.di
//
//import android.content.Context
//import androidx.room.Room
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import ipca.example.storemanagement.data.local.AppDatabase
//import ipca.example.storemanagement.data.local.dao.ItemDao
//import ipca.example.storemanagement.data.local.dao.UserDao
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "store_management_db"
//        ).build()
//    }
//
//    @Provides
//    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
//
//    @Provides
//    fun provideItemDao(appDatabase: AppDatabase): ItemDao = appDatabase.itemDao()
//}
