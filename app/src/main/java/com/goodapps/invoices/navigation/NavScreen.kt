package com.goodapps.invoices.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object Detail : NavScreen("detail/{itemId}") {
        fun createRoute(itemId: String): String {
            return "detail/$itemId"
        }
    }
}