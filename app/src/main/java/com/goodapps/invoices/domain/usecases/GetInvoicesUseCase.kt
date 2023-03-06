package com.goodapps.invoices.domain.usecases

import com.goodapps.invoices.domain.models.Invoice

interface GetInvoicesUseCase {
    suspend fun execute(): Result<List<Invoice>>
}
