# Tarot Cards Android - Native Development

Android 原生开发的塔罗牌占卜应用

## 技术栈

- **语言**: Kotlin
- **最低 SDK**: 24 (Android 7.0)
- **目标 SDK**: 34 (Android 14)
- **架构**: Material Design 3
- **UI 框架**: Jetpack Compose
- **依赖管理**: Gradle

## 项目结构

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/alucardulad/tarotcards/
│   │   │   ├── MainActivity.kt              # 应用入口
│   │   │   └── HomeScreenViewModel.kt       # 主页面 ViewModel
│   │   ├── res/
│   │   │   ├── layout/                      # XML 布局文件（可选）
│   │   │   ├── values/
│   │   │   │   ├── colors.xml               # 颜色定义
│   │   │   │   ├── strings.xml              # 字符串资源
│   │   │   │   └── styles.xml               # 样式定义
│   │   │   ├── drawable/                    # 可绘制资源
│   │   │   ├── mipmap/                      # 应用图标
│   │   │   └── xml/                         # XML 配置文件
│   │   └── AndroidManifest.xml              # 应用清单文件
│   └── build.gradle                         # 模块构建配置
gradle/
├── wrapper/
│   └── gradle-wrapper.properties            # Gradle 版本
└── build.gradle                             # 项目构建配置
```

## 快速开始

### 1. 环境准备
- Android Studio Hedgehog (2023.1.1) 或更高版本
- JDK 8+
- Android SDK 34

### 2. 导入项目
```bash
cd /Users/alucardulad/Desktop/其他库/tarot_cards_android
# 使用 Android Studio 打开项目
# 或者
./gradlew assembleDebug
```

### 3. 运行应用
```bash
./gradlew installDebug
```

### 4. 构建 APK
```bash
./gradlew assembleRelease
```

APK 位置: `app/build/outputs/apk/release/app-release.apk`

## 核心功能

### ✅ 已实现
- ✅ Material Design 3 UI
- ✅ 深色主题
- ✅ 紫色系配色方案
- ✅ 首页界面
- ✅ 问题输入
- ✅ 卡牌抽选（模拟）
- ✅ 卡牌展示
- ✅ ViewModel 状态管理

### ⏳ 待实现
- ⏳ 底部标签栏
- ⏳ 导航栏
- ⏳ 卡片详情页面
- ⏳ 占卜师选择页面
- ⏳ 历史记录页面
- ⏳ 收藏页面
- ⏳ 每日一签
- ⏳ AI 解析集成
- ⏳ 网络请求
- ⏳ 数据持久化（SharedPreferences）

## 配置说明

### 最低版本
- `minSdk 24` (Android 7.0)

### 权限
- `INTERNET` - 用于网络请求
- `ACCESS_NETWORK_STATE` - 检查网络状态

### 依赖库
- `androidx.core:core-ktx` - Core KTX 扩展
- `androidx.appcompat:appcompat` - AppCompat 兼容库
- `com.google.android.material:material` - Material Design 组件
- `androidx.constraintlayout:constraintlayout` - 约束布局
- `androidx.recyclerview:recyclerview` - 列表视图
- `androidx.cardview:cardview` - 卡片视图
- `com.squareup.okhttp3:okhttp` - HTTP 客户端
- `com.google.code.gson:gson` - JSON 序列化

## UI 设计

### 颜色方案
```kotlin
primaryColor: #7D3FE1      // 主色（紫色）
secondaryColor: #A5F2FF    // 辅色（青紫色）
background1: #2D1344       // 背景色1（深紫）
background2: #1E1233       // 背景色2（暗紫）
background3: #120632       // 背景色3（紫黑）
```

### 设计规范
- **圆角**: 22dp（大按钮）、16dp（卡片）
- **动画时长**: 0.3s
- **间距**: 16dp
- **字体**: Material 3 标准字体

## Jetpack Compose 组件

### 已使用组件
- `MaterialTheme` - Material 3 主题
- `Scaffold` - 页面结构
- `TopAppBar` - 顶部导航栏
- `Card` - 卡片组件
- `TextField` - 文本输入
- `Button` - 按钮
- `LazyColumn` - 滚动列表
- `Box` - 容器
- `Column` - 纵向布局
- `Row` - 横向布局
- `Box` - 层叠容器

### 待实现组件
- `BottomNavigation` - 底部导航栏
- `NavigationRail` - 侧边导航
- `NavigationDrawer` - 抽屉导航
- `SnackBar` - 提示消息
- `AlertDialog` - 对话框
- `Chip` - 标签
- `Divider` - 分割线
- `Spacer` - 间距

## AI 集成计划

### API 配置
- **模型**: GLM-4.7-Flash
- **请求方式**: POST
- **请求体**: JSON 格式

### 请求结构
```json
{
  "messages": [
    {
      "role": "system",
      "content": "系统提示词（根据占卜师风格）"
    },
    {
      "role": "user",
      "content": "用户的问题"
    }
  ]
}
```

### 响应处理
```kotlin
data class AIResponse(
    val choices: List<Choice>,
    val usage: Usage
)

