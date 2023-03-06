package com.goodapps.invoices.domain.interactors

import com.goodapps.invoices.domain.models.Invoice
import com.goodapps.invoices.domain.usecases.GetInvoicesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoicesInteractor @Inject constructor(private val getInvoicesUseCase: GetInvoicesUseCase) {

    private val _invoicesListFlow = MutableStateFlow<List<Invoice>>(emptyList())
    val invoicesListFlow: StateFlow<List<Invoice>>
        get() = _invoicesListFlow

    suspend fun fetchInvoices(): Result<List<Invoice>> = withContext(Dispatchers.IO) {
        val result = getInvoicesUseCase.execute()
        if (result.isSuccess) {
            _invoicesListFlow.tryEmit(result.getOrDefault(emptyList()))
        }
        result
    }

    suspend fun getInvoiceOffline(invoiceId: String) = withContext(Dispatchers.IO) {
        _invoicesListFlow.value.firstOrNull { it.id == invoiceId }
    }
}