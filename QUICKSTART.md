# Android 原生项目快速启动指南

## 📱 架构选择：Jetpack Compose

本项目使用 **Android 原生开发** + **Jetpack Compose** (声明式 UI 框架)

### 为什么选择 Jetpack Compose？
- ✅ **Android 官方推荐** - 2020 年 Google I/O 发布
- ✅ **现代化 UI** - 简洁的 API，减少样板代码
- ✅ **更好的性能** - 编译时检查，运行时优化
- ✅ **减少代码量** - 减少布局文件（XML）
- ✅ **强大的动画系统** - 内置动画 API
- ✅ **生命周期感知** - 自动管理生命周期

---

## 🚀 快速启动

### 1️⃣ 环境要求

- **Android Studio**: Hedgehog (2023.1.1) 或更高版本
- **JDK**: 8 或 11
- **Android SDK**: API 34 (Android 14)

### 2️⃣ 导入项目

#### 方法一：Android Studio 打开
```bash
cd /Users/alucardulad/Desktop/其他库/tarot_cards_android
# 在 Android Studio 中：File -> Open -> 选择项目根目录
```

#### 方法二：命令行打开
```bash
# 检查 Gradle
./gradlew --version

# 同步 Gradle
./gradlew tasks

# 构建项目
./gradlew build
```

### 3️⃣ 运行项目

#### 在模拟器上运行
```bash
# 启动模拟器
# Android Studio -> Device Manager -> 选择设备 -> Start

# 安装到模拟器
./gradlew installDebug

# 或者直接在 Android Studio 中点击 Run 按钮
```

#### 在真机上运行
```bash
# 1. 启用开发者选项和 USB 调试
# 2. 连接手机到电脑
# 3. 运行安装命令
./gradlew installDebug
```

### 4️⃣ 构建 APK

#### Debug 版本
```bash
./gradlew assembleDebug
# APK 位置: app/build/outputs/apk/debug/app-debug.apk
```

#### Release 版本（签名）
```bash
# 1. 生成签名密钥
keytool -genkey -v -keystore release.keystore -alias tarotcards -keyalg RSA -keysize 2048 -validity 10000

# 2. 配置 build.gradle（见下方）
# 3. 构建签名 APK
./gradlew assembleRelease
# APK 位置: app/build/outputs/apk/release/app-release.apk
```

---

## 📂 项目结构

```
tarot_cards_android/
├── app/
│   ├── build.gradle              # 模块构建配置
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml          # 应用清单
│   │   │   ├── java/com/alucardulad/tarotcards/
│   │   │   │   ├── MainActivity.kt          # 应用入口
│   │   │   │   └── HomeScreenViewModel.kt   # 主页面 ViewModel
│   │   │   └── res/
│   │   │       ├── values/
│   │   │       │   ├── colors.xml            # 颜色定义
│   │   │       │   ├── strings.xml           # 字符串资源
│   │   │       │   └── styles.xml            # 样式定义
│   │   │       ├── drawable/                 # 可绘制资源
│   │   │       └── mipmap/                   # 应用图标
│   │   └── build.gradle                     # 模块构建配置
├── build.gradle                   # 项目构建配置
├── settings.gradle                # 项目设置
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties  # Gradle 版本
├── gradle.properties              # Gradle 属性
├── .gitignore                     # Git 忽略文件
├── README.md                      # 项目说明
├── TODO.md                        # 待办事项
└── QUICKSTART.md                  # 快速启动指南（本文件）
```

---

## 🎨 UI 组件说明

### Compose 组件列表

| 组件 | 用途 | 状态 |
|------|------|------|
| `MaterialTheme` | Material 3 主题 | ✅ 已配置 |
| `Scaffold` | 页面结构 | ✅ 已实现 |
| `TopAppBar` | 顶部导航栏 | ✅ 已实现 |
| `Card` | 卡片组件 | ✅ 已实现 |
| `TextField` | 文本输入 | ✅ 已实现 |
| `Button` | 按钮 | ✅ 已实现 |
| `LazyColumn` | 滚动列表 | ✅ 已实现 |
| `Box` | 容器 | ✅ 已实现 |
| `Column` | 纵向布局 | ✅ 已实现 |
| `Row` | 横向布局 | ✅ 已实现 |