data class Choice(
    val message: Message,
    val finish_reason: String
)
```

## 数据持久化

### SharedPreferences
```kotlin
// 保存占卜师选择
pref.edit().putString("current_reader_id", readerId).apply()

// 读取占卜师选择
val readerId = pref.getString("current_reader_id", null)
```

### 历史记录
```kotlin
// JSON 格式保存
val historyJson = Gson().toJson(historyList)
pref.edit().putString("divination_history", historyJson).apply()
```

## 网络请求

### OkHttp 客户端
```kotlin
val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()
```

### Retrofit API 服务
```kotlin
interface AIApiService {
    @POST("/chat/completions")
    suspend fun generateResponse(@RequestBody request: ChatRequest): AIResponse
}
```

## 性能优化

### 1. 图片优化
- 使用 WebP 格式
- 压缩图片尺寸
- 使用缓存机制

### 2. 列表优化
- 使用 `LazyColumn` 替代 `RecyclerView`
- 图片使用 Coil 或 Glide 加载

### 3. 内存优化
- 避免内存泄漏
- 及时释放资源
- 使用 `ViewModel` 管理生命周期

## 测试

### 单元测试
```kotlin
@Test
fun `test card generation`() {
    val viewModel = HomeScreenViewModel()
    val cards = viewModel.generateRandomCards()
    assertEquals(3, cards.size)
}
```

### UI 测试
```kotlin
@Test
fun `test question input`() {
    composeTestRule.onNodeWithText("你的问题")
        .assertIsDisplayed()
}
```

## 发布

### 1. 签名 APK
```bash
# 在 build.gradle 中配置签名
android {
    signingConfigs {
        release {
            storeFile file("release.keystore")
            storePassword "your_password"
            keyAlias "your_alias"
            keyPassword "your_password"
        }
    }
}
```

### 2. 上传到应用商店
- Google Play Console
- 提交审核

## 开发计划

### 第一阶段（当前）
- [x] 项目初始化
- [x] 基础 UI 实现
- [x] 颜色方案配置
- [ ] 底部导航栏

### 第二阶段
- [ ] 占卜师选择页面
- [ ] 卡片详情页面
- [ ] 历史记录页面
- [ ] 数据持久化

### 第三阶段
- [ ] AI 集成
- [ ] 网络请求
- [ ] 粒子特效
- [ ] 动画效果

### 第四阶段
- [ ] 测试
- [ ] 优化
- [ ] 发布

## 注意事项

1. **Jetpack Compose** 是 Android 原生推荐的 UI 框架
2. **Kotlin** 是官方首选语言
3. **Material Design 3** 是最新的设计规范
4. **ViewModel** 用于管理 UI 相关的数据
5. **协程** 用于异步操作

## 参考资源

- [Kotlin 官方文档](https://kotlinlang.org/docs/home.html)
- [Jetpack Compose 文档](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Android 开发最佳实践](https://developer.android.com/topic/architecture)
