package com.goodapps.invoices.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.goodapps.invoices.ui.screens.InvoiceDetailsScreen
import com.goodapps.invoices.ui.screens.InvoicesScreen


@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route
    ) {
        composable(NavScreen.Home.route) {
            InvoicesScreen(onNavigateToDetail = {
                navController.navigate(NavScreen.Detail.createRoute(it))
            })
        }

        composable(
            NavScreen.Detail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = requireNotNull(backStackEntry.arguments?.getString("itemId"))
            InvoiceDetailsScreen(itemId)
        }
    }
}