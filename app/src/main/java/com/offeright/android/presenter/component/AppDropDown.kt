package com.offeright.android.presenter.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.offeright.android.R
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.Dimens
import com.offeright.android.util.ScreenUtils.isTablet


@Composable
fun AppDropDown(
    modifier: Modifier = Modifier,
    label: String = "",
    labelColor: Color = AppColor.Dark,
    initValue: String = "",
    isExpanded: Boolean = false,
    enabled: Boolean = true,
    options: List<String> = listOf(),
    onOptionSelected: (Int) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(isExpanded) }
    var selectedOption by remember { mutableStateOf(initValue) }
    val height = if (isTablet()) Dimens.dp57 else Dimens.dp46
    val spacing = if (isTablet()) Dimens.dp10 else Dimens.dp4

    LaunchedEffect(initValue) {
        selectedOption = initValue
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing),
        horizontalAlignment = Alignment.Start
    ) {
        AppText(
            rawText = label,
            size = Dimens.sp14,
            color = labelColor,
            weight = FontWeight.W600
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .appBorder()
                .padding(horizontal = Dimens.dp20)
                .height(height)
                .clickable {
                    if (enabled) {
                        expanded = !expanded
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                rawText = selectedOption,
                size = Dimens.sp14,
                color = AppColor.Dark,
                weight = FontWeight.W600
            )
            Spacer(modifier = Modifier.weight(1f))
            AppIcon(iconDrawable = R.drawable.ic_drop_down_gray)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (i in options.indices) {
                DropdownMenuItem(onClick = {
                    selectedOption = options[i]
                    expanded = false
                    onOptionSelected(i)
                }) {
                    AppText(
                        rawText = options[i],
                        color = AppColor.Dark,
                        size = Dimens.sp14
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
private fun AppDropDownPreview() {
    AppDropDown(
        label = "Select an option",
        initValue = "Option 1",
        isExpanded = true,
        options = listOf("Option 1", "Option 2", "Option 3")
    )
}