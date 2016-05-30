package xyz.jtmiles.beastforgw2.extensions


import java.io.BufferedReader
import java.util.*


val BufferedReader.lines: Iterator<String>
    get() = object : Iterator<String> {
        var line = this@lines.readLine()
        override fun next(): String {
            if (line == null)
                throw NoSuchElementException()
            val result = line!!                 // no idea why !! is needed here, compiler bug?
            line = this@lines.readLine()
            return result
        }
        override fun hasNext() = line != null
    }