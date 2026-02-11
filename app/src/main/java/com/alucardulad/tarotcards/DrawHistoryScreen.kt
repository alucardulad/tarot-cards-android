package com.alucardulad.tarotcards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

// 占卜记录页面
@Composable
fun DrawHistoryScreen(viewModel: DrawHistoryViewModel = viewModel()) {
    var historyList by remember { mutableStateOf(viewModel.loadHistory()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题区域
        Text(
            "占卜记录",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            "回顾我们一起走过的路",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )

        // 历史记录列表
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(historyList) { entry ->
                HistoryCard(
                    entry = entry,
                    onClick = {
                        // 点击查看详情
                    }
                )
            }
        }

        // 空状态提示
        if (historyList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "暂无占卜记录",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White.copy(alpha = 0.5f)
                        )
                    )
                    Text(
                        "开始你的第一次占卜吧~",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.3f)
                        )
                    )
                }
            }
        }
    }
}

// 历史记录卡片
@Composable
fun HistoryCard(entry: HistoryEntry, onClick: () -> Unit) {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val timeString = formatter.format(Date(entry.timestamp))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 时间图标
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color(0xFF7D3FE1),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.History,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 信息
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    timeString,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))

                // 问题
                Text(
                    entry.question,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.8f),
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))

                // 牌数
                Text(
                    "抽到 ${entry.cards.size} 张牌",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFFA5F2FF)
                    )
                )
            }

            // 查看按钮
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.3f),
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

// 历史记录 ViewModel
class DrawHistoryViewModel : ViewModel() {
    private val sharedPreferences by lazy {
        context.getSharedPreferences("tarot_cards_pref", android.content.Context.MODE_PRIVATE)
    }

    // 加载历史记录
    fun loadHistory(): List<HistoryEntry> {
        val historyJson = sharedPreferences.getString("divination_history", "[]") ?: "[]"
        return try {
            importJson(historyJson)
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 保存历史记录
    fun saveHistory(entry: HistoryEntry) {
        val currentHistory = loadHistory()
        val updatedHistory = listOf(entry) + currentHistory.take(49) // 最多保存50条
        val historyJson = exportJson(updatedHistory)

        sharedPreferences.edit()
            .putString("divination_history", historyJson)
            .apply()
    }

    // 清空历史记录
    fun clearHistory() {
        sharedPreferences.edit()
            .remove("divination_history")
            .apply()
    }

    // 导出 JSON
    private fun exportJson(history: List<HistoryEntry>): String {
        return history.joinToString(",") { entry ->
            "{ \"id\": \"${entry.id}\", \"question\": \"${entry.question}\", " +
                    "\"readerId\": \"${entry.readerId}\", \"timestamp\": ${entry.timestamp} }"
        }
    }

    // 导入 JSON
    private fun importJson(json: String): List<HistoryEntry> {
        if (json.isEmpty()) return emptyList()

        val items = json.split(",")
        return items.mapNotNull { item ->
            try {
                // 解析 JSON 字符串（简化版）
                val id = extractValue(item, "id")
                val question = extractValue(item, "question")
                val readerId = extractValue(item, "readerId")
                val timestampStr = extractValue(item, "timestamp")

                if (id != null && question != null && readerId != null && timestampStr != null) {
                    val timestamp = timestampStr.toLongOrNull() ?: 0
                    val cards = emptyList<CardModel>()
                    HistoryEntry(id, question, cards, readerId, timestamp)
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    // 提取 JSON 值
    private fun extractValue(json: String, key: String): String? {
        val start = json.indexOf("\"$key\"") + key.length + 3
        val end = json.indexOf("\"", start)
        return if (start > key.length + 2 && end > start) {
            json.substring(start, end)
        } else {
            null
        }
    }

    companion object {
        private lateinit var context: android.content.Context

        fun init(context: android.content.Context) {
            this.context = context.applicationContext
        }
    }
}
