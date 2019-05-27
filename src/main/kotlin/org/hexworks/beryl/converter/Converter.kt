package org.hexworks.beryl.converter

import org.hexworks.beryl.approximator.Approximator
import org.hexworks.beryl.data.FontSize
import java.awt.image.RasterFormatException
import java.io.InputStream
import javax.imageio.ImageIO


class Converter(stream: InputStream,
                private val approximator: Approximator,
                private val fontSize: FontSize) {

    private val image = ImageIO.read(stream)

    fun toAscii(): String {
        val builder = StringBuilder()
        for (y in 0 until image.height step fontSize.height) {
            for (x in 0 until image.width step fontSize.width) {
                try {
                    val subImage = image.getSubimage(x, y, fontSize.width, fontSize.height)
                    builder.append(approximator.subImageToChar(subImage))
                } catch (e: RasterFormatException) {
                    // If we run out of image bounds we don't do anything (i.e. cropping image ¯\_(ツ)_/¯).
                    continue
                }
            }
            builder.append('\n')
        }
        return builder.toString()
    }
}
