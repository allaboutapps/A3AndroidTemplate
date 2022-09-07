package {{ cookiecutter.package_name }}.utils

import android.icu.text.NumberFormat
import java.math.BigDecimal
import java.math.BigInteger
import java.text.ParsePosition

// region format

// region formatInteger

@Suppress("NOTHING_TO_INLINE")
inline fun Byte.formatInteger(): String {
    return this.toLong().formatInteger()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Short.formatInteger(): String {
    return this.toLong().formatInteger()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Int.formatInteger(): String {
    return this.toLong().formatInteger()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Long.formatInteger(): String {
    val numberFormat: NumberFormat = NumberFormat.getIntegerInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun BigInteger.formatInteger(): String {
    val numberFormat: NumberFormat = NumberFormat.getIntegerInstance()
    return numberFormat.format(this)
}

// endregion

// region formatNumber

@Suppress("NOTHING_TO_INLINE")
inline fun Byte.formatNumber(): String {
    return this.toLong().formatNumber()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Short.formatNumber(): String {
    return this.toLong().formatNumber()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Int.formatNumber(): String {
    return this.toLong().formatNumber()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Long.formatNumber(): String {
    val numberFormat: NumberFormat = NumberFormat.getNumberInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Float.formatNumber(): String {
    return this.toDouble().formatNumber()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Double.formatNumber(): String {
    val numberFormat: NumberFormat = NumberFormat.getNumberInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun BigInteger.formatNumber(): String {
    val numberFormat: NumberFormat = NumberFormat.getNumberInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun BigDecimal.formatNumber(): String {
    val numberFormat: NumberFormat = NumberFormat.getNumberInstance()
    return numberFormat.format(this)
}

// endregion

// region formatPercent

@Suppress("NOTHING_TO_INLINE")
inline fun Short.formatPercent(): String {
    return this.toLong().formatPercent()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Byte.formatPercent(): String {
    return this.toLong().formatPercent()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Int.formatPercent(): String {
    return this.toLong().formatPercent()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Long.formatPercent(): String {
    val numberFormat: NumberFormat = NumberFormat.getPercentInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun Float.formatPercent(): String {
    return this.toDouble().formatPercent()
}

@Suppress("NOTHING_TO_INLINE")
inline fun Double.formatPercent(): String {
    val numberFormat: NumberFormat = NumberFormat.getPercentInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun BigInteger.formatPercent(): String {
    val numberFormat: NumberFormat = NumberFormat.getPercentInstance()
    return numberFormat.format(this)
}

@Suppress("NOTHING_TO_INLINE")
inline fun BigDecimal.formatPercent(): String {
    val numberFormat: NumberFormat = NumberFormat.getPercentInstance()
    return numberFormat.format(this)
}

// endregion

// endregion

// region parse

@PublishedApi
internal fun String.parseByNumberFormatOrNull(numberFormat: NumberFormat): Number? {
    // no need to trim trailing whitespace, NumberFormat *should* do that for us
    val parsePosition = ParsePosition(0)
    val number: Number? = numberFormat.parse(this, parsePosition)

    // NumberFormat.parse just tries to parse the *start* of the string, not the entire one.
    // We enforce that the entire string (excluding any trailing whitespace) must be consumed for it to be a
    // valid number. (e.g.: the string "123 abc" is not a valid number)
    if (parsePosition.errorIndex >= 0 || parsePosition.index < this.trimEnd().length) {
        return null
    }

    return number
}

@Suppress("NOTHING_TO_INLINE")
@PublishedApi
internal inline fun <T : Any> T?.orThrowNumberFormatException(): T {
    return (this ?: throw NumberFormatException())
}

// region parseInteger

/**
 * @return `null` if the string can't be parsed into an integer.
 * @see parseInteger
 * @see NumberFormat.parse
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.parseIntegerOrNull(): Long? {
    val numberFormat: NumberFormat = NumberFormat.getIntegerInstance()
    val number: Number? = this.parseByNumberFormatOrNull(numberFormat)
    return (number as? Long)
}

/**
 * @throws NumberFormatException If the string can't be parsed into an integer.
 * @see parseIntegerOrNull
 * @see NumberFormat.parse
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.parseInteger(): Long {
    return this.parseIntegerOrNull().orThrowNumberFormatException()
}

// endregion

// region parseNumber

/**
 * @return [Long] if possible, otherwise [Double], or `null` if the string can't be parsed into a number.
 * @see parseNumber
 * @see NumberFormat.parse
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.parseNumberOrNull(): Number? {
    val numberFormat: NumberFormat = NumberFormat.getNumberInstance()
    return this.parseByNumberFormatOrNull(numberFormat)
}

/**
 * @return [Long] if possible, otherwise [Double].
 * @throws NumberFormatException If the string can't be parsed into a number.
 * @see parseNumberOrNull
 * @see NumberFormat.parse
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.parseNumber(): Number {
    return this.parseNumberOrNull().orThrowNumberFormatException()
}

// endregion

// region parsePercent

/**
 * @return [Long] if possible, otherwise [Double], or `null` if the string can't be parsed into a number.
 * @see parsePercent
 * @see NumberFormat.parse
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.parsePercentOrNull(): Number? {
    val numberFormat: NumberFormat = NumberFormat.getPercentInstance()
    return this.parseByNumberFormatOrNull(numberFormat)
}

/**
 * @return [Long] if possible, otherwise [Double].
 * @throws NumberFormatException If the string can't be parsed into a number.
 * @see parsePercentOrNull
 * @see NumberFormat.parse
 */
@Suppress("NOTHING_TO_INLINE")
inline fun String.parsePercent(): Number {
    return this.parsePercentOrNull().orThrowNumberFormatException()
}

// endregion

// endregion
