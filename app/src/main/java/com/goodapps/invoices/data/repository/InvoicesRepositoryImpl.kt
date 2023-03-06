package com.goodapps.invoices.data.repository

import com.goodapps.invoices.data.models.GetInvoicesResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoicesRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : InvoicesRepository {

    override suspend fun getInvoices() = withContext(Dispatchers.IO) {
        httpClient.get("invoices.json").body<GetInvoicesResponse>()
    }

}