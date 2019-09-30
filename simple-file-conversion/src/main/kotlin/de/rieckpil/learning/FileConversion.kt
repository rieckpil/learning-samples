package de.rieckpil.learning

import java.io.File
import java.util.stream.Collectors

const val SOCIAL_MEDIA_TYPE = "F"

const val INPUT_FILE = "./reviewBlogPostList.txt"
const val PREFIX = "${SOCIAL_MEDIA_TYPE}_review"
const val PREFIX_TO_REMOVE = "#REVIEW:"
const val FOLDER_OF_IMAGES = "/home/rieckpil/Downloads/${SOCIAL_MEDIA_TYPE}_REVIEW/"

fun main() {

    val articles = File(INPUT_FILE).reader().readLines().stream().map {
        it.replace(PREFIX_TO_REMOVE, "").replace(" ", "").trim()
    }.collect(Collectors.toList())

    File(FOLDER_OF_IMAGES)
            .listFiles()
            .iterator()
            .forEachRemaining {
                val fileNumber = Integer.valueOf(it.name.split(".")[0])
                val newFileName = "$PREFIX${articles[fileNumber - 1]}.png"
                val newFile = File(FOLDER_OF_IMAGES + newFileName)
                it.renameTo(newFile)
            }
}
