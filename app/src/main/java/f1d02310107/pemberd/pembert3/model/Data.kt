package f1d02310107.pemberd.pembert3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val fullName: String,
    val nim: String,
    val studyProgram: String,
    val gender: String,
    val hobbies: List<String>
) : Parcelable