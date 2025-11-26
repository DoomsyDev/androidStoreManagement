//package ipca.example.storemanagement.data.mapper
//
//import ipca.example.storemanagement.data.local.entity.ItemEntity
//import ipca.example.storemanagement.data.local.entity.UserEntity
//import ipca.example.storemanagement.domain.model.ItemModel
//import ipca.example.storemanagement.domain.model.UserModel
//
//// User Mappers
//fun UserEntity.toDomainModel(): UserModel {
//    return UserModel(
//        id = this.id,
//        name = this.name,
//        email = this.email
//    )
//}
//
//fun UserModel.toEntity(): UserEntity {
//    return UserEntity(
//        id = this.id,
//        name = this.name,
//        email = this.email
//    )
//}
//
//// Item Mappers
//fun ItemEntity.toDomainModel(): ItemModel {
//    return ItemModel(
//        id = this.id,
//        name = this.name,
//        description = this.description
//    )
//}
//
//fun ItemModel.toEntity(userId: String): ItemEntity {
//    return ItemEntity(
//        id = this.id,
//        name = this.name,
//        description = this.description,
//        userId = userId
//    )
//}
