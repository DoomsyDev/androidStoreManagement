package ipca.example.gestao_loja_aulas.data.mapper

import ipca.example.gestao_loja_aulas.data.local.ProductEntity
import ipca.example.gestao_loja_aulas.domain.model.Product

fun ProductEntity.toDomain(): Product =
    Product(
        id = id,
        name = name,
        description = description,
        price = price,
        quantity = quantity
    )

fun Product.toEntity(): ProductEntity =
    ProductEntity(
        id = id ?: "",
        name = name,
        description = description,
        price = price,
        quantity = quantity
    )
