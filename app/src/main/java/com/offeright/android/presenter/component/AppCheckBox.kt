package com.offeright.android.presenter.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.offeright.android.R
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.Dimens
import com.offeright.android.util.toggle

@Composable
fun AppCheckBox(
    modifier: Modifier = Modifier,
    text: String,
    textSize: TextUnit = Dimens.sp14,
    isChecked: Boolean,
    enable: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(isChecked) }

    Row(
        modifier = modifier
            .clickable {
                if (enable) {
                    checkedState.value = checkedState.value.toggle()
                    onCheckedChange(checkedState.value)
                }
            },
        verticalAlignment = Alignment.Top
    ) {
        AppIcon(
            iconDrawable = if (checkedState.value) R.drawable.ic_checkbox_rect_checked else R.drawable.ic_checkbox_rect_uncheck,
        )
        Spacer(modifier = Modifier.width(Dimens.dp10))
        AppText(
            rawText = text,
            size = textSize,
            color = AppColor.Black
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
fun AppCheckBoxPreview() {
    AppCheckBox(
        text = "Checkbox",
        isChecked = false,
        onCheckedChange = {}
    )
}