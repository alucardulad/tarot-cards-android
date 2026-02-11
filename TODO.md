# TODO List

## ✅ 已完成
- [x] 创建 Android 项目结构
- [x] 配置 Gradle 构建系统
- [x] 配置 AndroidManifest.xml
- [x] 配置颜色和样式资源
- [x] 实现 MainActivity.kt（Jetpack Compose）
- [x] 实现 HomeScreenViewModel.kt
- [x] 创建基础 UI（首页、问题输入、卡牌展示）
- [x] 配置依赖库

## 🚧 进行中
- [ ] 完成底部导航栏组件
- [ ] 添加导航栏组件

## ⏳ 待实现

### UI 组件
- [ ] 底部标签栏
- [ ] 导航栏
- [ ] 卡片详情页面
- [ ] 占卜师选择页面
- [ ] 历史记录页面
- [ ] 收藏页面
- [ ] 每日一签页面
- [ ] 粒子特效

### 功能模块
- [ ] 网络请求（AI 解析）
- [ ] 数据持久化
- [ ] 历史记录保存
- [ ] 收藏功能
- [ ] 占卜师切换
- [ ] 主题切换

### 优化
- [ ] 添加图标资源
- [ ] 性能优化
- [ ] 内存优化
- [ ] 适配不同屏幕尺寸
- [ ] 添加动画效果
- [ ] 测试

## 开发计划

### Phase 1: 基础框架（当前）
- ✅ 项目初始化
- ✅ UI 框架搭建（Jetpack Compose）
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

## 技术要点

### UI 框架
- **Jetpack Compose** - Android 原生推荐的声明式 UI 框架
- **Material Design 3** - 最新的设计规范
- **动画 API** - Compose 动画系统

### 架构模式
- **MVVM** - Model-View-ViewModel
- **单一数据源** - ViewModel 管理状态
- **生命周期感知** - ViewModel 自动管理生命周期

### 协程
- **协程作用域** - CoroutineScope 管理协程
- **协程调度** - Dispatchers.IO 用于网络请求
- **Flow** - 响应式数据流

## 注意事项

1. 所有 UI 组件使用 Compose API
2. ViewModel 管理 UI 状态
3. 协程用于异步操作（网络、AI 请求）
4. SharedPreferences 用于数据持久化
5. Material Design 3 设计规范
