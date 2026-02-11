package com.alucardulad.tarotcards

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

// 主题颜色数据模型
data class ThemeColors(
    val primary: Color,
    val secondary: Color,
    val background1: Color,
    val background2: Color,
    val background3: Color
)

// 占卜师主题配置
data class ReaderTheme(
    val readerId: String,
    val name: String,
    val colors: ThemeColors,
    val description: String
)

// 主题管理器
object ThemeManager {
    private val _selectedReaderId = mutableStateOf("reader_1")
    val selectedReaderId: String
        get() = _selectedReaderId.value

    private var context: Context? = null

    // 初始化
    fun init(context: Context) {
        this.context = context
        loadSavedReaderId()
    }

    // 加载保存的占卜师 ID
    private fun loadSavedReaderId() {
        val sharedPrefs = context?.getSharedPreferences("tarot_cards_pref", Context.MODE_PRIVATE)
        _selectedReaderId.value = sharedPrefs?.getString("current_reader_id", "reader_1") ?: "reader_1"
    }

    // 保存占卜师 ID
    private fun saveReaderId(readerId: String) {
        val sharedPrefs = context?.getSharedPreferences("tarot_cards_pref", Context.MODE_PRIVATE)
        sharedPrefs?.edit()?.putString("current_reader_id", readerId)?.apply()
    }

    // 选择占卜师
    fun selectReader(readerId: String) {
        _selectedReaderId.value = readerId
        saveReaderId(readerId)
    }

    // 获取当前占卜师的主题
    @Composable
    fun getCurrentTheme(): ThemeColors {
        val readerTheme = getReaderTheme(_selectedReaderId.value)
        return readerTheme.colors
    }

