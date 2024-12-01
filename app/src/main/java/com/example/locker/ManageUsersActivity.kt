package com.example.locker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.locker.api.ApiClient
import com.example.locker.models.User

class ManageUsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_users)

        val edtName = findViewById<EditText>(R.id.edt_name)
        val edtCardId = findViewById<EditText>(R.id.edt_card_id)
        val btnSave = findViewById<Button>(R.id.btn_save_user)

        btnSave.setOnClickListener {
            val user = User(name = edtName.text.toString(), cardId = edtCardId.text.toString())
            val api = ApiClient.instance
            api.updateUser(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ManageUsersActivity, "Lưu thành công!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(this@ManageUsersActivity, "Lỗi!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
