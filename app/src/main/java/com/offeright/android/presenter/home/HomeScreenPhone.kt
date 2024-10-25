package com.offeright.android.presenter.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.offeright.android.domain.model.User
import com.offeright.android.presenter.component.AppContainerView
import com.offeright.android.util.Dimens

@Composable
fun HomeScreenPhone(
    offerList: List<User>? = null,
    selectedOffer: (User) -> Unit
) {
    var segmentSelected by remember { mutableIntStateOf(0) }

    AppContainerView(
        modifier = Modifier.padding(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.dp20)
                .padding(top = Dimens.dp20)
                .verticalScroll(state = rememberScrollState())
        ) {
        }
    }
}