    // 获取所有占卜师主题
    fun getAllThemes(): List<ReaderTheme> {
        return listOf(
            ReaderTheme("reader_1", "陈柔", ThemeColors(
                Color(0xFF7D3FE1),  // 紫色
                Color(0xFFA5F2FF),  // 青紫色
                Color(0xFF2D1344),  // 深紫
                Color(0xFF1E1233),  // 暗紫
                Color(0xFF120632)   // 紫黑
            ), "温柔导师型"),

            ReaderTheme("reader_2", "神秘大师", ThemeColors(
                Color(0xFF2D1344),  // 深紫
                Color(0xFF1E1233),  // 暗紫
                Color(0xFF000000),  // 黑色
                Color(0xFF1A1A1A),  // 深灰
                Color(0xFF0A0A0A)   // 极黑
            ), "神秘威严型"),

            ReaderTheme("reader_3", "星语", ThemeColors(
                Color(0xFFFF6B9D),  // 粉色
                Color(0xFFFFD700),  // 金色
                Color(0xFFFFE4E1),  // 淡粉
                Color(0xFFFFC0CB),  // 粉色
                Color(0xFFFF69B4)   // 亮粉
            ), "随性聊友型"),

            ReaderTheme("reader_4", "月影", ThemeColors(
                Color(0xFF4A00E0),  // 蓝紫
                Color(0xFF8E2DE2),  // 紫色
                Color(0xFF1A0B2E),  // 深紫
                Color(0xFF2E003E),  // 紫黑
                Color(0xFF0D001F)   // 极深
            ), "东方命理型"),

            ReaderTheme("reader_5", "命运女王", ThemeColors(
                Color(0xFF8B0000),  // 深红
                Color(0xFFD4AF37),  // 金色
                Color(0xFF4A0404),  // 深红
                Color(0xFF2D0000),  // 红黑
                Color(0xFF1A0000)   // 极红黑
            ), "权威预言型"),

            ReaderTheme("reader_6", "梦境旅行者", ThemeColors(
                Color(0xFF9370DB),  // 浅紫
                Color(0xFF191970),  // 深蓝
                Color(0xFF2E0854),  // 深紫
                Color(0xFF1A0B2E),  // 紫黑
                Color(0xFF0D001F)   // 极深
            ), "梦幻潜意识型"),

            ReaderTheme("reader_7", "自由灵魂", ThemeColors(
                Color(0xFF87CEEB),  // 天空蓝
                Color(0xFF90EE90),  // 绿色
                Color(0xFF87CEEB),  // 天空蓝
                Color(0xFF6BB3D9),  // 淡蓝
                Color(0xFF4682B4)   // 钢蓝
            ), "自由奔放型"),

            ReaderTheme("reader_8", "古籍守护者", ThemeColors(
                Color(0xFFF5F5DC),  // 米色
                Color(0xFF8B4513),  // 褐色
                Color(0xFFD2B48C),  // 棕色
                Color(0xFFA0522D),  // 赭色
                Color(0xFF8B4513)   // 褐色
            ), "古老传统型"),

            ReaderTheme("reader_9", "小恶魔", ThemeColors(
                Color(0xFF1A1A1A),  // 黑色
                Color(0xFFFF4500),  // 红橙色
                Color(0xFF000000),  // 纯黑
                Color(0xFF2D2D2D),  // 深灰
                Color(0xFF1A1A1A)   // 极黑
            ), "反叛颠覆型"),

            ReaderTheme("reader_10", "天使长者", ThemeColors(
                Color(0xFFFFFFFF),  // 白色
                Color(0xFFD4AF37),  // 金色
                Color(0xFFFFFAFA),  // 淡白
                Color(0xFFE8E8E8),  // 淡灰
                Color(0xFFF5F5F5)   // 极淡
            ), "光明神圣型"),

            ReaderTheme("reader_11", "时光旅人", ThemeColors(
                Color(0xFFC0C0C0),  // 银色
                Color(0xFF00CED1),  // 青色
                Color(0xFFB0C4DE),  // 浅银
                Color(0xFFD3D3D3),  // 亮灰
                Color(0xFFA9A9A9)   // 暗灰
            ), "时间循环型"),

            ReaderTheme("reader_12", "元素行者", ThemeColors(
                Color(0xFF32CD32),  // 绿色
                Color(0xFFFF8C00),  // 橙色
                Color(0xFF90EE90),  // 浅绿
                Color(0xFFFFA500),  // 橙
                Color(0xFF32CD32)   // 绿
            ), "自然元素型"),

            ReaderTheme("reader_13", "幻境法师", ThemeColors(
                Color(0xFF9400D3),  // 紫色
                Color(0xFFFF69B4),  // 粉色
                Color(0xFFDA70D6),  // 蓝紫
                Color(0xFFEE82EE),  // 淡紫
                Color(0xFF9932CC)   // 紫红
            ), "奇幻虚幻型"),

            ReaderTheme("reader_14", "正义使者", ThemeColors(
                Color(0xFF00008B),  // 深蓝
                Color(0xFFFFFF),  // 白色
                Color(0xFFE6E6FA),  // 淡紫
                Color(0xFFF8F8FF),  // 极淡紫
                Color(0xFFFFFFFF)   // 纯白
            ), "正义公平型"),

            ReaderTheme("reader_15", "暗夜行者", ThemeColors(
                Color(0xFF4B0082),  // 靛蓝
                Color(0xFF000000),  // 黑色
                Color(0xFF2D0040),  // 深靛蓝
                Color(0xFF1A0030),  // 靛黑
                Color(0xFF0D0018)   // 极黑
            ), "阴影秘密型"),

            ReaderTheme("reader_16", "治愈先知", ThemeColors(
                Color(0xFF32CD32),  // 绿色
                Color(0xFFFFFF),  // 白色
                Color(0xFF90EE90),  // 浅绿
                Color(0xFFFFFFFF),  // 白
                Color(0xFF98FB98)   // 苍绿
            ), "治愈希望型"),

            ReaderTheme("reader_17", "花仙子", ThemeColors(
                Color(0xFFFFB6C1),  // 淡粉
                Color(0xFF90EE90),  // 浅绿
                Color(0xFFFFF0F5),  // 淡粉红
                Color(0xFFFFE4E1),  // 淡粉
                Color(0xFFFFC0CB)   // 粉色
            ), "精灵可爱型"),

            ReaderTheme("reader_18", "冥河摆渡人", ThemeColors(
                Color(0xFF191970),  // 深蓝
                Color(0xFFFFFFFF),  // 白色
                Color(0xFF4B0082),  // 靛蓝
                Color(0xFF191970),  // 深蓝
                Color(0xFF2E0854)   // 紫黑
            ), "阴间引导型"),

            ReaderTheme("reader_19", "机甲大师", ThemeColors(
                Color(0xFFC0C0C0),  // 银色
                Color(0xFF00CED1),  // 青色
                Color(0xFFB0C4DE),  // 浅银
                Color(0xFFD3D3D3),  // 亮灰
                Color(0xFFA9A9A9)   // 暗灰
            ), "科技理性型"),

            ReaderTheme("reader_20", "野兽之王", ThemeColors(
                Color(0xFFDAA520),  // 金色
                Color(0xFF8B4513),  // 褐色
                Color(0xFFB8860B),  // 暗金
                Color(0xFFCD853F),  // 秘鲁色
                Color(0xFFDAA520)   // 金
            ), "野性力量型"),

            ReaderTheme("reader_21", "记忆编织者", ThemeColors(
                Color(0xFFF5F5DC),  // 米色
                Color(0xFFDDA0DD),  // 淡紫
                Color(0xFFF5DEB3),  // 小麦色
                Color(0xFFF5F5DC),  // 米色
                Color(0xFFFFE4C4)   // 奶油色
            ), "情感怀旧型"),

            ReaderTheme("reader_22", "星界领主", ThemeColors(
                Color(0xFF191970),  // 深蓝
                Color(0xFF00CED1),  // 青色
                Color(0xFF2F4F4F),  // 深岩灰
                Color(0xFF483D8B),  // 深石板蓝
                Color(0xFF191970)   // 深蓝
            ), "宇宙神秘型")
        )
    }

    // 获取占卜师主题
    fun getReaderTheme(readerId: String): ReaderTheme? {
        return getAllThemes().find { it.readerId == readerId }
    }

    // 获取 22 种占卜师
    @Composable
    fun getAllReaders(): List<ReaderTheme> {
        return getAllThemes()
    }

    // 获取当前主题色
    @Composable
    fun getPrimaryColor(): Color {
        return getCurrentTheme().primary
    }

    // 获取当前辅色
    @Composable
    fun getSecondaryColor(): Color {
        return getCurrentTheme().secondary
    }

    // 获取当前背景色
    @Composable
    fun getBackground1(): Color {
        return getCurrentTheme().background1
    }

    @Composable
    fun getBackground2(): Color {
        return getCurrentTheme().background2
    }

    @Composable
    fun getBackground3(): Color {
        return getCurrentTheme().background3
    }
}
