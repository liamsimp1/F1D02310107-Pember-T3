package f1d02310107.pemberd.pembert3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import f1d02310107.pemberd.pembert3.model.Data

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvFullName: TextView
    private lateinit var tvNim: TextView
    private lateinit var tvStudyProgram: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvHobbies: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        displayData()
        setupClickListener()
    }

    private fun initViews() {
        tvFullName = findViewById(R.id.tvFullName)
        tvNim = findViewById(R.id.tvNim)
        tvStudyProgram = findViewById(R.id.tvStudyProgram)
        tvGender = findViewById(R.id.tvGender)
        tvHobbies = findViewById(R.id.tvHobbies)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun displayData() {
        val Data = intent.getParcelableExtra<Data>("DATA")

        if (Data != null) {
            tvFullName.text = Data.fullName
            tvNim.text = Data.nim
            tvStudyProgram.text = Data.studyProgram
            tvGender.text = Data.gender
            tvHobbies.text = Data.hobbies.joinToString(", ")
        } else {
            tvFullName.text = "Data tidak tersedia"
            tvNim.text = "-"
            tvStudyProgram.text = "-"
            tvGender.text = "-"
            tvHobbies.text = "-"
        }
    }

    private fun setupClickListener() {
        btnBack.setOnClickListener {
            finish()
        }
    }
}