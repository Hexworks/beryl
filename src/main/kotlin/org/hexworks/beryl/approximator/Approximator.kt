package org.hexworks.beryl.approximator

import java.awt.image.BufferedImage


interface Approximator {
    fun subImageToChar(image: BufferedImage): Char
}
