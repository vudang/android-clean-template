package com.offeright.android.presenter.component
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.offeright.android.R
import com.offeright.android.presenter.theme.AppColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun AppPdfViewer(pdfUrl: String) {
    var currentPage by remember { mutableStateOf(0) }
    var totalPages by remember { mutableStateOf(0) }
    var pdfBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    LaunchedEffect(pdfUrl) {
        withContext(Dispatchers.IO) {
            try {
                // Download PDF
                val connection = URL(pdfUrl).openConnection() as HttpURLConnection
                connection.connect()
                val inputStream = connection.inputStream
                val file = File(context.cacheDir, "temp.pdf")
                FileOutputStream(file).use { output ->
                    inputStream.copyTo(output)
                }

                // Render PDF
                val renderer = PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY))
                totalPages = renderer.pageCount

                if (totalPages > 0) {
                    val page = renderer.openPage(currentPage)
                    val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    pdfBitmap = bitmap
                    page.close()
                }

                renderer.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppButtonText(
                    title = R.string.previous,
                    titleColor = AppColor.Dark,
                    enabled = currentPage > 0,
                    onClick = { if (currentPage > 0) currentPage-- }
                )

                AppButtonText(
                    title = R.string.next,
                    titleColor = AppColor.Dark,
                    enabled = currentPage < totalPages - 1,
                    onClick = { if (currentPage < totalPages - 1) currentPage++ }
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            pdfBitmap?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "PDF Page",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Preview(showSystemUi = true, device = Devices.PIXEL)
@Composable
private fun AppPdfPreview() {
    AppPdfViewer(pdfUrl = "https://offeright-be-dev.s3.amazonaws.com/offers/buyers/1722209309885-Iuqm3BS2Jb-contract-template-01.pdf")
}
