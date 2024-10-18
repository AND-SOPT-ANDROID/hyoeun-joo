package org.sopt.and.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import org.sopt.and.feature.model.UserInfo

inline fun <reified T : Parcelable> Intent.getSafeParcelable(name: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(name, T::class.java)
    } else {
        getParcelableExtra(name) as? T
    }
}

inline fun <reified T : Activity> navigateWithUserInfo(
    context: Context,
    userInfo: UserInfo? = null,
) {
    Intent(context, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        userInfo?.let {
            putExtra("userInfo", it)
        }
        context.startActivity(this)
    }
}
