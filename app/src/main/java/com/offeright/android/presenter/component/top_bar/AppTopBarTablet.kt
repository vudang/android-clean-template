package com.offeright.android.presenter.component.top_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.offeright.android.R
import com.offeright.android.presenter.component.AppButtonIcon
import com.offeright.android.presenter.component.AppIcon
import com.offeright.android.presenter.component.AppText
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.Dimens

@Composable
fun AppTopBarTablet(
    modifier: Modifier,
    userName: String = "",
    favClick: () -> Unit = {},
    notifyClick: () -> Unit = {},
    profileClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp0),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(
            iconDrawable = R.drawable.ic_logo_dark_tablet
        )
        Spacer(modifier = Modifier.weight(1f))

        AppButtonIcon(
            modifier = Modifier.padding(),
            iconDrawable = R.drawable.ic_notify,
            onClick = notifyClick
        )
        AppButtonIcon(
            modifier = Modifier.padding(),
            iconDrawable = R.drawable.ic_love,
            onClick = favClick
        )
        AppButtonIcon(
            modifier = Modifier.padding(),
            iconDrawable = R.drawable.ic_circle_fill,
            onClick = profileClick
        )
        AppText(
            rawText = userName,
            color = AppColor.Dark,
            weight = FontWeight.Bold,
            size = Dimens.sp12
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun AppTopBarTabletPreview() {
    AppTopBarTablet(
        userName = "Username1234",
        modifier = Modifier
            .fillMaxWidth()
    )
}