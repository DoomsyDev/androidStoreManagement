//package ipca.example.storemanagement.data.repository
//
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.userProfileChangeRequest
//import com.google.firebase.database.FirebaseDatabase
//import ipca.example.storemanagement.data.local.dao.ItemDao
//import ipca.example.storemanagement.data.local.dao.UserDao
//import ipca.example.storemanagement.data.mapper.toDomainModel
//import ipca.example.storemanagement.data.mapper.toEntity
//import ipca.example.storemanagement.domain.model.UserModel
//import ipca.example.storemanagement.domain.repository.AuthRepository
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.tasks.await
//import javax.inject.Inject
//
//class AuthRepositoryImpl @Inject constructor(
//    private val firebaseAuth: FirebaseAuth,
//    private val firebaseDatabase: FirebaseDatabase,
//    private val userDao: UserDao,
//    private val itemDao: ItemDao
//) : AuthRepository {
//
//    // ESTA É A VERSÃO QUE FUNCIONA. SEM ERROS, SEM MERDAS.
//    override fun getCurrentUser(): Flow<UserModel?> = callbackFlow {
//        val authListener = FirebaseAuth.AuthStateListener { auth ->
//            val firebaseUser = auth.currentUser
//            if (firebaseUser != null) {
//                // Se o utilizador está logado, lança uma coroutine para observar o Room.
//                // A coroutine `launch` é automaticamente cancelada quando o `awaitClose` é chamado.
//                launch {
//                    userDao.getUserById(firebaseUser.uid).collect { userEntity ->
//                        // Envia os dados do Room para a UI.
//                        trySend(userEntity?.toDomainModel())
//                    }
//                }
//            } else {
//                // Se não há utilizador (logout), envia null.
//                trySend(null)
//            }
//        }
//        // Adiciona o listener ao Firebase Auth.
//        firebaseAuth.addAuthStateListener(authListener)
//
//        // SÓ PODE HAVER UM AWAITCLOSE. Este aqui remove o listener quando o flow é fechado.
//        // É tudo o que é preciso.
//        awaitClose { firebaseAuth.removeAuthStateListener(authListener) }
//    }
//
//    override suspend fun getCurrentUserId(): String? {
//        return firebaseAuth.currentUser?.uid
//    }
//
//    override suspend fun login(email: String, password: String): Result<Unit> {
//        return try {
//            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
//            val userId = authResult.user?.uid
//            if (userId != null) {
//                // Após o login, busca os dados do utilizador do Firebase e guarda-os no Room.
//                // O `callbackFlow` acima vai reagir a esta mudança no Room.
//                fetchAndCacheUser(userId)
//                Result.success(Unit)
//            } else {
//                Result.failure(Exception("Falha ao obter o ID do utilizador após o login."))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    override suspend fun register(name: String, email: String, password: String): Result<Unit> {
//        return try {
//            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
//            val firebaseUser = authResult.user!!
//
//            val profileUpdates = userProfileChangeRequest { displayName = name }
//            firebaseUser.updateProfile(profileUpdates).await()
//
//            val user = UserModel(id = firebaseUser.uid, name = name, email = email)
//            firebaseDatabase.getReference("users").child(firebaseUser.uid).setValue(user).await()
//
//            userDao.insertUser(user.toEntity()) // Guarda na cache local imediatamente
//
//            Result.success(Unit)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
//
//    override suspend fun logout() {
//        val userId = firebaseAuth.currentUser?.uid
//        firebaseAuth.signOut()
//        if (userId != null) {
//            // Limpa a cache local ao fazer logout.
//            userDao.clearAll()
//            itemDao.clearUserItems(userId)
//        }
//    }
//
//    private suspend fun fetchAndCacheUser(userId: String) {
//        try {
//            val snapshot = firebaseDatabase.getReference("users").child(userId).get().await()
//            snapshot.getValue(UserModel::class.java)?.let { userFromFirebase ->
//                userDao.insertUser(userFromFirebase.toEntity())
//            }
//        } catch (e: Exception) {
//            // Falha silenciosamente
//        }
//    }
//}
