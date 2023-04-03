package com.example.realestate.presentation.PropertyListing.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.realestate.domain.entities.Specification
import com.example.realestate.ui.theme.*
import java.text.NumberFormat
import java.util.*
import com.example.realestate.R
/*
Single list item in Property
 */
@Composable
fun PropertyListItem(
    specification: Specification,
    onItemClicked: (Specification) -> Unit
) {
    val euroFormat = NumberFormat.getCurrencyInstance(Locale("en", "EU"))
    euroFormat.currency = Currency.getInstance("EUR")


    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = specification.url).apply(block = fun ImageRequest.Builder.() {
            error(R.drawable.placeholder)
        }).build()
    )

    Card(modifier = Modifier) {

        Box(
            modifier = Modifier
                .background(color = CardBg)
                .fillMaxWidth()
                .clickable {
                    onItemClicked(specification)
                }

        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, bottom = 16.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier, elevation = 2.dp,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(16f / 9f)
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Card(shape = RoundedCornerShape(topStart = 4.dp, bottomEnd = 4.dp)) {
                        Box(
                            modifier = Modifier
                                .background(ErrorColor)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = specification.professional ?: "", color = FontColorWhite,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
                    Icon(
                        Icons.Default.LocationOn,
                        tint = Color.Gray,
                        contentDescription = "Favorite"
                    )
                    Text(
                        text = specification.city ?: "", color = SubFontColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = specification.propertyType ?: "",
                    color = SubFontColor,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = euroFormat.format(specification.price),
                        color = PrimaryColor,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                    )

                    Text(
                        text = "${specification.area} m²",
                        color = PrimaryColor,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End,
                    )
                }
            }

        }
    }

}