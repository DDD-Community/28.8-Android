package com.ddd.carssok.feature.home

import androidx.annotation.ColorRes
import com.ddd.carssok.feature.record.navi.RecordAccidentDestination
import com.ddd.carssok.feature.record.navi.RecordDriveHistoryDestination
import com.ddd.carssok.feature.record.navi.RecordMaintenanceDestination
import com.ddd.carssok.feature.record.navi.RecordRefuelListDestination

data class HomeUiState(
    val car: Car,
    val totalDriveDistance: Int,
    val widgets: List<Widget>,
) {

    data class Car(
        val brand: String,
        val model: String,
        val refuel: String,
        val imgUrl: String,
    ) {
        val title: String = "$brand $model $refuel"

        companion object {
            val EMPTY = Car(
                brand = "",
                model = "",
                refuel = "",
                imgUrl = "",
            )
        }
    }

    sealed interface Widget {
        val widgetTitle: String
        val widgetSubTitle: String
        val list: List<Item>

        data class Item(
            val title: String,
            val subText: String,
            val category: Category,
        )

        sealed class Category {
            abstract val name: String
            abstract val route: String

            sealed class Level: Category() {
                object Normal: Level() {
                    override val name: String get() = "일반"
                    override val route: String get() = RecordMaintenanceDestination.route // TODO 점검기록 상세로 이동
                }
                object Emergency: Level() {
                    override val name: String get() = "긴급"
                    override val route: String get() = RecordMaintenanceDestination.route // TODO 점검기록 상세로 이동
                }
            }

            sealed class Record: Category() {
                @get:ColorRes
                abstract val color: Int

                object Accident: Record() {
                    override val name: String get() = "사고"
                    override val route: String get() = RecordAccidentDestination.route // TODO 사고기록 목록으로 변경
                    override val color: Int get() = R.color.home_widget_history_accident
                }
                object Maintenance: Record() {
                    override val name: String get() = "정비"
                    override val route: String get() = RecordMaintenanceDestination.route // TODO 점검기록 목록으로 변경
                    override val color: Int get() = R.color.home_widget_history_maintenance
                }
                object Refuel: Record() {
                    override val name: String get() = "주유"
                    override val route: String get() = RecordRefuelListDestination.route
                    override val color: Int get() = R.color.home_widget_history_refuel
                }
                object Drive: Record() {
                    override val name: String get() = "주행"
                    override val route: String get() = RecordDriveHistoryDestination.route
                    override val color: Int get() = R.color.home_widget_history_drive
                }
            }
        }

        data class History(
            override val list: List<Item>,
        ) : Widget {
            override val widgetTitle: String = "기록 히스토리"
            override val widgetSubTitle: String = "최근 30일 기준으로 보여드려요."

            companion object {
                val EMPTY = History(
                    list = emptyList()
                )
            }
        }

        data class Maintenance(
            override val list: List<Item>
        ): Widget {
            override val widgetTitle: String = "정비 시기를 확인해요"
            override val widgetSubTitle: String = ""

            companion object {
                val EMPTY = Maintenance(
                    list = emptyList()
                )
            }
        }
    }

    companion object {
        val EMPTY = HomeUiState(
            car = Car.EMPTY,
            totalDriveDistance = 0,
            widgets = emptyList()
        )

        val SAMPLE = HomeUiState(
            car = Car(
                brand = "현대",
                model = "아반떼",
                refuel = "하이브리드 F/L (7세대)",
                imgUrl = "",
            ),
            totalDriveDistance = 121200,
            widgets = listOf(
                Widget.History(
                    list = listOf(
                        Widget.Item("서울특별시 옥수동 독서당", "12.24일 (월)", category = Widget.Category.Record.Accident),
                        Widget.Item("와이퍼 블레이드", "12.24일 (월)", category = Widget.Category.Record.Maintenance),
                        Widget.Item("디디디최고주유소", "12.24일 (월)", category = Widget.Category.Record.Refuel),
                        Widget.Item("주행거리 34km", "12.24일 (월)", category = Widget.Category.Record.Drive),
                    )
                ),
                Widget.Maintenance(
                    list = listOf(
                        Widget.Item("-300km", "와이퍼 블레이드를 곧 교체하셔야 해요.", category = Widget.Category.Level.Normal),
                        Widget.Item("-1000km", "엔진 오일 필터 정비까지는 시간이 좀 남았지만, 슬슬 준비해 주세요!", category = Widget.Category.Level.Normal),
                        Widget.Item("+470km", "타이어 정비가 시급해요!", category = Widget.Category.Level.Emergency),
                    )
                ),
            ),
        )
    }
}