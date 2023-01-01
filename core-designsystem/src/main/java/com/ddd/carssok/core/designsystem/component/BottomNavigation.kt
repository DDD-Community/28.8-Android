package com.ddd.carssok.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle


@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = colorResource(id = R.color.primary_bg),
        content = content
    )
}

@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = colorResource(id = R.color.primary_text),
            unselectedTextColor = colorResource(id = R.color.disable_text)
        )
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationPreview() {
    BottomNavigation {
        listOf("first tab", "second tab").forEach {
            BottomNavigationItem(
                selected = true,
                onClick = { },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = com.google.android.material.R.drawable
                                .ic_m3_chip_checked_circle
                        ),
                        contentDescription = null
                    )
                },
                label = {
                    TypoText(text = it, typoStyle = TypoStyle.BODY_X11_SMALL)
                })
        }
    }
}