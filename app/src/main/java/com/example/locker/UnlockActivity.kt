package com.example.locker
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.locker.api.ApiClient
import com.example.locker.models.UnlockRequest
import com.example.locker.models.UnlockResponse

class UnlockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unlock)

        val btnSendUnlock = findViewById<Button>(R.id.btn_send_unlock)

        btnSendUnlock.setOnClickListener {
            val api = ApiClient.instance
            api.unlock(UnlockRequest(userId = "12345")).enqueue(object : Callback<UnlockResponse> {
                override fun onResponse(call: Call<UnlockResponse>, response: Response<UnlockResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@UnlockActivity, "Mở khóa thành công!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@UnlockActivity, "Thất bại!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UnlockResponse>, t: Throwable) {
                    Toast.makeText(this@UnlockActivity, "Lỗi kết nối!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
