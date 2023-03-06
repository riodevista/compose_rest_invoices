package com.goodapps.invoices.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodapps.invoices.domain.interactors.InvoicesInteractor
import com.goodapps.invoices.ui.models.common.toUIModel
import com.goodapps.invoices.ui.models.screen_models.InvoicesDetailsScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoicesDetailsViewModel @Inject constructor(
    private val invoicesInteractor: InvoicesInteractor
) : ViewModel() {

    private val _screenModel = MutableStateFlow(InvoicesDetailsScreenModel())
    val screenModel: StateFlow<InvoicesDetailsScreenModel>
        get() = _screenModel

    fun prepareScreenModel(invoiceId: String) {
        viewModelScope.launch {
            invoicesInteractor.getInvoiceOffline(invoiceId)?.let { invoice ->
                _screenModel.update { it.copy(invoice = invoice.toUIModel()) }
            }
        }
    }
}