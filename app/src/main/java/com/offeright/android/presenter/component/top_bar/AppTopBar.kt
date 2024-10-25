package com.offeright.android.presenter.component.top_bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.Dimens
import com.offeright.android.util.ScreenUtils.isTablet

@Composable
fun AppTopBar(
    modifier: Modifier,
) {
    Box(contentAlignment = Alignment.BottomStart) {
        if (isTablet()) {
            AppTopBarTablet(
                modifier = modifier,
            )
        } else {
            AppTopBarPhone(
                modifier = modifier,
            )
        }
        Divider(
            color = AppColor.Light,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.dp1)
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppTopBarPreview() {
    AppTopBar(
        modifier = Modifier
            .fillMaxWidth()
    )
}