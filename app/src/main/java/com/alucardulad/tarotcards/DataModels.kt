// 卡牌数据模型
data class CardModel(
    val id: String,
    val name: String,
    val meaningUpright: String,
    val meaningReversed: String,
    val icon: String
)

// 占卜师数据模型
data class ReaderModel(
    val id: String,
    val name: String,
    val avatar: String,
    val tags: List<String>,
    val bio: String,
    val style: String,
    val isFavorite: Boolean = false
)

// 历史记录数据模型
data class HistoryEntry(
    val id: String,
    val question: String,
    val cards: List<CardModel>,
    val readerId: String,
    val timestamp: Long
)

// 主题颜色数据模型
data class ThemeColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val backgroundColor1: Color,
    val backgroundColor2: Color,
    val backgroundColor3: Color
)
