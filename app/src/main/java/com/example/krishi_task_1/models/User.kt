package com.example.krishi_task_1.models

import android.graphics.Bitmap
import android.os.Parcelable
import java.io.Serializable

data class User(
    var doesExist: Boolean,
    var name: String,
    var email: String,
    var bp: String
): Serializable
