package com.ddd.carssok.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

object FileUtils {
    private const val MAX_WIDTH = 1280
    private const val MAX_HEIGHT = 960


    // 최적화 bitmap 반환
    private fun decodeBitmapFromUri(uri: Uri, context: Context): Bitmap? {

        // 인자값으로 넘어온 입력 스트림을 나중에 사용하기 위해 저장하는 BufferedInputStream 사용
        val input = BufferedInputStream(context.contentResolver.openInputStream(uri))

        input.mark(input.available()) // 입력 스트림의 특정 위치를 기억

        var bitmap: Bitmap?

        BitmapFactory.Options().run {
            // inJustDecodeBounds를 true로 설정한 상태에서 디코딩한 다음 옵션을 전달
            inJustDecodeBounds = true
            bitmap = BitmapFactory.decodeStream(input, null, this)

            input.reset() // 입력 스트림의 마지막 mark 된 위치로 재설정

            // inSampleSize 값과 false로 설정한 inJustDecodeBounds를 사용하여 다시 디코딩
            inSampleSize = calculateInSampleSize(this)
            inJustDecodeBounds = false

            bitmap = BitmapFactory.decodeStream(input, null, this)
        }

        input.close()

        return bitmap

    }

    // 리샘플링 값 계산 : 타겟 너비와 높이를 기준으로 2의 거듭제곱인 샘플 크기 값을 계산
    private fun calculateInSampleSize(options: BitmapFactory.Options): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > MAX_HEIGHT || width > MAX_WIDTH) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= MAX_HEIGHT && halfWidth / inSampleSize >= MAX_WIDTH) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    fun optimizeBitmap(context: Context, uri: Uri): String? {
        try {
            val storage = context.cacheDir // 임시 파일 경로
            val fileName = String.format("%s.%s", UUID.randomUUID(), "jpg") // 임시 파일 이름

            val tempFile = File(storage, fileName)
            tempFile.createNewFile() // 임시 파일 생성

            // 지정된 이름을 가진 파일에 쓸 파일 출력 스트림을 만든다.
            val fos = FileOutputStream(tempFile)

            decodeBitmapFromUri(uri, context)?.apply {
                compress(Bitmap.CompressFormat.JPEG, 100, fos)
                recycle()
            } ?: throw NullPointerException()

            fos.flush()
            fos.close()

            return tempFile.absolutePath // 임시파일 저장경로 리턴

        } catch (e: Exception) {
            Log.e("eee", "FileUtil - ${e.message}")
        }

        return null
    }
}