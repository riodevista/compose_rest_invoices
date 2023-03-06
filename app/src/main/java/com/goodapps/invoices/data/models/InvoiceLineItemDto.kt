package com.goodapps.invoices.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InvoiceLineItemDto(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("quantity")
    val quantity: Int,
    @SerialName("priceinCents")
    val priceInCents: Int
)