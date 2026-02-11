package com.alucardulad.tarotcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化所有 ViewModel 和 ThemeManager
        ReaderSelectViewModel.init(this)
        DailyTarotViewModel.init(this)
        DrawHistoryViewModel.init(this)
        FavoritesViewModel.init(this)
        DailyDrawViewModel.init(this)
        AppreciationViewModel.init(this)
        ThemeManager.init(this)

        setContent {
            TarotCardsApp()
        }
    }
}
