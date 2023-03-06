package com.goodapps.invoices.domain.usecases

import com.goodapps.invoices.data.mappers.toDomain
import com.goodapps.invoices.data.models.InvoiceItemDto
import com.goodapps.invoices.data.repository.InvoicesRepository
import com.goodapps.invoices.domain.models.Invoice
import javax.inject.Inject


class GetInvoicesUseCaseImpl @Inject constructor(
    private val invoicesRepository: InvoicesRepository
) : GetInvoicesUseCase {

    override suspend fun execute(): Result<List<Invoice>> {
        return try {
            val invoices = invoicesRepository.getInvoices()
            Result.success(invoices.items.map(InvoiceItemDto::toDomain))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}