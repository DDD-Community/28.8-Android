package com.ddd.carssok.core.data.repository.onboarding

import com.ddd.carssok.core.data.model.OnBoardingDetailModelEntity
import com.ddd.carssok.core.data.model.OnBoardingModelEntity
import javax.inject.Inject

class FakeOnBoardingRepositoryImpl @Inject constructor(

): OnBoardingRepository {
    override suspend fun getModels(brand: String): List<OnBoardingModelEntity> {
        return listOf(
            OnBoardingModelEntity(
                title = "제네시스 G90 (4세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "기본형",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F4c1761%2FF66B79FB7D695898713EE125D1257421D607E2F2F774687741_2TPM",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "기본형 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F4c1761%2FF66B79FB7D695898713EE125D1257421D607E2F2F774687741_2TPM",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 G90 (3세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "럭셔리",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fd0ffad%2FED789C9DC3EF437840D980FE112AD8080A8E2C1A672B6E184F_52CN",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "럭셔리 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fd0ffad%2FED789C9DC3EF437840D980FE112AD8080A8E2C1A672B6E184F_52CN",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "프리미엄 럭셔리 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fd0ffad%2FED789C9DC3EF437840D980FE112AD8080A8E2C1A672B6E184F_52CN",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "프레스티지 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fd0ffad%2FED789C9DC3EF437840D980FE112AD8080A8E2C1A672B6E184F_52CN",
                    ),
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 G90 LWB (4세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "기본형 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F2fa6c8%2F0846EDFD186C298DC8CAE486350FC32CEDE412C9FD88C28DE0_41HW",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 GV80 (1세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2023년형",
                        title = "new\\n2WD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F280e5d%2F9346DCFD3B82C9A5BF86D64FD88C063D5D0DA95A5BA7F95AA4_3AMO",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2023년형",
                        title = "new\\nAWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F280e5d%2F9346DCFD3B82C9A5BF86D64FD88C063D5D0DA95A5BA7F95AA4_3AMO",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2023년형",
                        title = "AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F0932bb%2FE1B4EB217914532ECD2F25583CDF81EF739C208F0B1E79F3DA_30NH",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 G80 (3세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2023년형",
                        title = "2WD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F0932bb%2FE1B4EB217914532ECD2F25583CDF81EF739C208F0B1E79F3DA_30NH",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 일렉트리파이드 G80 (3세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "EV",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F2fe971%2F90DAF8BDA337EC84FDEC051BD3DD5CEA1C42A71BD3AF43E2E4_72O0",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 GV70 (1세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "2WD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F75468c%2F597F3ED588F8E944FA614C936DDC05863EF87256FA8C41B4D7_3DR7",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F75468c%2F597F3ED588F8E944FA614C936DDC05863EF87256FA8C41B4D7_3DR7",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2020년형",
                        title = "2.0T 어드밴스드 2WD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F7c36f7%2F102898F2331C8C7F32164961CFBE9C95FCAF0AF2B65C807F8B_5TGG",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 일렉트리파이드 GV70 (1세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "EV AW",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F12c5a2%2FF7FD87C2BCDC4853E94CCD0B9900844BFEE332D0A9DDF229F1_2ZFA",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 G70 F/L (1세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "2.0 터보",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fa4bd27%2FE4346ECC737AE54F5F760A92545CECBCA6686F281D8155AB0E_3IS8",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "2.0 터보 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fa4bd27%2FE4346ECC737AE54F5F760A92545CECBCA6686F281D8155AB0E_3IS8",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "2.0 터보",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fa4bd27%2FE4346ECC737AE54F5F760A92545CECBCA6686F281D8155AB0E_3IS8",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2021년형",
                        title = "2.0 터보 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Fa4bd27%2FE4346ECC737AE54F5F760A92545CECBCA6686F281D8155AB0E_3IS8",
                    )
                )
            ),
            OnBoardingModelEntity(
                title = "제네시스 GV60 (1세대)",
                detailModels = listOf(
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "스탠다드 2WD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F2fa6c8%2F0846EDFD186C298DC8CAE486350FC32CEDE412C9FD88C28DE0_41HW",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "스탠다드 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F6b47d2%2FC11118A6BD338EAFC15F410E28FD75E46D3E45A0790654D087_550F",
                    ),
                    OnBoardingDetailModelEntity(
                        year = "2022년형",
                        title = "퍼포먼스 AWD",
                        thumbnail = "https://img1.daumcdn.net/thumb/R380x0/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F6b47d2%2FC11118A6BD338EAFC15F410E28FD75E46D3E45A0790654D087_550F",
                    )
                )
            ),
        )
    }
}