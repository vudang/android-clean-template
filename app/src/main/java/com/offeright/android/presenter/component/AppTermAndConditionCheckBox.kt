package com.offeright.android.presenter.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.offeright.android.R
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.AppFont
import com.offeright.android.util.Dimens

@Composable
fun AppTermsAndConditionsCheckbox(
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isChecked = remember { mutableStateOf(false) }
    val highlightStyle = SpanStyle(
        fontFamily = AppFont.fontFamily,
        fontSize = Dimens.sp12,
        color = AppColor.GreenLight,
        fontWeight = FontWeight.Bold
    )

    val normalStyle = SpanStyle(
        fontFamily = AppFont.fontFamily,
        fontSize = Dimens.sp12,
        color = AppColor.White,
        fontWeight = FontWeight.Normal
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp10)
    ) {
        AppIcon(
            iconDrawable = if (isChecked.value) R.drawable.ic_check_box_check else R.drawable.ic_check_box_uncheck,
            modifier = Modifier.clickable {
                isChecked.value = !isChecked.value
                onCheckedChange(isChecked.value)
            }
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = normalStyle,
                    block = { append("I agree to the ") }
                )
                withStyle(
                    style = highlightStyle,
                    block = { append("Terms and Conditions") }
                )
                withStyle(
                    style = normalStyle,
                    block = { append(" and ") }
                )
                withStyle(
                    style = highlightStyle,
                    block = { append("Privacy Policy") }
                )
            },
        )
    }
}

@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun AppTACPreview() {
    AppTermsAndConditionsCheckbox(onCheckedChange = {})
}
