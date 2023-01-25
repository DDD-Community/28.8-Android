package com.ddd.carssok.core.designsystem.component.input

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.ddd.carssok.core.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
object InputTextDefaults {

    @Composable
    fun DefaultColors(
        state: InputState = InputState.EMPTY,
        textColor: Color = colorResource(id = R.color.primary_text),
        focusedBorderColor: Color = colorResource(id = R.color.divider_secondary),
        unfocusedBorderColor: Color = colorResource(id = R.color.divider_primary),
        errorBorderColor: Color = colorResource(id = R.color.error_text),
        cursorColor: Color = colorResource(id = R.color.primary_text),
        errorCursorColor: Color = colorResource(id = R.color.error_text),
        containerColor: Color = colorResource(id = R.color.secondary_bg),
        errorContainerColor: Color = colorResource(id = R.color.error_bg),
    ) = TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        containerColor = if(state == InputState.ERROR) errorContainerColor else containerColor,
    )

    @Composable
    fun GroupBoxColors(
        state: InputState = InputState.EMPTY,
        textColor: Color = colorResource(id = R.color.primary_text),
        focusedBorderColor: Color = colorResource(id = R.color.divider_secondary),
        unfocusedBorderColor: Color = colorResource(id = R.color.divider_primary),
        errorBorderColor: Color = colorResource(id = R.color.error_text),
        cursorColor: Color = colorResource(id = R.color.primary_text),
        errorCursorColor: Color = colorResource(id = R.color.error_text),
        containerColor: Color = colorResource(id = R.color.primary_bg),
        errorContainerColor: Color = colorResource(id = R.color.error_bg),
    ) = TextFieldDefaults.outlinedTextFieldColors(
        textColor = textColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        cursorColor = cursorColor,
        errorCursorColor = errorCursorColor,
        containerColor = if(state == InputState.ERROR) errorContainerColor else containerColor,
    )

    @Composable
    fun DropDownColors(
        textColor: Color = colorResource(id = R.color.primary_text),
        focusedBorderColor: Color = colorResource(id = R.color.divider_secondary),
        unfocusedBorderColor: Color = colorResource(id = R.color.divider_primary),
        errorBorderColor: Color = colorResource(id = R.color.error_text),
        cursorColor: Color = colorResource(id = R.color.primary_text),
        containerColor: Color = colorResource(id = R.color.secondary_bg),
    ) = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
        textColor = textColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        cursorColor = cursorColor,
        containerColor = containerColor,
    )

    @Composable
    fun DropDownInGroupColors(
        textColor: Color = colorResource(id = R.color.primary_text),
        focusedBorderColor: Color = colorResource(id = R.color.divider_secondary),
        unfocusedBorderColor: Color = colorResource(id = R.color.divider_primary),
        errorBorderColor: Color = colorResource(id = R.color.error_text),
        cursorColor: Color = colorResource(id = R.color.primary_text),
        containerColor: Color = colorResource(id = R.color.primary_bg),
    ) = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
        textColor = textColor,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        errorBorderColor = errorBorderColor,
        cursorColor = cursorColor,
        containerColor = containerColor,
    )
}