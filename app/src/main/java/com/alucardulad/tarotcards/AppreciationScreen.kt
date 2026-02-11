package com.alucardulad.tarotcards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

// 星空粒子效果
data class StarField(
    val x: Float,
    val y: Float,
    val size: Float,
    val alpha: Float,
    val twinkleSpeed: Float
)

// 光球粒子
data class OrbParticle(
    val x: Float,
    val y: Float,
    val size: Float,
    val speedX: Float,
    val speedY: Float,
    val color: Color
)

// 流星
data class ShootingStar(
    val x: Float,
    val y: Float,
    val tailLength: Float,
    val speed: Float,
    val alpha: Float,
    val active: Boolean = true
)

// 尘埃粒子
data class DustParticle(
    val x: Float,
    val y: Float,
    val size: Float,
    val alpha: Float
)

// 粒子系统管理器
object ParticleSystem {
    // 星星
    private val stars = remember { mutableStateListOf<StarField>() }
    @Composable
    fun createStarField(count: Int = 50): List<StarField> {
        stars.clear()
        for (i in 0 until count) {
            stars.add(
                StarField(
                    x = Random.nextFloat() * 1000f,
                    y = Random.nextFloat() * 1000f,
                    size = Random.nextFloat() * 2f + 0.5f,
                    alpha = Random.nextFloat() * 0.5f + 0.5f,
                    twinkleSpeed = Random.nextFloat() * 0.02f + 0.01f
                )
            )
        }
        return stars
    }

    // 光球
    private val orbs = remember { mutableStateListOf<OrbParticle>() }
    @Composable
    fun createOrbs(count: Int = 5): List<OrbParticle> {
        orbs.clear()
        val colors = listOf(
            Color(0xFF7D3FE1),
            Color(0xFFA5F2FF),
            Color(0xFFFFFFFF)
        )
        for (i in 0 until count) {
            orbs.add(
                OrbParticle(
                    x = Random.nextFloat() * 1000f,
                    y = Random.nextFloat() * 1000f,
                    size = Random.nextFloat() * 20f + 10f,
                    speedX = Random.nextFloat() * 0.5f - 0.25f,
                    speedY = Random.nextFloat() * 0.3f - 0.15f,
                    color = colors.random()
                )
            )
        }
        return orbs
    }

    // 流星
    private val shootingStars = remember { mutableStateListOf<ShootingStar>() }
    @Composable
    fun createShootingStar(): ShootingStar {
        return ShootingStar(
            x = Random.nextFloat() * 800f,
            y = -50f,
            tailLength = Random.nextFloat() * 100f + 50f,
            speed = Random.nextFloat() * 10f + 5f,
            alpha = Random.nextFloat() * 0.8f + 0.2f
        )
    }

    // 尘埃
    private val dusts = remember { mutableStateListOf<DustParticle>() }
    @Composable
    fun createDust(count: Int = 20): List<DustParticle> {
        dusts.clear()
        for (i in 0 until count) {
            dusts.add(
                DustParticle(
                    x = Random.nextFloat() * 1000f,
                    y = Random.nextFloat() * 1000f,
                    size = Random.nextFloat() * 1f + 0.5f,
                    alpha = Random.nextFloat() * 0.5f + 0.2f
                )
            )
        }
        return dusts
    }
}

// 卡片浮动动画
@Composable
fun AnimatedCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val animation by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
        label = "float"
    )

    Box(
        modifier = modifier
            .scale(animation)
    ) {
        content()
    }
}

// 星空鉴赏页面
@Composable
fun AppreciationScreen(
    viewModel: AppreciationViewModel = viewModel()
) {
    var selectedCard by remember { mutableStateOf<CardModel?>(null) }

    // 粒子效果
    val stars = remember { ParticleSystem.createStarField(50) }
    val orbs = remember { ParticleSystem.createOrbs(5) }
    val dusts = remember { ParticleSystem.createDust(20) }

    // 生成流星
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(Random.nextLong(5000, 10000))
            viewModel.addShootingStar()
            kotlinx.coroutines.delay(3000) // 流星持续时间
        }
    }

    // 移除消失的流星
    val shootingStars = viewModel.shootingStars.collectAsState().value
    LaunchedEffect(shootingStars) {
        viewModel.removeInactiveShootingStars()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 背景（星空）
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2D1344),
                            Color(0xFF1E1233),
                            Color(0xFF120632)
                        )
                    )
                )
        )

        // 星星
        stars.forEach { star ->
            Box(
                modifier = Modifier
                    .offset(star.x.dp, star.y.dp)
                    .size(star.size.dp)
                    .background(Color.White, RoundedCornerShape(50))
                    .alpha(star.alpha)
            )
        }

        // 光球
        orbs.forEach { orb ->
            Box(
                modifier = Modifier
                    .offset(orb.x.dp, orb.y.dp)
                    .size(orb.size.dp)
                    .background(
                        orb.color.copy(alpha = 0.3f),
                        RoundedCornerShape(50)
                    )
                    .shadow(8.dp, RoundedCornerShape(50))
            )
        }

        // 尘埃
        dusts.forEach { dust ->
            Box(
                modifier = Modifier
                    .offset(dust.x.dp, dust.y.dp)
                    .size(dust.size.dp)
                    .background(Color(0xFF7D3FE1).copy(alpha = dust.alpha), RoundedCornerShape(50))
            )
        }

        // 流星
        shootingStars.forEach { shootingStar ->
            Box(
                modifier = Modifier
                    .offset(shootingStar.x.dp, shootingStar.y.dp)
            ) {
                androidx.compose.ui.graphics.drawscope.drawIntoCanvas {
                    val paint = androidx.compose.ui.graphics.Paint()
                    paint.color = Color.White.copy(alpha = shootingStar.alpha)
                    paint.style = androidx.compose.ui.draw.StrokeCap.Round
                    paint.strokeWidth = 2f
                    it.drawLine(
                        start = androidx.compose.ui.geometry.Offset(
                            shootingStar.x,
                            shootingStar.y
                        ),
                        end = androidx.compose.ui.geometry.Offset(
                            shootingStar.x - shootingStar.tailLength,
                            shootingStar.y + shootingStar.tailLength * 0.5f
                        ),
                        color = Color.White.copy(alpha = shootingStar.alpha),
                        strokeWidth = 2f
                    )
                }
            }
        }

        // 卡片列表
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.cards) { card ->
                AnimatedCard {
                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable { selectedCard = card },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.1f)
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    androidx.compose.ui.graphics.Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF7D3FE1),
                                            Color(0xFFA5F2FF)
                                        )
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            CardImage(cardId = card.id)
                        }
                    }
                }
            }
        }
    }

    // 卡牌详情
    selectedCard?.let { card ->
        AlertDialog(
            onDismissRequest = { selectedCard = null },
            title = {
                Text(
                    card.name,
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
                        "正位：${card.meaningUpright}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                    Text(
                        "逆位：${card.meaningReversed}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { selectedCard = null },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color(0xFFA5F2FF)
                    )
                ) {
                    Text("确定", fontWeight = FontWeight.Bold)
                }
            },
            containerColor = Color(0xFF2D1344),
            titleContentColor = Color.White,
            textContentColor = Color.White.copy(alpha = 0.8f)
        )
    }
}

