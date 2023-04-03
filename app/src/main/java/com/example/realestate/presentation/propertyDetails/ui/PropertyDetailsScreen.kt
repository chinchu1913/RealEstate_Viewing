package com.example.realestate.presentation.propertyDetails.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.library.presentation.common.AppBarComponent
import com.example.realestate.R
import com.example.realestate.presentation.components.DetailsRowItem
import com.example.realestate.presentation.propertyDetails.DetailsScreenEvent
import com.example.realestate.presentation.propertyDetails.viewmodel.DetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.text.NumberFormat
import java.util.*

/*
Single list item in PropertyDetail
 */
@Destination
@Composable
fun PropertyDetailsScreen(
    navigator: DestinationsNavigator,
    propertyId: Int,
    detailsViewModel: DetailsViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {

    LaunchedEffect(propertyId) {
        detailsViewModel.onEvent(DetailsScreenEvent.GetPropertyDetails(propertyId))
    }

    val state = detailsViewModel.state
    /*
Format value in Euro format
 */
    val euroFormat = NumberFormat.getCurrencyInstance(Locale("en", "EU"))
    euroFormat.currency = Currency.getInstance("EUR")
    val price = state.details?.price?.toFloat() ?: 0.0
    val formattedPrice = euroFormat.format(price)
    /*
Add Place holder image if url is null
   */
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = state.details?.url)
            .apply(block = fun ImageRequest.Builder.() {
               error(R.drawable.placeholder)
            }).build()
    )
    Scaffold(
        topBar = {
            AppBarComponent(
                title = stringResource(id = R.string.text_article_details),
                showBack = true,
                navigator = navigator
            )
        }, content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                DetailsRowItem(
                    stringResource(id = R.string.text_type),
                    "${state.details?.propertyType ?: "-"}",
                    stringResource(id = R.string.text_professional),
                    "${state.details?.professional ?: "-"}"
                )

                Spacer(modifier = Modifier.height(16.dp))
                DetailsRowItem(
                    stringResource(id = R.string.text_city),
                    "${state.details?.city ?: "-"}",
                    stringResource(id = R.string.text_price),
                    "$formattedPrice"
                )
                Spacer(modifier = Modifier.height(24.dp))
                DetailsRowItem(
                    stringResource(id = R.string.text_rooms),
                    "${state.details?.rooms ?: "-"}",
                    stringResource(id = R.string.text_bedrooms),
                    "${state.details?.bedrooms ?: "-"}"
                )
                Spacer(modifier = Modifier.height(24.dp))
                DetailsRowItem(
                    stringResource(id = R.string.text_area),
                    "${state.details?.area ?: "-"}m²",
                    stringResource(id = R.string.text_offer_type),
                    "${state.details?.offerType ?: "-"}%"
                )
            }

        })
}






