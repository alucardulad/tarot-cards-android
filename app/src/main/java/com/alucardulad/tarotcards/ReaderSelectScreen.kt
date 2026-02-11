package com.alucardulad.tarotcards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// 占卜师选择页面
@Composable
fun ReaderSelectScreen(viewModel: ReaderSelectViewModel = viewModel()) {
    var selectedReader by remember { mutableStateOf<ReaderModel?>(null) }
    var showDetail by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题区域
        Text(
            "选择你的占卜师",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            "每个占卜师都有独特的风格，找到最适合你的那个吧~",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFFA5F2FF)
            )
        )

        // 占卜师列表
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(viewModel.readers) { reader ->
                ReaderCard(
                    reader = reader,
                    isSelected = selectedReader?.id == reader.id,
                    onClick = {
                        selectedReader = reader
                        showDetail = true
                    },
                    onFavoriteToggle = {
                        viewModel.toggleFavorite(reader.id)
                    }
                )
            }
        }
    }

    // 占卜师详情对话框
    if (showDetail && selectedReader != null) {
        ReaderDetailDialog(
            reader = selectedReader!!,
            onDismiss = { showDetail = false }
        )
    }
}

// 占卜师卡片组件
@Composable
fun ReaderCard(
    reader: ReaderModel,
    isSelected: Boolean,
    onClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 头像
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = if (isSelected) Color(0xFF7D3FE1) else Color.White.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    reader.avatar,
                    fontSize = 30.sp,
                    color = if (isSelected) Color.White else Color(0xFF7D3FE1)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 信息
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        reader.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    if (isSelected) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "已选择",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFA5F2FF)
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                // 标签
                reader.tags.forEach { tag ->
                    Text(
                        tag,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    reader.bio,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.7f),
                        maxLines = 2,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                )
            }

            // 收藏按钮
            IconButton(onClick = { onFavoriteToggle() }) {
                Icon(
                    imageVector = if (reader.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "收藏",
                    tint = if (reader.isFavorite) Color(0xFFA5F2FF) else Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

// 占卜师详情对话框
@Composable
fun ReaderDetailDialog(
    reader: ReaderModel,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                reader.name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    reader.bio,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White.copy(alpha = 0.8f)
                    )
                )
                reader.tags.forEach { tag ->
                    Text(
                        tag,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFFA5F2FF)
                        )
                    )
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
