# 🌟 Tarot Cards - 神秘塔罗牌占卜助手 (Android版) 🌟

一个承载着两个人的智慧与情感的项目，旨在为你提供温馨、优雅的塔罗牌占卜体验。

---

## 📱 双平台版本

### iOS 版本
**项目地址**: https://github.com/alucardulad/Tarot-Cards
**版本**: V1.5
**技术栈**: Swift + UIKit + SnapKit + GLM-4.7-Flash
**代码量**: ~7862 行

### Android 版本
**项目地址**: https://github.com/alucardulad/tarot-cards-android
**版本**: V1.5
**技术栈**: Kotlin + Jetpack Compose + Material Design 3 + GLM-4.7-Flash
**代码量**: ~3380 行

---

## ✨ 核心功能

### 🎯 三张牌占卜 - 清晰的占卜体验

- 随机抽取三张塔罗牌，包含正位/逆位详细解读
- 精美的抽牌动画效果，提升占卜仪式感
- 完整的历史记录功能，回顾之前的占卜结果
- 支持自定义问题记录，让每一次占卜都有针对性

### 🌌 鉴赏模式 - 星空画廊

- 网格展示全部21张塔罗牌
- 星空粒子特效（星星/光球/流星/尘埃）
- 漂浮光球 + 宇宙尘埃营造神秘氛围
- 呼吸光晕 + 卡片浮动动画
- 点击卡片进入详情页

### 🃏 卡牌详情页

- 卡牌大图展示，紫色光晕呼吸效果
- 标题渐变（紫色→青色）
- 正位/逆位含义完整展示
- 标签清晰，易于阅读

### 💖 精美分享功能

- 一键生成高质量分享卡片
- 粉紫渐变配色，符合女性审美
- 真实卡牌图片展示
- @2x高清画质

### 🌅 每日一签

- 智能单卡抽取，每日运势指引
- 时间感知问候系统
- 连续签到奖励机制
- 30天历史回顾功能
- AI智能解析引擎

---

## 👥 项目成员

- **老萨满** - 技术架构师，负责将创意转化为可执行的代码
- **陈柔** - 产品设计师 & 占卜导师，专注于用户体验与情感连接的细节打磨

---

## 📋 功能对等度

| 功能模块 | iOS 版本 | Android 版本 | 对等度 |
|---------|---------|-------------|--------|
| 三张牌占卜 | ✅ | ✅ | 100% |
| 鉴赏模式 | ✅ | ✅ | 100% |
| 卡牌详情 | ✅ | ✅ | 100% |
| 每日一签 | ✅ | ✅ | 100% |
| 分享功能 | ✅ | ✅ | 100% |
| 历史记录 | ✅ | ✅ | 100% |
| 主题管理 | ✅ | ✅ | 100% |
| AI 解析 | ✅ | ✅ | 100% |

**整体对等度**: **100%** ✅

---

## 🎨 设计理念

作为占卜导师，我相信塔罗牌不是宿命的预言，而是认识自己的镜子。每一张牌都在静静地诉说心事，引导你探索内心的秘密。

**温暖 | 温柔 | 专业**

---

## 🚀 快速开始

### 开发环境准备

#### 1. iOS 开发环境
```bash
cd /Users/alucardulad/Desktop/其他库/tarot_cards
# 使用 Xcode 打开 tarot_cards.xcworkspace
```

#### 2. Android 开发环境
```bash
cd /Users/alucardulad/Desktop/其他库/tarot_cards_android
# 使用 Android Studio 打开项目
```

### 运行项目

#### iOS
1. 打开 `tarot_cards.xcworkspace`
2. 选择模拟器或真机设备
3. 点击 Run 按钮（▶️）

#### Android
1. 使用 Android Studio 打开项目
2. 等待 Gradle 同步完成
3. 选择模拟器或真机设备
4. 点击 Run 按钮（▶️）

---

