package com.alucardulad.tarotcards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// 每日塔罗牌页面
@Composable
fun DailyTarotScreen(viewModel: DailyTarotViewModel = viewModel()) {
    var question by remember { mutableStateOf("") }
    var isDrawing by remember { mutableStateOf(false) }
    var drawnCards by remember { mutableStateOf<List<CardModel>>(emptyList()) }
    var showDetail by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题区域
        Text(
            "每日塔罗牌",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            "向塔罗牌寻求指引，探索内心的答案",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )

        // 问题输入卡片
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.1f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    "你的问题",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    value = question,
                    onValueChange = { question = it },
                    placeholder = { Text("今天想了解什么？", color = Color.White.copy(alpha = 0.7f)) },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color(0xFF7D3FE1),
                        unfocusedIndicatorColor = Color(0xFF7D3FE1).copy(alpha = 0.5f),
                        cursorColor = Color(0xFF7D3FE1)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        isDrawing = true
                        // 模拟抽牌
                        GlobalScope.launch {
                            delay(2000) // 2秒延迟
                            drawnCards = viewModel.generateRandomCards()
                            isDrawing = false
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = !isDrawing && question.isNotEmpty(),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7D3FE1),
                        contentColor = Color.White
                    )
                ) {
                    if (isDrawing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("开始占卜", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }

        // 抽到的卡牌
        if (drawnCards.isNotEmpty()) {
            Text(
                "抽到的塔罗牌",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(drawnCards) { card ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDetail = true },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.1f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CardIcon(icon = card.icon, modifier = Modifier.size(48.dp))
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    card.name,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "正位：${card.meaningUpright}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                )
                                Text(
                                    "逆位：${card.meaningReversed}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                tint = Color(0xFF7D3FE1)
                            )
                        }
                    }
                }
            }
        }

        // 占卜师提示
        val currentReaderId = viewModel.getCurrentReaderId()
        val currentReader = readers.find { it.id == currentReaderId }
        if (currentReader != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "占卜师：",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.7f)
                    )
                )
                Text(
                    currentReader.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFA5F2FF)
                    )
                )
            }
        }
    }

    // 卡牌详情对话框
    if (showDetail && drawnCards.isNotEmpty()) {
        CardDetailDialog(
            cards = drawnCards,
            onDismiss = { showDetail = false }
        )
    }
}

// 卡牌图标
@Composable
fun CardIcon(icon: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(48.dp)
            .background(
                color = Color(0xFF7D3FE1),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            icon,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}

// 卡牌详情对话框
@Composable
fun CardDetailDialog(cards: List<CardModel>, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                "塔罗牌详情",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cards) { card ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.1f)
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CardIcon(icon = card.icon, modifier = Modifier.size(40.dp))
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        card.name,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        "正位：${card.meaningUpright}",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.White.copy(alpha = 0.7f)
                                        )
                                    )
                                    Text(
                                        "逆位：${card.meaningReversed}",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.White.copy(alpha = 0.7f)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("确定", color = Color.White)
            }
        },
        containerColor = Color(0xFF2D1344),
        titleContentColor = Color.White,
        textContentColor = Color.White.copy(alpha = 0.8f)
    )
}

// 每日塔罗 ViewModel
class DailyTarotViewModel : ViewModel() {
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
        )
    )

    // 获取当前占卜师 ID
    fun getCurrentReaderId(): String {
        return sharedPreferences.getString("current_reader_id", "reader_1") ?: "reader_1"
    }

    // 生成随机 3 张牌
    fun generateRandomCards(): List<CardModel> {
        val shuffled = tarotCards.shuffled().take(3)
        return shuffled
    }

    companion object {
        private lateinit var context: android.content.Context

        fun init(context: android.content.Context) {
            this.context = context.applicationContext
        }
    }
}
