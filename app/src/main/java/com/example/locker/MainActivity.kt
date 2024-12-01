package com.example.locker
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnUnlock = findViewById<Button>(R.id.btn_unlock)
        val btnViewHistory = findViewById<Button>(R.id.btn_view_history)
        val btnManageUsers = findViewById<Button>(R.id.btn_manage_users)

        btnUnlock.setOnClickListener {
            startActivity(Intent(this, UnlockActivity::class.java))
        }

        btnViewHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        btnManageUsers.setOnClickListener {
            startActivity(Intent(this, ManageUsersActivity::class.java))
        }
    }
}
