package com.gnetop.sdk.customlivedata.util

import java.util.regex.Pattern

/**
 * 转换工具类
 */
object ConvertUtil {

    /**
     * 转换
     *
     * @param input   输入的内容
     * @param isCheck 是否取消选择OX
     * @return 返回散列密钥
     */
    fun convert(input: String, isCheck: Boolean): String {
        val array: String
        val result = input(input, isCheck)
        if (result.length % 2 != 0) {
            return "123"
        }
        val mArray = IntArray(result.length / 2)
        for (index in 0 until result.length / 2) {
            val h = result.substring(index * 2, index * 2 + 2)
            mArray[index] = h.toInt(16)
        }
        array = convertTo64(mArray)
        return array
    }

    /**
     * 输入内容
     *
     * @param input   输入的内容
     * @param isCheck 是否取消选择OX
     * @return 返回取消OX后的值
     */
    private fun input(input: String, isCheck: Boolean): String {
        var input = input
        input = input.toUpperCase()
        if (isCheck) {
            val pattern = Pattern.compile("0X")
            val matcher = pattern.matcher(input)
            input = matcher.replaceAll("")
        }
        val pattern = Pattern.compile("[^A-Fa-f0-9]")
        val matcher = pattern.matcher(input)
        input = matcher.replaceAll("")
        return input
    }


    /**
     * 转换为64为的String字符串
     *
     * @param input int 数组
     * @return 转换后的散列密钥
     */
    private fun convertTo64(input: IntArray): String {
        val base64_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
        var ret = ""
        var i = 0
        var j = 0
        val char_array_3 = IntArray(3)
        val char_array_4 = IntArray(4)
        var in_len = input.size
        var pos = 0
        while (in_len-- != 0) {
            char_array_3[i++] = input[pos++]
            if (i == 3) {
                char_array_4[0] = char_array_3[0] and 0xfc shr 2
                char_array_4[1] =
                    (char_array_3[0] and 0x03 shl 4) + (char_array_3[1] and 0xf0 shr 4)
                char_array_4[2] =
                    (char_array_3[1] and 0x0f shl 2) + (char_array_3[2] and 0xc0 shr 6)
                char_array_4[3] = char_array_3[2] and 0x3f
                i = 0
                while (i < 4) {
                    ret += base64_chars[char_array_4[i]]
                    i++
                }
                i = 0
            }
        }
        if (i != 0) {
            j = i
            while (j < 3) {
                char_array_3[j] = 0
                j++
            }
            char_array_4[0] = char_array_3[0] and 0xfc shr 2
            char_array_4[1] = (char_array_3[0] and 0x03 shl 4) + (char_array_3[1] and 0xf0 shr 4)
            char_array_4[2] = (char_array_3[1] and 0x0f shl 2) + (char_array_3[2] and 0xc0 shr 6)
            char_array_4[3] = char_array_3[2] and 0x3f
            j = 0
            while (j < i + 1) {
                ret += base64_chars[char_array_4[j]]
                j++
            }
            while (i++ < 3) ret += '='
        }
        return ret
    }
}