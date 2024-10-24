package com.offeright.android.presenter.module_template

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.offeright.android.presenter.component.AppContainerView

@Composable
fun TemplateScreenTablet() {
    AppContainerView(modifier = Modifier.padding()) {

    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun TemplateScreenTabletPreview() {
    TemplateScreenTablet()
}