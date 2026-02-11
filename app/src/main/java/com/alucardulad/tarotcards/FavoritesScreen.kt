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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 我的收藏页面
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = viewModel()) {
    var favoriteReaders by remember { mutableStateOf(viewModel.loadFavorites()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 标题区域
        Text(
            "我的收藏",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Text(
            "收藏了你喜欢的占卜师",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )

        // 收藏列表
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favoriteReaders) { reader ->
                FavoriteReaderCard(
                    reader = reader,
                    onRemove = {
                        viewModel.removeFavorite(reader.id)
                        favoriteReaders = favoriteReaders.filter { it.id != reader.id }
                    }
                )
            }
        }

        // 空状态提示
        if (favoriteReaders.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.HeartBroken,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.3f),
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "暂无收藏",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White.copy(alpha = 0.5f)
                        )
                    )
                    Text(
                        "去占卜师页面收藏你喜欢的吧~",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White.copy(alpha = 0.3f)
                        )
                    )
                }
            }
        }
    }
}

// 收藏的占卜师卡片
@Composable
fun FavoriteReaderCard(
    reader: ReaderModel,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
            // 头像
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        color = Color(0xFFA5F2FF),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    reader.avatar,
                    fontSize = 30.sp,
                    color = Color(0xFF7D3FE1)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 信息
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    reader.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
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
            }

            // 移除按钮
            IconButton(onClick = { onRemove() }) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "移除",
                    tint = Color.White.copy(alpha = 0.5f)
                )
            }
        }
    }
}

// 收藏功能 ViewModel
class FavoritesViewModel : ViewModel() {
    private val sharedPreferences by lazy {
        context.getSharedPreferences("tarot_cards_pref", android.content.Context.MODE_PRIVATE)
    }

    // 加载收藏的占卜师
    fun loadFavorites(): List<ReaderModel> {
        val favoriteIds = sharedPreferences.getStringSet("favorite_readers", emptySet()) ?: emptySet()
        return readers.filter { favoriteIds.contains(it.id) }
    }

    // 移除收藏
    fun removeFavorite(readerId: String) {
        val favoriteIds = sharedPreferences.getStringSet("favorite_readers", emptySet())?.toMutableSet() ?: mutableSetOf()

        favoriteIds.remove(readerId)

        sharedPreferences.edit()
            .putStringSet("favorite_readers", favoriteIds)
            .apply()
    }

    // 检查是否收藏
    fun isFavorite(readerId: String): Boolean {
        val favoriteIds = sharedPreferences.getStringSet("favorite_readers", emptySet()) ?: emptySet()
        return favoriteIds.contains(readerId)
    }

    companion object {
        private lateinit var context: android.content.Context

        fun init(context: android.content.Context) {
            this.context = context.applicationContext
        }
    }
}
