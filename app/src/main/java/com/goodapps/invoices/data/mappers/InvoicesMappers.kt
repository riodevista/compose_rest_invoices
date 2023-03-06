package com.goodapps.invoices.data.mappers

import com.goodapps.invoices.data.models.InvoiceItemDto
import com.goodapps.invoices.data.models.InvoiceLineItemDto
import com.goodapps.invoices.domain.models.Invoice
import com.goodapps.invoices.domain.models.InvoiceLineItem
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun InvoiceItemDto.toDomain() = Invoice(
    id = this.id,
    date = this.date.toDate()?.toddMMyyy() ?: "-",
    description = this.description,
    items = this.items.map(InvoiceLineItemDto::toDomain)
)

fun InvoiceLineItemDto.toDomain() = InvoiceLineItem(
    id = this.id,
    name = this.name,
    quantity = this.quantity,
    priceInCents = this.priceInCents
)

fun String.toDate(): Date? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return try {
        dateFormat.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toddMMyyy() = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)
