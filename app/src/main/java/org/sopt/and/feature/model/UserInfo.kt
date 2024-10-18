package org.sopt.and.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserInfo(
    val id: String,
    val password: String,
): Parcelable
