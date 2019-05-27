package org.hexworks.beryl.approximator

import org.hexworks.beryl.data.CharScale
import java.awt.Color
import java.awt.image.BufferedImage


class LightnessApproximator(private val charScale: CharScale) : Approximator {
    override fun subImageToChar(image: BufferedImage): Char {
        val lums = mutableListOf<Double>()
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                lums.add(relativeLuminance(Color(image.getRGB(x, y))))
            }
        }
        // Scale down the average to the charScale.
        // Formula: valueInSmallerScale / smallerScaleMax = value / normalScaleMax
        val final = lums.toDoubleArray().average() / 256 * charScale.scale.length
        return charScale.scale[final.toInt()]
    }

    private fun relativeLuminance(c: Color): Double {
        // Calculating relative luminance from RGB values.
        // https://en.wikipedia.org/wiki/Relative_luminance
        return 0.2126 * c.red.toDouble() + 0.7152 * c.green.toDouble() + 0.0722 * c.blue.toDouble()
    }
}
