package com.ddd.carssok.feature.introduce

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ddd.carssok.core.designsystem.R
import com.ddd.carssok.core.designsystem.TypoStyle
import com.ddd.carssok.core.designsystem.component.TypoText

@Composable
fun IntroduceRoute() {
    IntroduceScreen()
}

@Composable
fun IntroduceScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary_bg))
    ) {
        item {
            Spacer(modifier = Modifier.height(48.dp))
        }
        item {
            IntroduceHeader()
        }
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(color = colorResource(id = R.color.secondary_bg))
            )
        }
        item {
            IntroduceBody()
        }
        item {
            IntroduceFooter()
        }
    }
}

@Composable
fun IntroduceFooter() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(28.dp))
        TypoText(text = "카쏙 성장을 위해\n의견이 전달하고 싶으시다면,", typoStyle = TypoStyle.HEADLINE_MEDIUM_18)
        Text(modifier = Modifier.padding(top = 20.dp, bottom = 28.dp), text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = with(LocalDensity.current) { Dp(12.toFloat()).toSp() },
                    color = colorResource(id = R.color.primary_text)
                )
            ) {
                append("언제든 ")
                withStyle(
                    SpanStyle(
                        color = colorResource(id = R.color.tertiary_text)
                    )
                ) {
                    append("carssok.cs@gmail.com")
                }
                append("으로 연락해 주세요!\n")
                append("여러분의 소중한 의견 하나가 더 나은 카쏙을 만들 수 있습니다.")
            }
        })
    }
}

@Composable
fun IntroduceBody() {
    val items = listOf(
        "주행 기록" to "내가 그날 얼마나 달렸더라.. 하고\n갸우뚱하신 적 있으시죠.필요할 때마다 주행을\n기록해두고, 차량 관리에 참고하세요!",
        "사고 기록" to "아차! 사고가 났을 때 당황해서\n사진만 찍어두고 관리 안 되시죠? 찍어둔 사진을\n날짜별로 잘 정리해서 필요할 때 확인하세요!",
        "정비 기록" to "항목마다 다른 점검 시기들을 어떻게 다 기억하나요?\n카쏙에서 주행을 하신 시점에 맞춰 \n각종 점검 시기를 안내드릴게요.",
        "주유 기록" to "아차! 사고가 났을 때 당황해서\n사진만 찍어두고 관리 안 되시죠? 찍어둔 사진을\n날짜별로 잘 정리해서 필요할 때 확인하세요!",
    )
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        TypoText(
            modifier = Modifier.padding(top = 32.dp, bottom = 14.dp),
            text = "이렇게 활용해주세요!",
            typoStyle = TypoStyle.HEADLINE_MEDIUM_18
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.secondary_bg)
            ),
            border = BorderStroke(1.dp, colorResource(id = R.color.button_disabled))
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 20.dp)
            ) {
                items.forEach { (title, body) ->
                    Row(
                        modifier = Modifier.padding(top = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_check_outline_24),
                            contentDescription = null
                        )
                        TypoText(
                            modifier = Modifier.padding(start = 3.dp),
                            text = title,
                            typoStyle = TypoStyle.HEADLINE_X_SMALL_14
                        )
                    }
                    TypoText(
                        modifier = Modifier.padding(top = 10.dp),
                        color = colorResource(id = R.color.secondary_text),
                        text = body,
                        typoStyle = TypoStyle.BODY_SMALL_12
                    )
                }
            }
        }
    }
}

@Composable
fun IntroduceHeader() {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TypoText(text = "카쏙", typoStyle = TypoStyle.HEADLINE_LARGE_20)
            Image(
                painter = painterResource(id = R.drawable.ic_clovar),
                contentDescription = null
            )
        }
        TypoText(text = "차 안의 모든 것을 관리한다", typoStyle = TypoStyle.HEADLINE_LARGE_20)
        Text(modifier = Modifier.padding(top = 28.dp), text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = with(LocalDensity.current) { Dp(12.toFloat()).toSp() },
                    color = colorResource(id = R.color.primary_text)
                )
            ) {
                append("차를 사랑하는 사람들에게\n")
                append("차를 타는 것 말고도 중요한 것은\n")
                withStyle(
                    SpanStyle(
                        color = colorResource(id = R.color.tertiary_text)
                    )
                ) {
                    append("차를 관리하는 방법을 아는 것")
                }
                append("이라고 생각했습니다.")
            }
        })
        Text(modifier = Modifier.padding(top = 20.dp), text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = with(LocalDensity.current) { Dp(12.toFloat()).toSp() },
                    color = colorResource(id = R.color.primary_text)
                )
            ) {
                append("계절마다 혹은 차가 달린 km마다\n")
                append("확인해야 하는 부품 교체, 점검 시기는 제각기 다 다릅니다.")
            }
        })

        Text(modifier = Modifier.padding(top = 20.dp, bottom = 32.dp), text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = with(LocalDensity.current) { Dp(12.toFloat()).toSp() },
                    color = colorResource(id = R.color.primary_text)
                )
            ) {
                append("바쁜 일상 속에서, ")
                withStyle(
                    SpanStyle(
                        color = colorResource(id = R.color.tertiary_text)
                    )
                ) {
                    append("카쏙이 차 관리를 함께 해드릴게요.\n")
                }
                append("기록만 해주시면, 그 이후는 카쏙이 책임지겠습니다.")
            }
        })
    }
}

@Preview
@Composable
fun IntroduceScreenPreview() {
    IntroduceScreen()
}