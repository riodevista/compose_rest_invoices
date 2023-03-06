package com.goodapps.invoices.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goodapps.invoices.ui.models.common.InvoiceLineItemUiModel
import com.goodapps.invoices.ui.models.common.InvoiceUIModel
import com.goodapps.invoices.ui.viewmodels.InvoicesViewModel

@Composable
fun InvoicesScreen(onNavigateToDetail: (String) -> Unit) {
    val context = LocalContext.current
    val viewModel: InvoicesViewModel = hiltViewModel()
    val screenModel = viewModel.screenModel.collectAsState()

    if (screenModel.value.showProgress) {
        LoadingIndicator()
    } else {
        val invoices = screenModel.value.invoices
        if (invoices.isNotEmpty()) {
            InvoiceList(invoices = screenModel.value.invoices) {
                onNavigateToDetail(it.id)
            }
        } else {
            EmptyView()
        }
    }

    LaunchedEffect(key1 = screenModel.value.errorEvent) {
        screenModel.value.errorEvent?.getContentIfNotHandled()?.let(context::showToast)
    }
}

@Composable
fun InvoiceList(invoices: List<InvoiceUIModel>, onItemClick: (InvoiceUIModel) -> Unit) {
    LazyColumn {
        items(invoices) { invoice ->
            InvoiceItem(invoice = invoice, onItemClick = onItemClick)
        }
    }
}

@Composable
fun InvoiceItem(invoice: InvoiceUIModel, onItemClick: (InvoiceUIModel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onItemClick.invoke(invoice)
                }
        ) {
            Text(text = "Total: $${invoice.total / 100.0}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Date: ${invoice.date}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Items:")
            invoice.items.forEach { item ->
                InvoiceLineItem(item)
            }
        }
    }
}

@Composable
fun InvoiceLineItem(item: InvoiceLineItemUiModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Text(
            text = "${item.quantity}x ${item.name}"
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = "$${item.priceInCents / 100.0}",
            )
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun EmptyView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(modifier = Modifier.align(Alignment.Center), text = "EmptyView", fontSize = 24.sp)
    }
}


fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
