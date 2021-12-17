package utils

import java.io.BufferedReader
import java.io.File

fun BufferedReader.readAllCharsOneByOne(): String {
    val content = StringBuilder()

    var value: Int
    while (this.read().also { value = it } != -1) {
        content.append(value.toChar())
    }

    return content.toString()
}

fun File.searchFileExtension(extension: String): ArrayList<String> {
    val fileList = arrayListOf<String>()

    val listFile = this.listFiles()

    if (listFile.isNullOrEmpty().not()) {
        for (file in listFile) {
            val expansion = file.name.split(".").takeLast(1)[0]
            if (expansion == extension) fileList.add(file.name)
        }
    } else return fileList

    return fileList
}