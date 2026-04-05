package f1d02310107.pemberd.pembert3

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import f1d02310107.pemberd.pembert3.model.Data

class MainActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etNim: EditText
    private lateinit var spinnerProdi: Spinner
    private lateinit var rgGender: RadioGroup
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var cbMusik: CheckBox
    private lateinit var cbGame: CheckBox
    private lateinit var cbTravel: CheckBox
    private lateinit var cbFotografi: CheckBox
    private lateinit var btnShowProfile: Button
    private lateinit var tvErrorName: TextView
    private lateinit var tvErrorNim: TextView
    private lateinit var tvErrorProdi: TextView
    private lateinit var tvErrorGender: TextView
    private lateinit var tvErrorHobby: TextView

    private lateinit var prodiList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSpinner()
        setupClickListener()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etNim = findViewById(R.id.etNim)
        spinnerProdi = findViewById(R.id.spinnerProdi)
        rgGender = findViewById(R.id.rgGender)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        cbMusik = findViewById(R.id.cbMusik)
        cbGame = findViewById(R.id.cbGame)
        cbTravel = findViewById(R.id.cbTravel)
        cbFotografi = findViewById(R.id.cbFotografi)
        btnShowProfile = findViewById(R.id.btnShowProfile)
        tvErrorName = findViewById(R.id.tvErrorName)
        tvErrorNim = findViewById(R.id.tvErrorNim)
        tvErrorProdi = findViewById(R.id.tvErrorProdi)
        tvErrorGender = findViewById(R.id.tvErrorGender)
        tvErrorHobby = findViewById(R.id.tvErrorHobby)
    }

    private fun setupSpinner() {
        prodiList = listOf(
            "Pilih Program Studi",
            "Teknik Informatika",
            "Teknik Sipil",
            "Teknik Elektro",
            "Teknik Mesin",
            "Arsitektur",
            "Teknik Industri",
            "Lainnya"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, prodiList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProdi.adapter = adapter
    }

    private fun setupClickListener() {
        btnShowProfile.setOnClickListener {
            if (validateInputs()) {
                navigateToProfile()
            }
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        val fullName = etFullName.text.toString().trim()
        val nim = etNim.text.toString().trim()
        val selectedProdi = spinnerProdi.selectedItemPosition
        val genderId = rgGender.checkedRadioButtonId
        val hobbies = getSelectedHobbies()

        tvErrorName.text = ""
        tvErrorNim.text = ""
        tvErrorProdi.text = ""
        tvErrorGender.text = ""
        tvErrorHobby.text = ""

        if (fullName.isEmpty()) {
            tvErrorName.text = "Nama lengkap tidak boleh kosong"
            isValid = false
        }

        if (nim.isEmpty()) {
            tvErrorNim.text = "NIM tidak boleh kosong"
            isValid = false
        } else if (!nim.matches(Regex("^[A-Za-z0-9]{9,11}\$"))) {
            tvErrorNim.text = "NIM harus terdiri dari 9-11 karakter (huruf/angka/campuran)"
            isValid = false
        }

        if (selectedProdi == 0) {
            tvErrorProdi.text = "Pilih program studi"
            isValid = false
        }

        if (genderId == -1) {
            tvErrorGender.text = "Pilih jenis kelamin"
            isValid = false
        }

        if (hobbies.size < 3) {
            tvErrorHobby.text = "Pilih minimal 3 hobi"
            isValid = false
        }

        return isValid
    }

    private fun getSelectedHobbies(): List<String> {
        val hobbies = mutableListOf<String>()
        if (cbMembaca.isChecked) hobbies.add("Membaca")
        if (cbOlahraga.isChecked) hobbies.add("Olahraga")
        if (cbMusik.isChecked) hobbies.add("Musik")
        if (cbGame.isChecked) hobbies.add("Game")
        if (cbTravel.isChecked) hobbies.add("Traveling")
        if (cbFotografi.isChecked) hobbies.add("Fotografi")
        return hobbies
    }

    private fun navigateToProfile() {
        val fullName = etFullName.text.toString().trim()
        val nim = etNim.text.toString().trim()
        val selectedProdi = spinnerProdi.selectedItemPosition
        val studyProgram = prodiList[selectedProdi]
        val gender = if (rgGender.checkedRadioButtonId == R.id.rbLaki) "Laki-laki" else "Perempuan"
        val hobbies = getSelectedHobbies()

        val studentData = Data(
            fullName = fullName,
            nim = nim,
            studyProgram = studyProgram,
            gender = gender,
            hobbies = hobbies
        )

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("DATA", studentData)
        startActivity(intent)
    }
}