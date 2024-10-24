import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.offeright.android.R
import com.offeright.android.presenter.component.AppButtonIcon
import com.offeright.android.presenter.component.AppPdfViewer
import com.offeright.android.presenter.component.AppText
import com.offeright.android.presenter.theme.AppColor
import com.offeright.android.util.UrlType
import com.offeright.android.util.getUrlType

@Composable
fun AppRemoteMediaPreview(
    url: String,
    onDismiss: () -> Unit = {}
) {
    var urlType by remember { mutableStateOf<UrlType?>(null) }

    LaunchedEffect(url) {
        urlType = getUrlType(url)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.White),
        contentAlignment = Alignment.Center,
    ) {
        when (urlType) {
            UrlType.PDF -> {
                AppPdfViewer(pdfUrl = url)
            }
            UrlType.PHOTO -> {
                AppPdfViewer(pdfUrl = url)
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AppText(
                        rawText = "Unsupported URL type",
                        color = AppColor.Dark,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        AppButtonIcon(
            iconDrawable = R.drawable.ic_close,
            onClick = onDismiss,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
        )
    }
}


@Preview(showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
private fun AppMediaPreview() {
    AppRemoteMediaPreview(url = "https://images.pexels.com/photos/3680219/pexels-photo-3680219.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2")
}
