package com.goodapps.invoices.ui.models.screen_models

import com.goodapps.invoices.ui.models.Event
import com.goodapps.invoices.ui.models.common.InvoiceUIModel

data class InvoicesScreenModel(
    val invoices: List<InvoiceUIModel> = emptyList(),
    val showProgress: Boolean = false,
    val errorEvent: Event<String>? = null
)