### 待实现组件
- `BottomNavigation` - 底部导航栏
- `NavigationRail` - 侧边导航
- `NavigationDrawer` - 抽屉导航
- `SnackBar` - 提示消息
- `AlertDialog` - 对话框

---

## 🎯 核心功能

### ✅ 已实现
1. **首页界面** - 每日塔罗牌主页
2. **问题输入** - 输入占卜问题
3. **卡牌抽选** - 模拟随机抽牌
4. **卡牌展示** - 显示抽到的 3 张牌
5. **深色主题** - Material Design 3 深色模式
6. **紫色配色** - 与 iOS 版本保持一致
7. **状态管理** - ViewModel 管理状态

### ⏳ 待实现
1. **底部导航栏** - 5 个功能入口
2. **导航栏** - 顶部导航配置
3. **卡片详情** - 点击卡片查看详情
4. **占卜师选择** - 22 种占卜师
5. **历史记录** - 保存占卜历史
6. **收藏功能** - 收藏喜欢的占卜师
7. **每日一签** - 单卡占卜
8. **AI 集成** - GLM API 解析
9. **网络请求** - OkHttp/Retrofit
10. **数据持久化** - SharedPreferences

---

## 🔧 配置说明

### 颜色配置 (colors.xml)
```xml
<color name="primary_color">#7D3FE1</color>      <!-- 主色：紫色 -->
<color name="secondary_color">#A5F2FF</color>    <!-- 辅色：青紫色 -->
<color name="background_1">#2D1344</color>       <!-- 背景色1：深紫 -->
<color name="background_2">#1E1233</color>       <!-- 背景色2：暗紫 -->
<color name="background_3">#120632</color>       <!-- 背景色3：紫黑 -->
```

### Gradle 配置 (app/build.gradle)
```gradle
android {
    namespace 'com.alucardulad.tarotcards'
    compileSdk 34
    defaultConfig {
        minSdk 24  // 最低 Android 7.0
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }
}
```

### 依赖库
```gradle
dependencies {
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    implementation 'com.squareup.okhttp3:okhttp:4.12.0'
    implementation 'com.google.code.gson:gson:2.10.1'
}
```

---

## 📊 代码结构

### MainActivity.kt
应用入口，负责启动 Compose UI

### HomeScreenViewModel.kt
ViewModel 管理页面状态：
- 问题文本
- 是否正在抽牌
- 抽到的卡牌列表
- 占卜师选择

### UI 组件
- `TarotCardsApp()` - 应用主题
- `HomeScreen()` - 主页面
- `CardIcon()` - 卡牌图标

---

## 🎯 开发计划

### Phase 1: 基础框架（当前）
- ✅ 项目初始化
- ✅ UI 框架搭建
- ✅ 颜色方案配置
- ✅ 基础页面实现

### Phase 2: 核心功能
- [ ] 底部导航栏
- [ ] 导航系统
- [ ] 卡片详情页面
- [ ] 数据模型完善

### Phase 3: 高级功能
- [ ] AI 集成
- [ ] 网络请求
- [ ] 数据持久化
- [ ] 状态管理

### Phase 4: 优化和发布
- [ ] 性能优化
- [ ] UI 优化
- [ ] 测试
- [ ] 签名打包
- [ ] 发布到应用商店

---

## 🚀 下一步

1. **在 Android Studio 中打开项目**
2. **同步 Gradle** (自动触发)
3. **选择设备并运行**
4. **测试现有功能**
5. **开始实现待办事项**

---

## 📚 参考资源

- [Jetpack Compose 官方文档](https://developer.android.com/jetpack/compose)
- [Material Design 3 文档](https://m3.material.io/)
- [Kotlin 协程指南](https://kotlinlang.org/docs/coroutines-guide.html)
- [Android 开发最佳实践](https://developer.android.com/topic/architecture)

---

**祝开发顺利！** 💪✨
