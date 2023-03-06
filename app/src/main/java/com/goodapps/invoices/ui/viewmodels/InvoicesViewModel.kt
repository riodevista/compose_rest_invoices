package com.goodapps.invoices.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goodapps.invoices.R
import com.goodapps.invoices.core.resources.ResourcesProvider
import com.goodapps.invoices.domain.interactors.InvoicesInteractor
import com.goodapps.invoices.domain.models.Invoice
import com.goodapps.invoices.ui.models.Event
import com.goodapps.invoices.ui.models.common.toUIModel
import com.goodapps.invoices.ui.models.screen_models.InvoicesScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InvoicesViewModel @Inject constructor(
    private val invoicesInteractor: InvoicesInteractor,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _screenModel = MutableStateFlow(InvoicesScreenModel())
    val screenModel: StateFlow<InvoicesScreenModel>
        get() = _screenModel

    init {
        loadData()
    }

    fun loadData() {
        _screenModel.update { it.copy(showProgress = true) }
        viewModelScope.launch {
            val fetchInvoicesResult = invoicesInteractor.fetchInvoices()
            if (fetchInvoicesResult.isSuccess) {
                fetchInvoicesResult.getOrDefault(emptyList()).let {
                    _screenModel.update { prevModel ->
                        prevModel.copy(
                            invoices = it.map(Invoice::toUIModel),
                            showProgress = false,
                            errorEvent = null
                        )
                    }
                }
            } else {
                _screenModel.update {
                    it.copy(
                        showProgress = false,
                        errorEvent = Event(
                            resourcesProvider.getString(
                                R.string.default_error_message
                            )
                        )
                    )
                }
            }
        }
    }
}