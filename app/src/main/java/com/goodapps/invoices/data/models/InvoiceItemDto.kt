package com.goodapps.invoices.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvoiceItemDto(
    @SerialName("id")
    val id: String,
    @SerialName("date")
    val date: String,
    @SerialName("description")
    val description: String? = null,
    @SerialName("items")
    val items: List<InvoiceLineItemDto>
)