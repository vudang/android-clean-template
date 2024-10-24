package com.offeright.android.presenter.user_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.offeright.android.R
import com.offeright.android.presenter.component.AppButtonRegular
import com.offeright.android.presenter.component.AppButtonStyle
import com.offeright.android.presenter.component.AppButtonText
import com.offeright.android.presenter.component.AppContainerView
import com.offeright.android.presenter.component.AppIcon
import com.offeright.android.presenter.component.AppText
import com.offeright.android.presenter.component.AppTextField
import com.offeright.android.presenter.component.AppTextFieldStyle
import com.offeright.android.presenter.component.ValidationRule
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.Dimens

@Composable
fun UserDetailScreenTablet(
    logoutClick: () -> Unit = {},
    closeClick: () -> Unit = {},
    changePassClick: () -> Unit = {}
) {
    AppContainerView(
        modifier = Modifier.padding(),
        customTopBar = { TopBar(logoutClick = logoutClick) }
    ) {
        ContentView(closeClick, changePassClick)
    }
}

@Composable
private fun TopBar(logoutClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp30),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp0),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(
            iconDrawable = R.drawable.ic_logo_dark_tablet
        )
        Spacer(modifier = Modifier.weight(1f))

        AppButtonText(
            title = R.string.user_detail_logout,
            titleColor = AppColor.Dark,
            onClick = logoutClick,
        )
    }
}

@Composable
private fun ContentView(closeClick: () -> Unit, changePassClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = Dimens.dp340),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderView()
        NameView()
        MailPhoneView()
        BottomButtons(closeClick = closeClick)

        AppButtonText(
            modifier = Modifier.padding(top = Dimens.dp20),
            title = R.string.change_password,
            titleColor = AppColor.Dark,
            onClick = changePassClick,
        )
    }
}

@Composable
private fun BottomButtons(
    viewModel: UserDetailViewModel = hiltViewModel(),
    closeClick: () -> Unit
) {
    val isDataChanged by remember { viewModel.isDataChanged }.collectAsState()

    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp20),
    ) {
        AppButtonRegular(
            text = R.string.close,
            style = AppButtonStyle.BORDER,
            onClick = closeClick,
            modifier = Modifier
                .weight(1f)
                .padding(top = Dimens.dp40)
        )
        AppButtonRegular(
            text = R.string.user_detail_save_change,
            onClick = { viewModel.updateUserData() },
            enable = isDataChanged,
            modifier = Modifier
                .weight(1f)
                .padding(top = Dimens.dp40)
        )
    }
}

@Composable
private fun HeaderView() {
    AppText(
        text = R.string.user_detail_title,
        size = Dimens.sp40,
        color = AppColor.Dark,
        weight = FontWeight.ExtraBold,
        align = TextAlign.Center,
        modifier = Modifier.padding(top = Dimens.dp30)
    )
}

@Composable
private fun NameView(viewModel: UserDetailViewModel = hiltViewModel()) {
    val user by remember { viewModel.user }.collectAsState()

    Row(
        modifier = Modifier.padding(top = Dimens.dp40),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp20)
    ) {
        AppTextField(
            label = R.string.signup_first_name,
            style = AppTextFieldStyle.BORDER,
            initValue = user?.firstName,
            validateRules = listOf(ValidationRule.REQUIRED),
            onValueChange = { viewModel.validateFirstName(it) },
            modifier = Modifier.weight(1f)
        )
        AppTextField(
            label = R.string.signup_last_name,
            style = AppTextFieldStyle.BORDER,
            initValue = user?.lastName,
            validateRules = listOf(ValidationRule.REQUIRED),
            onValueChange = { viewModel.validateLastName(it) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun MailPhoneView(viewModel: UserDetailViewModel = hiltViewModel()) {
    val user by viewModel.user.collectAsState()
    Row(
        modifier = Modifier.padding(top = Dimens.dp20),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp20)
    ) {
        AppTextField(
            label = R.string.signup_email,
            style = AppTextFieldStyle.BORDER,
            initValue = user?.email,
            enable = false,
            validateRules = listOf(ValidationRule.EMAIL, ValidationRule.REQUIRED),
            keyboardType = KeyboardType.Email,
            onValueChange = { },
            modifier = Modifier.weight(1f)
        )
        AppTextField(
            label = R.string.signup_mobile,
            style = AppTextFieldStyle.BORDER,
            initValue = user?.mobileNo,
            validateRules = listOf(ValidationRule.PHONE, ValidationRule.REQUIRED),
            keyboardType = KeyboardType.Number,
            enable = false,
            onValueChange = {  },
            modifier = Modifier.weight(1f)
        )
    }
    AppTextField(
        label = R.string.signup_address,
        style = AppTextFieldStyle.BORDER,
        initValue = user?.address,
        validateRules = listOf(ValidationRule.REQUIRED),
        onValueChange = { viewModel.validateAddress(it) },
        modifier = Modifier.padding(top = Dimens.dp20)
    )
}


@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun TemplateScreenTabletPreview() {
    UserDetailScreenTablet()
}