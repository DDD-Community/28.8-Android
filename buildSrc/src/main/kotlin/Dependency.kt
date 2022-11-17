object Dependency {

    object Gradle {
        const val GRADLE = "com.android.tools.build:gradle:7.3.1"
        const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"
    }

    object Kotlin {
        const val version = "1.7.0"
        const val KOTLIN_STDLIB      = "org.jetbrains.kotlin:kotlin-stdlib:${version}"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.8.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.5.0"
        const val CONSTRAINT_LAYOUT     = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Compose {
            private const val version = "1.2.0"
            const val MATERIAL = "androidx.compose.material:material:$version"
            const val PREVIEW_SUPPORT = "androidx.compose.ui:ui-tooling-preview:$version"
            const val UI = "androidx.compose.ui:ui:$version"
            const val UI_TOOL = "androidx.compose.ui:ui-tooling:$version"
            const val RUNTIME = "androidx.compose.runtime:runtime:$version"
        }

        object Lifecycle {
            private const val version = "2.5.1"
            const val COMPOSE     = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        }

        object Navigation {
            private const val version = "2.5.2"
            const val COMPOSE   = "androidx.navigation:navigation-compose:${version}"
        }
    }

    object Hilt {
        const val version = "2.42"
        const val HILT           = "com.google.dagger:hilt-android:${version}"
        const val HILT_COMPILER  = "com.google.dagger:hilt-android-compiler:${version}"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val RETROFIT          = "com.squareup.retrofit2:retrofit:${version}"
        const val CONVERTER_GSON    = "com.squareup.retrofit2:converter-gson:${version}"
    }

    object OkHttp {
        private const val version = "4.9.1"
        const val OKHTTP                = "com.squareup.okhttp3:okhttp:${version}"
        const val LOGGING_INTERCEPTOR   = "com.squareup.okhttp3:logging-interceptor:${version}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:1.5.0"
    }

    object JunitTest {
        const val version = "4.13.2"
        const val JUNIT                 = "junit:junit:${version}"
        const val ANDROID_JUNIT_RUNNER  = "AndroidJUnitRunner"
    }

    object AndroidXTest {
        const val ANDROID_JUNIT     = "androidx.test.ext:junit:1.1.3"
        const val TEST_RUNNER       = "androidx.test:runner:1.4.0"
        const val ESPRESSO_CORE     = "androidx.test.espresso:espresso-core:3.4.0"
    }
}