// 卡牌图片组件
@Composable
fun CardImage(cardId: String) {
    try {
        // 尝试从资源加载图片
        val imageRes = when (cardId) {
            "1" -> R.drawable.card_0
            "2" -> R.drawable.card_1
            "3" -> R.drawable.card_2
            "4" -> R.drawable.card_3
            "5" -> R.drawable.card_4
            "6" -> R.drawable.card_5
            "7" -> R.drawable.card_6
            "8" -> R.drawable.card_7
            "9" -> R.drawable.card_8
            "10" -> R.drawable.card_9
            "11" -> R.drawable.card_10
            "12" -> R.drawable.card_11
            "13" -> R.drawable.card_12
            "14" -> R.drawable.card_13
            "15" -> R.drawable.card_14
            "16" -> R.drawable.card_15
            "17" -> R.drawable.card_16
            "18" -> R.drawable.card_17
            "19" -> R.drawable.card_18
            "20" -> R.drawable.card_19
            "21" -> R.drawable.card_20
            "22" -> R.drawable.card_21
            else -> R.drawable.card_0 // 默认第一张
        }
        Image(
            painter = painterResource(imageRes),
            contentDescription = "塔罗牌 ${cardId}",
            modifier = Modifier.fillMaxSize()
        )
    } catch (e: Exception) {
        // 加载失败，显示默认图标
        Text(
            "🃏",
            fontSize = 48.sp,
            color = Color.White
        )
    }
}

// 星空鉴赏 ViewModel
class AppreciationViewModel : ViewModel() {
    val cards by mutableStateOf<List<CardModel>>(
        listOf(
            CardModel("1", "愚人", "新的开始", "鲁莽", "🃏"),
            CardModel("2", "魔术师", "创造力", "欺骗", "🪄"),
            CardModel("3", "女祭司", "直觉", "缺乏洞察力", "🌙"),
            CardModel("4", "皇后", "丰饶", "依赖", "👑"),
            CardModel("5", "皇帝", "权威", "专横", "🦁"),
            CardModel("6", "教皇", "传统", "叛逆", "📜"),
            CardModel("7", "恋人", "爱", "不和谐", "💕"),
            CardModel("8", "战车", "前进", "失控", "🚗"),
            CardModel("9", "力量", "勇气", "恐惧", "🦁"),
            CardModel("10", "隐士", "内省", "孤独", "🕯️"),
            CardModel("11", "命运之轮", "改变", "抗拒改变", "⚙️"),
            CardModel("12", "倒吊人", "牺牲", "被动", "🤸"),
            CardModel("13", "死神", "结束", "抗拒改变", "💀"),
            CardModel("14", "节制", "平衡", "过度", "⚗️"),
            CardModel("15", "恶魔", "束缚", "解脱", "😈"),
            CardModel("16", "高塔", "改变", "抗拒改变", "🗼"),
            CardModel("17", "星星", "希望", "失望", "⭐"),
            CardModel("18", "月亮", "幻觉", "清醒", "🌙"),
            CardModel("19", "太阳", "快乐", "悲伤", "☀️"),
            CardModel("20", "审判", "觉醒", "抗拒", "🎺"),
            CardModel("21", "世界", "完成", "不完整", "🌍")
        )
    )

    val shootingStars = mutableStateListOf<ShootingStar>()

    fun addShootingStar() {
        shootingStars.add(ParticleSystem.createShootingStar())
    }

    fun removeInactiveShootingStars() {
        shootingStars.removeAll { !it.active }
    }
}
