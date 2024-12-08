package com.example.locker.models

data class HistoryResponse(
    val data: List<HistoryItem>
) {
    data class HistoryItem(
        val fullName: String,
        val idCard: String,
        val openAt: String
    )
}
