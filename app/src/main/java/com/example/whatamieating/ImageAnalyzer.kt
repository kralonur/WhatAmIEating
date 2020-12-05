package com.example.whatamieating

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.whatamieating.ml.LiteModelAiyVisionClassifierFoodV11
import com.example.whatamieating.util.YuvToRgbConverter
import org.tensorflow.lite.gpu.CompatibilityList
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.model.Model
import timber.log.Timber

typealias RecognitionListener = (recognition: List<Recognition>) -> Unit

private const val MAX_RESULT_DISPLAY = 3 // Maximum number of results displayed
private const val NUMBER_OF_THREADS = 4


class ImageAnalyzer(ctx: Context, private val listener: RecognitionListener) :
    ImageAnalysis.Analyzer {

    private val foodModel: LiteModelAiyVisionClassifierFoodV11 by lazy {

        val compatList = CompatibilityList()

        val options = if (compatList.isDelegateSupportedOnThisDevice) {
            Timber.d("This device is GPU Compatible ")
            Model.Options.Builder().setDevice(Model.Device.GPU).build()
        } else {
            Timber.d("This device is GPU Incompatible ")
            Model.Options.Builder().setNumThreads(NUMBER_OF_THREADS).build()
        }

        LiteModelAiyVisionClassifierFoodV11.newInstance(ctx, options)
    }

    override fun analyze(imageProxy: ImageProxy) {

        val items = mutableListOf<Recognition>()

        val tfImage = TensorImage.fromBitmap(toBitmap(imageProxy))

        val outputs = foodModel.process(tfImage)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score } // Sort with highest confidence first
            }.take(MAX_RESULT_DISPLAY) // take the top results

        for (output in outputs) {
            items.add(Recognition(output.label, output.score))
        }

        listener(items.toList())

        imageProxy.close()
    }

    private val yuvToRgbConverter = YuvToRgbConverter(ctx)
    private lateinit var bitmapBuffer: Bitmap
    private lateinit var rotationMatrix: Matrix

    @SuppressLint("UnsafeExperimentalUsageError")
    private fun toBitmap(imageProxy: ImageProxy): Bitmap? {

        val image = imageProxy.image ?: return null

        // Initialise Buffer
        if (!::bitmapBuffer.isInitialized) {
            Timber.d("Initialise toBitmap()")
            rotationMatrix = Matrix()
            rotationMatrix.postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
            bitmapBuffer = Bitmap.createBitmap(
                imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
            )
        }

        yuvToRgbConverter.yuvToRgb(image, bitmapBuffer)

        return Bitmap.createBitmap(
            bitmapBuffer,
            0,
            0,
            bitmapBuffer.width,
            bitmapBuffer.height,
            rotationMatrix,
            false
        )
    }

}