package ipca.example.gestao_loja_aulas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ipca.example.gestao_loja_aulas.data.repository.ProductRepositoryImpl
import ipca.example.gestao_loja_aulas.data.repository.UserRepositoryImpl
import ipca.example.gestao_loja_aulas.domain.repository.ProductRepository
import ipca.example.gestao_loja_aulas.domain.repository.UserRepository

import ipca.example.gestao_loja_aulas.domain.usecase.product.*
import ipca.example.gestao_loja_aulas.domain.usecase.user.*

import ipca.example.gestao_loja_aulas.domain.usecase.ProductUseCases
import ipca.example.gestao_loja_aulas.domain.usecase.UserUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // -----------------------------------------------------------------------
    // REPOSITORIOS
    // -----------------------------------------------------------------------
    @Provides
    @Singleton
    fun provideProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository = impl

    @Provides
    @Singleton
    fun provideUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository = impl


    // -----------------------------------------------------------------------
    // USE CASES DO PRODUTO
    // -----------------------------------------------------------------------
    @Provides
    @Singleton
    fun provideProductUseCases(
        repo: ProductRepository
    ): ProductUseCases {
        return ProductUseCases(
            addProduct = AddProductUseCase(repo),
            getProducts = GetProductsUseCase(repo),
            getProductById = GetProductByIdUseCase(repo),
            updateProduct = UpdateProductUseCase(repo),
            deleteProduct = DeleteProductUseCase(repo)
        )
    }

    // -----------------------------------------------------------------------
    // USE CASES DO USER
    // -----------------------------------------------------------------------
    @Provides
    @Singleton
    fun provideUserUseCases(
        repo: UserRepository
    ): UserUseCases {
        return UserUseCases(
            login = LoginUseCase(repo),
            register = RegisterUseCase(repo),
            logout = LogoutUseCase(repo),
            getCurrentUser = GetCurrentUserUseCase(repo)
        )
    }
}
