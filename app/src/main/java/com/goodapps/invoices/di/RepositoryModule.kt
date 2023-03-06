package com.goodapps.invoices.di

import com.goodapps.invoices.data.repository.InvoicesRepository
import com.goodapps.invoices.data.repository.InvoicesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideInvoicesRepository(httpClient: HttpClient): InvoicesRepository {
        return InvoicesRepositoryImpl(httpClient)
    }
}
