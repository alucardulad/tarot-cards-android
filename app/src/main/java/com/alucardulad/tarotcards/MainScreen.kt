package com.alucardulad.tarotcards

import androidx.compose.animation.core.tween
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// MainScreen - 主页面（包含底部导航栏，动态主题）
@Composable
fun TarotCardsApp() {
    val navController = rememberNavController()
    val colors = remember { ThemeManager.getCurrentTheme() }

    val darkColorScheme = darkColorScheme(
        primary = colors.primary,
        secondary = colors.secondary,
        background = colors.background1,
        surface = colors.background2
    )

    MaterialTheme(colorScheme = darkColorScheme) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colors.background1,
                            colors.background2,
                            colors.background3
                        )
                    )
                )
        ) {
            NavigationContent(navController, colors)
        }
    }
}

@Composable
fun NavigationContent(navController: NavController, colors: ThemeColors) {
    Scaffold(
        bottomBar = {
            BottomNavigation(
                containerColor = colors.background1,
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "每日塔罗") },
                    label = { Text("每日塔罗") },
                    selected = true,
                    onClick = {
                        navController.navigate("daily_tarot") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.CalendarToday, contentDescription = "每日一签") },
                    label = { Text("每日一签") },
                    selected = false,
                    onClick = {
                        navController.navigate("daily_draw")
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.History, contentDescription = "占卜记录") },
                    label = { Text("占卜记录") },
                    selected = false,
                    onClick = {
                        navController.navigate("draw_history")
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Sparkles, contentDescription = "星空鉴赏") },
                    label = { Text("星空鉴赏") },
                    selected = false,
                    onClick = {
                        navController.navigate("appreciation")
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Heart, contentDescription = "我的收藏") },
                    label = { Text("我的收藏") },
                    selected = false,
                    onClick = {
                        navController.navigate("favorites")
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person3, contentDescription = "占卜师") },
                    label = { Text("占卜师") },
                    selected = false,
                    onClick = {
                        navController.navigate("reader_select")
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "daily_tarot",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("daily_tarot") {
                DailyTarotScreen()
            }
            composable("daily_draw") {
                DailyDrawScreen()
            }
            composable("draw_history") {
                DrawHistoryScreen()
            }
            composable("appreciation") {
                AppreciationScreen()
            }
            composable("favorites") {
                FavoritesScreen()
            }
            composable("reader_select") {
                ReaderSelectScreen()
            }
        }
    }
}

// 页面框架占位符
@Composable
fun DailyTarotScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "每日塔罗牌",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "每日塔罗牌功能开发中...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}

@Composable
fun DrawHistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "占卜记录",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "占卜记录功能开发中...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}

@Composable
fun AppreciationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "星空鉴赏",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "星空鉴赏功能开发中...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}

@Composable
fun FavoritesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "我的收藏",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "我的收藏功能开发中...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}

@Composable
fun ReaderSelectScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "选择你的占卜师",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            "占卜师选择功能开发中...",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White.copy(alpha = 0.7f)
            )
        )
    }
}
