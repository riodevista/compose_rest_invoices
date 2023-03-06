package com.goodapps.invoices.di

import com.goodapps.invoices.core.resources.AndroidResourcesProvider
import com.goodapps.invoices.core.resources.ResourcesProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourcesModule {

    @Singleton
    @Binds
    abstract fun bindResourcesProvider(resourcesProvider: AndroidResourcesProvider): ResourcesProvider

}
