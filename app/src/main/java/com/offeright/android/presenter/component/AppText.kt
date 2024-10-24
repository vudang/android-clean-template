package com.offeright.android.presenter.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.offeright.android.R
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.AppFont
import com.offeright.android.util.Dimens

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: Int = R.string.empty,
    rawText: String? = null,
    size: TextUnit = Dimens.sp14,
    color: Color = AppColor.White,
    weight: FontWeight = FontWeight.Normal,
    align: TextAlign = TextAlign.Left,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        modifier = modifier,
        text = rawText ?: stringResource(id = text),
        color = color,
        fontSize = size,
        fontWeight = weight,
        fontFamily = AppFont.fontFamily,
        textAlign = align,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppTextPreview() {
    AppText(
        text = R.string.signup_title,
        size = Dimens.sp18,
        color = AppColor.Black,
        weight = FontWeight.Bold,
        align = TextAlign.Center,
    )
}