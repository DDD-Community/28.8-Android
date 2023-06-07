@file:OptIn(ExperimentalMaterial3Api::class)

package com.ddd.carssok.feature.record.accident

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.Appbar
import com.ddd.carssok.core.designsystem.component.CarssokButton
import com.ddd.carssok.core.designsystem.component.Progress
import com.ddd.carssok.core.designsystem.component.TypoText
import com.ddd.carssok.core.designsystem.component.input.InputImageBox
import com.ddd.carssok.core.designsystem.component.input.InputTextBox
import com.ddd.carssok.core.util.FileUtils.optimizeBitmap
import com.ddd.carssok.feature.record.R
import kotlinx.coroutines.flow.collectLatest
import java.io.File

@Composable
fun RecordAccidentRoute(
    viewModel: RecordAccidentViewModel = hiltViewModel(),
    onClickedBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest {
            when (it) {
                is RecordAccidentViewModel.Event.OnSuccess -> onClickedBack.invoke()
            }
        }
    }
    RecordAccidentScreen(
        uiState,
        onClickedBack = onClickedBack,
        onUpdateDate = viewModel::updateDate,
        onChangedMemo = viewModel::updateMemo,
        onChangedLocationMemo = viewModel::updateLocationMemo,
        onPhotoChanged = viewModel::addPicture,
        onClickedRemovePhoto = viewModel::removePicture,
        onClickedSave = viewModel::saveRecordAccident
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordAccidentScreen(
    uiState: RecordAccidentViewModel.UiState,
    onClickedBack: () -> Unit,
    onUpdateDate: (String) -> Unit,
    onChangedMemo: (String) -> Unit,
    onChangedLocationMemo: (String) -> Unit,
    onPhotoChanged: (Uri) -> Unit,
    onClickedRemovePhoto: (Int) -> Unit,
    onClickedSave: (List<File>) -> Unit
) {
    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            onUpdateDate(
                "$selectedYear-${"%02d".format(selectedMonth + 1)}-$selectedDayOfMonth"
            )
        },
        uiState.year,
        uiState.month - 1,
        uiState.day
    )

    val context = LocalContext.current
    Scaffold(
        topBar = {
            Appbar(
                backButtonImageResource = com.ddd.carssok.core.designsystem.R.drawable.ic_arrow_back_circle_32,
                onClickedBack = onClickedBack
            )
        },
        containerColor = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_bg),
        floatingActionButton = {
            CarssokButton(
                modifier = Modifier.size(width = 160.dp, height = 56.dp),
                titleRes = R.string.record_accident_save,
                isEnabled = true,
                onClicked = {
                    onClickedSave(uiState.picture.map {
                        File(optimizeBitmap(context, it).orEmpty())
                    })
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                Progress()
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            item("record_accident_title") { RecordAccidentTitle() }
            item("record_accident_date") {
                RecordAccidentDate(
                    date = uiState.date,
                    onClickedDataPicker = {
                        datePicker.show()
                    }
                )
            }
            item("record_accident_location") {
                RecordAccidentLocation(
                    locationMemo = uiState.locationMemo,
                    onChangedLocationMemo = onChangedLocationMemo
                )
            }
            item("record_accident_gallery") {
                RecordAccidentGallery(
                    uiState.picture,
                    onPhotoChanged,
                    onClickedRemovePhoto
                )
            }
            item("record_accident_memo") { RecordAccidentMemo(memo = uiState.memo, onChangedMemo = onChangedMemo) }
            item { Spacer(modifier = Modifier.height(96.dp)) }
        }
    }
}

@Composable
fun RecordAccidentTitle() {
    Row {
        TypoText(
            modifier = Modifier.padding(top = 20.dp, bottom = 28.dp, start = 24.dp, end = 24.dp),
            text = stringResource(R.string.record_accident_title),
            typoStyle = TypoStyle.DISPLAY_SMALL_24
        )

    }
}

@Composable
fun RecordAccidentDate(date: String, onClickedDataPicker: () -> Unit) {
    InputTextBox(
        modifier = Modifier.clickable { onClickedDataPicker() },
        title = stringResource(id = R.string.record_accident_timestamp_title),
        hintText = stringResource(R.string.record_accident_timestamp_hint),
        intPutText = date,
        importanceCount = 1,
        inputTextFiledEnabled = false,
        leadingIcon = {
            Icon(
                painter = painterResource(id = com.ddd.carssok.core.designsystem.R.drawable.ic_calendar_24),
                tint = colorResource(id = com.ddd.carssok.core.designsystem.R.color.primary_text),
                contentDescription = null
            )
        },
        onInputTextChange = {

        }
    )
}

@Composable
fun RecordAccidentLocation(locationMemo: String, onChangedLocationMemo: (String) -> Unit) {
    InputTextBox(
        title = stringResource(id = R.string.record_accident_location_title),
        hintText = stringResource(R.string.record_accident_location_hint),
        intPutText = locationMemo,
        importanceCount = 1,
        onInputTextChange = onChangedLocationMemo
    )
}


@Composable
fun RecordAccidentMemo(memo: String, onChangedMemo: (String) -> Unit) {
    InputTextBox(
        title = stringResource(id = R.string.record_accident_memo_title),
        hintText = stringResource(R.string.record_accident_memo_hint),
        intPutText = memo,
        importanceCount = 1,
        onInputTextChange = onChangedMemo
    )
}

@Composable
fun RecordAccidentGallery(
    picture: List<Uri>,
    onPhotoChanged: (Uri) -> Unit,
    onClickedRemovePhoto: (Int) -> Unit
) {
    val context = LocalContext.current
    val takePhotoFromAlbumLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    onPhotoChanged(uri)
                } ?: run {
                    Toast.makeText(context, "empty", Toast.LENGTH_LONG).show()
                }
            } else if (result.resultCode != Activity.RESULT_CANCELED) {
                Toast.makeText(context, "실패", Toast.LENGTH_LONG).show()
            }
        }

    InputImageBox(
        title = stringResource(id = R.string.record_accident_image),
        titleHint = stringResource(id = R.string.record_accident_image_upload_max),
        cardTitle = stringResource(id = R.string.record_accident_image_upload_add),
        cardIconRes = com.ddd.carssok.core.designsystem.R.drawable.ic_calendar_24,
        saveList = picture,
        onClickedRemove = onClickedRemovePhoto,
        onClickedAdd = {
            takePhotoFromAlbumLauncher.launch(takePhotoFromAlbumIntent)
        }
    )
}


private val takePhotoFromAlbumIntent =
    Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
        type = "image/*"
        action = Intent.ACTION_GET_CONTENT
        putExtra(
            Intent.EXTRA_MIME_TYPES,
            arrayOf("image/jpeg", "image/png", "image/bmp", "image/webp")
        )
        putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
    }

@Suppress("DEPRECATION", "NewApi")
private fun Uri.parseBitmap(context: Context): Bitmap {
    return when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        true -> {
            val source = ImageDecoder.createSource(context.contentResolver, this)
            ImageDecoder.decodeBitmap(source)
        }
        else -> {
            MediaStore.Images.Media.getBitmap(context.contentResolver, this)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RecordAccidentScreenPreview() {
    RecordAccidentRoute(
        onClickedBack = {

        }
    )
}