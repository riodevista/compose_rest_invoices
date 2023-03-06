package com.goodapps.invoices.data.models

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class GetInvoicesResponse(
    @SerialName("items")
    val items: List<InvoiceItemDto>
)