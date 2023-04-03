package com.example.realestate.presentation.PropertyListing.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.library.presentation.articles.HomeScreenEvent
import com.example.library.presentation.articles.viewmodel.HomeScreenViewModel
import com.example.library.presentation.common.AppBarComponent
import com.example.library.presentation.common.EmptyScreen
import com.example.library.presentation.common.FailureScreen
import com.example.realestate.R
import com.example.realestate.domain.entities.Specification
import com.example.realestate.presentation.components.ConnectedComponent
import com.example.realestate.presentation.components.ErrorComponent
import com.example.realestate.presentation.components.NotConnectedComponent
import com.example.realestate.presentation.destinations.PropertyDetailsScreenDestination
import com.example.realestate.ui.theme.AppBg
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


/*
Composable for home screen
 */

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeScreenViewModel =
        hiltViewModel()
) {
    val state = homeViewModel.state
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = AppBg
    )
    val isRefreshing = SwipeRefreshState(isRefreshing = state.isLoading)

    Scaffold(
        modifier = Modifier,
        isFloatingActionButtonDocked = true,
        backgroundColor = AppBg,
        topBar = {
            AppBarComponent(
                title = stringResource(id = R.string.app_name),
                navigator = navigator
            )
        },
        content = { paddingValues ->

            SwipeRefresh(
                modifier = Modifier
                    .padding(paddingValues),
                state = isRefreshing,
                onRefresh = {
                    homeViewModel.onEvent(HomeScreenEvent.GetPropertyList)
                },
            ) {
                PropertyListingComponent(
                    itemList = state.propertyList,
                    onItemClicked = {
                        navigator.navigate(
                            PropertyDetailsScreenDestination(
                                propertyId = it.id
                            )
                        )
                    }
                )

                if (state.isError && state.propertyList.isEmpty() && state.showNetworkConnected) {
                    FailureScreen(onRetryClicked = {
                        homeViewModel.onEvent(
                            HomeScreenEvent.GetPropertyList
                        )
                    })
                }
                if (state.isEmpty) {
                    EmptyScreen(stringResource(R.string.article_empty_text))
                }
                if (state.showNetworkUnavailable) {
                    NotConnectedComponent()
                }

                if (state.showNetworkConnected) {
                    ConnectedComponent()
                }
            }


        })
}


/*
Article listing view
 */
@Composable
fun PropertyListingComponent(
    itemList: List<Specification>,
    onItemClicked: (Specification) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemList.size) { i ->
            val property = itemList[i]
            Box(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp)) {
                PropertyListItem(
                    property,
                    onItemClicked = onItemClicked
                )
            }
        }

    }
}