package armqi.logically.su.domain.text

fun interface TextTransliterator {
    fun transliterate(value: String): String
}
