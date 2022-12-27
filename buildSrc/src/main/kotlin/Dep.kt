object Dep {

    object Modules {
        const val APP = ":app"

        const val CORE_DATASTORE = ":core-datastore"
        const val CORE_DESIGN_SYSTEM = ":core-designsystem"
        const val CORE_NAVIGATOR = ":core-navigator"
        const val CORE_NETWORK = ":core-network"
        const val CORE_DATA = ":core-data"

        const val FEATURE_HOME = ":feature:home"
        const val FEATURE_RECORD = ":feature:record"
        const val FEATURE_INTRODUCE = ":feature:introduce"
        const val FEATURE_ONBOARDING = ":feature:onboarding"
        const val CORE_MODEL = ":core-model"

    }

    object Gradle {
        const val GRADLE = "com.android.tools.build:gradle:7.3.1"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
        const val NAVIGATION_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.Navigation.version}"
    }

    object Kotlin {
        const val version = "1.7.20"
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${version}"
        const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.8.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.5.0"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val SPLASH = "androidx.core:core-splashscreen:1.0.0"

        object Compose {
            private const val version = "1.3.1"
            private const val material3_version = "1.0.1"
            const val compiler_version = "1.3.2"
            const val MATERIAL = "androidx.compose.material:material:$version"
            const val MATERIAL3 = "androidx.compose.material3:material3:$material3_version"
            const val MATERIAL3_WINDOW_SIZE = "androidx.compose.material3:material3-window-size-class:$material3_version"
            const val PREVIEW_SUPPORT = "androidx.compose.ui:ui-tooling-preview:$version"
            const val UI = "androidx.compose.ui:ui:$version"
            const val UI_TOOL = "androidx.compose.ui:ui-tooling:$version"
            const val RUNTIME = "androidx.compose.runtime:runtime:$version"
        }

        object Lifecycle {
            private const val version = "2.5.1"
            const val COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

        object Navigation {
            const val version = "2.5.2"
            const val COMPOSE = "androidx.navigation:navigation-compose:${version}"
            const val HILT_NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:1.0.0"
        }

        object DataStore {
            private const val version = "1.0.0"
            const val DATASTORE = "androidx.datastore:datastore-preferences:$version"
        }
    }

    object Hilt {
        const val version = "2.42"
        const val HILT = "com.google.dagger:hilt-android:${version}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${version}"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${version}"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${version}"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${version}"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${version}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:1.5.0"
    }

    object JunitTest {
        const val version = "4.13.2"
        const val JUNIT = "junit:junit:${version}"
        const val ANDROID_JUNIT_RUNNER = "AndroidJUnitRunner"
    }

    object AndroidXTest {
        const val ANDROID_JUNIT = "androidx.test.ext:junit:1.1.3"
        const val TEST_RUNNER = "androidx.test:runner:1.4.0"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }
}