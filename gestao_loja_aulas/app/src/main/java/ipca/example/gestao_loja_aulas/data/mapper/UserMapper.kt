package ipca.example.gestao_loja_aulas.data.mapper

import ipca.example.gestao_loja_aulas.domain.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain(): User =
    User(
        id = uid,
        name = displayName,
        email = email
    )
