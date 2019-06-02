package org.hexworks.beryl.examples

import org.hexworks.beryl.approximator.LightnessApproximator
import org.hexworks.beryl.converter.Converter
import org.hexworks.beryl.data.CharScale
import org.hexworks.beryl.data.FontSize


object Playground {
    @JvmStatic
    fun main(args: Array<String>) {
        val converter = Converter(
                Thread.currentThread().contextClassLoader.getResourceAsStream("images/fresh-rose-stem.jpg"),
                LightnessApproximator(CharScale.LIGHTNESS),
                FontSize(8, 14))

        print(converter.toAscii())
    }
}
