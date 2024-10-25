package com.offeright.android.presenter.component.top_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.offeright.android.R
import com.offeright.android.presenter.component.AppButtonIcon
import com.offeright.android.presenter.component.AppIcon
import com.offeright.android.util.Dimens

@Composable
fun AppTopBarPhone(
    modifier: Modifier,
) {
    Row(
        modifier = modifier.padding(vertical = Dimens.dp16),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp0),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(
            iconDrawable = R.drawable.ic_logo_dark_phone
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppTopBarPhonePreview() {
    AppTopBarPhone(
        modifier = Modifier
            .fillMaxWidth()
    )
}