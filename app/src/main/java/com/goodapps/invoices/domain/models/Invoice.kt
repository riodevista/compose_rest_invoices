package com.goodapps.invoices.domain.models

data class Invoice(
    val id: String,
    val date: String,
    val description: String?,
    val items: List<InvoiceLineItem>
)