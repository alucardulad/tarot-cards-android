package com.alucardulad.tarotcards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

// 每日一签页面
@Composable
fun DailyDrawScreen(viewModel: DailyDrawViewModel = viewModel()) {
    var dailyCard by remember { mutableStateOf<CardModel?>(null) }
    var isSignedIn by remember { mutableStateOf(viewModel.checkTodaySignedIn()) }
    var showSignInDialog by remember { mutableStateOf(false) }
    var showSignOutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题区域
        Text(
            "每日一签",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            "每天一张牌，一份指引",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )

        // 签到按钮
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isSignedIn)
                    Color(0xFFA5F2FF) else Color(0xFF7D3FE1)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    if (isSignedIn) Icons.Default.CheckCircle else Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = if (isSignedIn) Color(0xFF2D1344) else Color.White,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        if (isSignedIn) "今日已签到" else "签到今日运势",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isSignedIn) Color(0xFF2D1344) else Color.White
                        )
                    )
                    Text(
                        if (isSignedIn) {
                            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                            "今天：$today"
                        } else {
                            "点击按钮签到今日塔罗牌"
                        },
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (isSignedIn) Color(0xFF2D1344).copy(alpha = 0.7f) else Color.White.copy(alpha = 0.7f)
                        )
                    )
                }

                TextButton(
                    onClick = {
                        if (isSignedIn) {
                            showSignOutDialog = true
                        } else {
                            showSignInDialog = true
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (isSignedIn) Color(0xFF2D1344) else Color.White
                    )
                ) {
                    Text(
                        if (isSignedIn) "签到" else "签到",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // 今日卡牌
        if (dailyCard != null) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "今日运势",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.1f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 卡牌图标
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                color = Color(0xFF7D3FE1),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            dailyCard?.icon ?: "",
                            fontSize = 48.sp,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 卡牌名
                    Text(
                        dailyCard?.name ?: "",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 卡牌含义
                    Text(
                        "正位：${dailyCard?.meaningUpright}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFFA5F2FF)
                        )
                    )
                }
            }
        }

        // 签到历史
        Text(
            "签到历史",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(viewModel.loadHistory()) { entry ->
                val formatter = SimpleDateFormat("MM-dd", Locale.getDefault())
                val dateString = formatter.format(Date(entry.timestamp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        dateString,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    )
                }
            }
        }
    }

    // 签到对话框
    if (showSignInDialog) {
        DailyDrawDialog(
            onDismiss = { showSignInDialog = false },
            onSignIn = {
                dailyCard = viewModel.generateDailyCard()
                isSignedIn = true
                viewModel.saveSignIn(dailyCard!!, Date())
                showSignInDialog = false
            }
        )
    }

    // 签出对话框
    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            title = {
                Text(
                    "确认签到",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            },
            text = {
                Text(
                    "确定要签到今日运势吗？",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White.copy(alpha = 0.8f)
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.removeSignIn(Date())
                        isSignedIn = false
                        dailyCard = null
                        showSignOutDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFFA5F2FF)
                    )
                ) {
                    Text("签到", fontWeight = FontWeight.Bold)
                }
            },
            containerColor = Color(0xFF2D1344),
            titleContentColor = Color.White,
            textContentColor = Color.White.copy(alpha = 0.8f)
        )
    }
}

// 每日一签对话框
@Composable
fun DailyDrawDialog(onDismiss: () -> Unit, onSignIn: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "签到今日运势",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        },
        text = {
            Text(
                "点击签到，抽取今日运势牌",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White.copy(alpha = 0.8f)
                )
            )
        },
        confirmButton = {
            TextButton(
                onClick = onSignIn,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFFA5F2FF)
                )
            ) {
                Text("确定签到", fontWeight = FontWeight.Bold)
            }
        },
        containerColor = Color(0xFF2D1344),
        titleContentColor = Color.White,
        textContentColor = Color.White.copy(alpha = 0.8f)
    )
}

// 每日一签 ViewModel
class DailyDrawViewModel : ViewModel() {
    private val sharedPreferences by lazy {
        context.getSharedPreferences("tarot_cards_pref", android.content.Context.MODE_PRIVATE)
    }

    // 模拟的塔罗牌数据
    private val tarotCards = listOf(
        CardModel(
            id = "1",
            name = "愚人",
            meaningUpright = "新的开始、冒险、纯真",
            meaningReversed = "鲁莽、不成熟、冒险",
            icon = "🃏"
        ),
        CardModel(
            id = "2",
            name = "魔术师",
            meaningUpright = "创造力、意志力、能力",
            meaningReversed = "欺骗、滥用能力、失败",
            icon = "🪄"
        ),
        CardModel(
            id = "3",
            name = "女祭司",
            meaningUpright = "直觉、神秘、潜意识",
            meaningReversed = "被压抑的直觉、缺乏洞察力",
            icon = "🌙"
        ),
        CardModel(
            id = "4",
            name = "皇后",
            meaningUpright = "丰饶、母性、创造力",
            meaningReversed = "依赖、过度保护、不安全感",
            icon = "👑"
        ),
        CardModel(
            id = "5",
            name = "皇帝",
            meaningUpright = "权威、结构、控制",
            meaningReversed = "专横、僵化、缺乏同情心",
            icon = "🦁"
        ),
        CardModel(
            id = "6",
            name = "教皇",
            meaningUpright = "传统、信仰、教导",
            meaningReversed = "叛逆、怀疑、传统",
            icon = "📜"
        ),
        CardModel(
            id = "7",
            name = "恋人",
            meaningUpright = "爱、和谐、选择",
            meaningReversed = "不和谐、错误选择、冲突",
            icon = "💕"
        )
    )

    // 生成今日运势牌（基于日期）
    fun generateDailyCard(): CardModel {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val dayOfYear = SimpleDateFormat("D", Locale.getDefault()).format(Date()).toInt()
        val index = dayOfYear % tarotCards.size
        return tarotCards[index]
    }

    // 检查今日是否已签到
    fun checkTodaySignedIn(): Boolean {
        val lastSignIn = sharedPreferences.getLong("last_sign_in", 0)
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val lastSignInDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(lastSignIn))

        return today == lastSignInDate
    }

    // 保存签到
    fun saveSignIn(card: CardModel, timestamp: Long) {
        sharedPreferences.edit()
            .putLong("last_sign_in", timestamp)
            .putString("daily_card_json", exportCard(card))
            .apply()
    }

    // 移除签到
    fun removeSignIn(timestamp: Long) {
        sharedPreferences.edit()
            .remove("last_sign_in")
            .remove("daily_card_json")
            .apply()
    }

    // 加载签到历史
    fun loadHistory(): List<Long> {
        val historyJson = sharedPreferences.getString("daily_sign_in_history", "[]") ?: "[]"
        return try {
            // 简化版，返回最近的 30 天
            List(30) { System.currentTimeMillis() - (it * 24 * 60 * 60 * 1000) }.reversed()
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 导出 JSON
    private fun exportCard(card: CardModel): String {
        return "{ \"id\": \"${card.id}\", \"name\": \"${card.name}\", " +
                "\"meaningUpright\": \"${card.meaningUpright}\", " +
                "\"meaningReversed\": \"${card.meaningReversed}\", " +
                "\"icon\": \"${card.icon}\" }"
    }

    companion object {
        private lateinit var context: android.content.Context

        fun init(context: android.content.Context) {
            this.context = context.applicationContext
        }
    }
}
