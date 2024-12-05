package org.sopt.and.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserInfo(
    val userName: String,
    val password: String,
    val hobby: String
) : Parcelable
