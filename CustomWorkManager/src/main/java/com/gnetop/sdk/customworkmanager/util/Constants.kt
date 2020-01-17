package com.gnetop.sdk.customworkmanager.util

object Constants {
    const val DENY_PERMISSION_ITEM = "DENY_PERMISSION_ITEM"

    // 后台工作详细通知的通知通道名称
    /***通道名称*/
    @JvmField
    val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
        "Verbose WorkManager Notifications"

    /***通道描述*/
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"

    /***通知标题*/
    @JvmField
    val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"

    /***通道ID*/
    const val CHANNEL_ID = "VERBOSE_NOTIFICATION"

    /***通知ID*/
    const val NOTIFICATION_ID = 1

    // The name of the image manipulation work
    const val IMAGE_MANIPULATION_WORK_NAME = "image_manipulation_work"

    // Other keys
    const val OUTPUT_PATH = "blur_filter_outputs"
    const val KEY_IMAGE_URI = "KEY_IMAGE_URI"
    const val TAG_OUTPUT = "OUTPUT"

    const val DELAY_TIME_MILLIS: Long = 3000
    const val REQUEST_CODE_IMAGE = 0x01

}