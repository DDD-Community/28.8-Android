import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.ddd.carssok.core.designsystem.component.CommonDialog
import com.ddd.carssok.feature.record.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecordDriveSaveDialog(
    dialogState: MutableState<Boolean>,
    onClickedConfirm: () -> Unit,
) {
    if(dialogState.value) {
        CommonDialog(
            text = stringResource(id = R.string.record_drive_save_dialog_content),
            confirmButtonTitle = stringResource(id = R.string.record_drive_save_dialog_confirm_button),
            dismissButtonTitle = stringResource(id = R.string.record_drive_save_dialog_dismiss_button),
            onConfirmClicked = {
                dialogState.value = false
                onClickedConfirm()
            },
            onDismissClicked = {
                dialogState.value = false
            }
        )
    }
}

@Composable
fun RecordDriveBackHandler(
    enable: Boolean = false,
    navigateUp: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    var rememberExitDialog by remember { mutableStateOf(false) }

    if(rememberExitDialog) {
        CommonDialog(
            text = stringResource(id = R.string.record_exit_dialog_content),
            confirmButtonTitle = stringResource(id = R.string.record_exit_dialog_confirm_button_continue),
            dismissButtonTitle = stringResource(id = R.string.record_exit_dialog_dismiss_button_exit),
            onConfirmClicked = {
                rememberExitDialog = false
            },
            onDismissClicked = {
                rememberExitDialog = false
                navigateUp()
            }
        )
    }

    BackHandler(enabled = enable) {
        coroutineScope.launch {
            rememberExitDialog = true
        }
    }
}

