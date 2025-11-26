//package ipca.example.storemanagement.di
//
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.FirebaseDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import ipca.example.storemanagement.data.local.dao.ItemDao
//import ipca.example.storemanagement.data.local.dao.UserDao
//import ipca.example.storemanagement.data.repository.AuthRepositoryImpl
//import ipca.example.storemanagement.data.repository.ItemRepositoryImpl
//import ipca.example.storemanagement.domain.repository.AuthRepository
//import ipca.example.storemanagement.domain.repository.ItemRepository
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RepositoryModule {
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(
//        firebaseAuth: FirebaseAuth,
//        firebaseDatabase: FirebaseDatabase,
//        userDao: UserDao,
//        itemDao: ItemDao
//    ): AuthRepository {
//        return AuthRepositoryImpl(firebaseAuth, firebaseDatabase, userDao, itemDao)
//    }
//
//    @Provides
//    @Singleton
//    fun provideItemRepository(
//        itemDao: ItemDao,
//        authRepository: AuthRepository,
//        // *** ADICIONAR ESTA DEPENDÊNCIA ***
//        firebaseDatabase: FirebaseDatabase
//    ): ItemRepository {
//        return ItemRepositoryImpl(itemDao, authRepository, firebaseDatabase)
//    }
//}
