package com.goodapps.invoices.domain.models

data class InvoiceLineItem(
    val id: String,
    val name: String,
    val quantity: Int,
    val priceInCents: Int
)