package de.rieckpil.learning

import java.io.File
import java.util.stream.Collectors

val blogType = BlogType.HOWTO

val INPUT_FILE = blogType.inputFile
val PREFIX = blogType.socialMediaType + blogType.prefix
val PREFIX_TO_REMOVE = blogType.prefixToRemove
val FOLDER_OF_IMAGES = "/home/rieckpil/Downloads/${blogType.socialMediaType}_${blogType.name.toUpperCase()}/"

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

enum class BlogType(val inputFile: String, val prefix: String, val prefixToRemove: String, val socialMediaType: String) {
    HOWTO("./howtoBlogPostList.txt", "_howto", "#HOWTO:", "T"),
    WHATIS("./whatisBlogPostList.txt", "_whatis", "#WHATIS:", "B"),
    REVIEW("./reviewBlogPostList.txt", "_review", "#REVIEW:", "B"),
}