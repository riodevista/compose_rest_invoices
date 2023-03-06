package com.goodapps.invoices.data.repository

import com.goodapps.invoices.data.models.GetInvoicesResponse


interface InvoicesRepository {

    suspend fun getInvoices(): GetInvoicesResponse
}