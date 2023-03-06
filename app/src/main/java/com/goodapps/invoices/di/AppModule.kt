package com.goodapps.invoices.di

import com.goodapps.invoices.data.repository.InvoicesRepository
import com.goodapps.invoices.domain.interactors.InvoicesInteractor
import com.goodapps.invoices.domain.usecases.GetInvoicesUseCase
import com.goodapps.invoices.domain.usecases.GetInvoicesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetInvoicesUseCase(repository: InvoicesRepository): GetInvoicesUseCase {
        return GetInvoicesUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideInvoicesInteractor(getInvoicesUseCase: GetInvoicesUseCase): InvoicesInteractor {
        return InvoicesInteractor(getInvoicesUseCase)
    }

}
