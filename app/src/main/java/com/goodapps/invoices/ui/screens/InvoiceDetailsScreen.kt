package com.goodapps.invoices.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goodapps.invoices.ui.models.common.InvoiceLineItemUiModel
import com.goodapps.invoices.ui.viewmodels.InvoicesDetailsViewModel

@Composable
fun InvoiceDetailsScreen(invoiceId: String) {
    val viewModel: InvoicesDetailsViewModel = hiltViewModel()
    val screenModel = viewModel.screenModel.collectAsState()
    val invoice = screenModel.value.invoice

    viewModel.prepareScreenModel(invoiceId)

    invoice?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Total: $${invoice.total / 100.0}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date: ${invoice.date}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${invoice.description ?: "N/A"}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Items:",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            InvoiceItemList(items = invoice.items)
        }
    }
}

@Composable
fun InvoiceItemList(items: List<InvoiceLineItemUiModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(items) { index, item ->
            InvoiceItem(item = item)
            if (index < items.size - 1) {
                Spacer(modifier = Modifier.height(7.5.dp))
                Divider(
                    modifier = Modifier.height(1.dp),
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(7.5.dp))
            } else {
                Spacer(modifier = Modifier.height(7.5.dp))
            }
        }
    }
}

@Composable
fun InvoiceItem(item: InvoiceLineItemUiModel) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = item.name,
            style = MaterialTheme.typography.body1,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${item.quantity} x $${item.priceInCents / 100.0}",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "$${item.quantity * item.priceInCents / 100.0}",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