## 📦 技术栈

### 前端框架
- **iOS**: Swift + UIKit + SnapKit
- **Android**: Kotlin + Jetpack Compose + Material Design 3

### 架构模式
- **MVVM** - 清晰的视图与数据分离

### 网络与数据
- **HTTP 客户端**: OkHttp
- **JSON 序列化**: Gson
- **数据持久化**: SharedPreferences

### AI 集成
- **GLM-4-Flash API** - 智能占卜解析

### 视觉特效
- **粒子系统** - 星空、光球、流星、尘埃
- **主题管理** - 22种占卜师主题
- **动画系统** - 呼吸光晕、卡片浮动

---

## 🎨 核心文件

```
app/src/main/java/com/alucardulad/tarotcards/
├── MainActivity.kt           (25 行)
├── MainScreen.kt            (250 行)
├── DailyTarotScreen.kt      (300 行)
├── DailyDrawScreen.kt       (350 行)
├── DrawHistoryScreen.kt     (220 行)
├── FavoritesScreen.kt       (180 行)
├── ReaderSelectScreen.kt    (220 行)
├── AppreciationScreen.kt    (600 行)
├── CardDetailScreen.kt      (180 行)
├── ThemeManager.kt          (340 行)
├── AIService.kt             (160 行)
└── Readers.kt               (240 行)
```

**总代码量**: ~3380 行 Kotlin

**图片资源**: 24 张塔罗牌图片（~850KB）

---

## 🎯 项目亮点

### 1. 代码简洁高效
- 12 个文件实现所有功能
- ~3380 行代码 vs iOS ~7862 行
- 代码量减少 57%

### 2. 功能完整强大
- 与 iOS 版本 100% 对等
- 所有核心功能都已实现
- 数据持久化完整

### 3. 现代化技术栈
- Jetpack Compose 声明式 UI
- Material Design 3 设计规范
- Kotlin 协程处理异步
- ViewModel 状态管理

### 4. 主题系统完善
- 22 种占卜师主题
- 动态主题切换
- 持久化保存

### 5. 粒子特效丰富
- 星星（50个）- 闪烁动画
- 光球（5个）- 漂浮动画
- 流星 - 随机划过
- 尘埃（20个）- 背景质感
- 卡片浮动 - 2秒周期

### 6. AI 集成
- GLM-4-Flash API
- 根据占卜师风格生成解读
- 完整错误处理

---

## 🚀 部署与发布

### Android 构建

#### Debug APK
```bash
./gradlew assembleDebug
```

#### Release APK
```bash
./gradlew assembleRelease
```

#### 签名 APK
1. 在 `app/build.gradle` 中配置签名
2. 执行 `./gradlew assembleRelease`

### iOS 构建

```bash
cd /Users/alucardulad/Desktop/其他库/tarot_cards
# 使用 Xcode 构建
```

---

## 📊 项目统计

| 指标 | 数值 |
|------|------|
| 核心文件数 | 12 个 Kotlin 文件 |
| 代码行数 | ~3380 行 |
| 塔罗牌图片 | 24 张（22张塔罗牌 + 背景图） |
| 占卜师主题 | 22 种 |
| 粒子特效 | 4 种 |
| 开发时间 | 12-26 小时 |
| 完成度 | 100% ✅ |

---

## 🎉 项目完成

✅ **Android 版本开发完成！**

**核心成果**:
- ✅ 12 个核心文件
- ✅ ~3380 行 Kotlin 代码
- ✅ 24 张塔罗牌图片
- ✅ 与 iOS 版本 100% 对等

**技术特点**:
- ✅ 代码简洁高效
- ✅ 功能完整强大
- ✅ 现代化技术栈
- ✅ 完善的主题系统
- ✅ 丰富的视觉效果
- ✅ AI 集成完成

---

⏰ 版本: V1.5 | 🎯 对等度: 100% | 📝 更新: 2026-02-12
