package com.goodapps.invoices.ui.models.common

import com.goodapps.invoices.domain.models.Invoice
import com.goodapps.invoices.domain.models.InvoiceLineItem

data class InvoiceUIModel(
    val id: String,
    val date: String,
    val description: String?,
    val total: Long, // migrate to BigInteger when needed
    val items: List<InvoiceLineItemUiModel>
)

data class InvoiceLineItemUiModel(
    val id: String,
    val name: String,
    val quantity: Int,
    val priceInCents: Int
)


fun Invoice.toUIModel() =
    InvoiceUIModel(
        id = id,
        date = date,
        description = description,
        total = items.run {
            var totalSum = 0L
            forEach { totalSum += it.quantity * it.priceInCents }
            return@run totalSum
        },
        items = items.map(InvoiceLineItem::toUIModel)
    )

private fun InvoiceLineItem.toUIModel() = InvoiceLineItemUiModel(id, name, quantity, priceInCents)
