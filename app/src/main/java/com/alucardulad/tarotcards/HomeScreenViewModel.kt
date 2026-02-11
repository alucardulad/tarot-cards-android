package com.alucardulad.tarotcards

import androidx.lifecycle.ViewModel

class HomeScreenViewModel : ViewModel() {
    fun generateRandomCards(): List<CardModel> {
        return listOf(
            CardModel(
                id = "1",
                name = "愚人",
                meaningUpright = "新的开始、冒险、纯真",
                meaningReversed = "鲁莽、不成熟、冒险",
                icon = "🃏"
            ),
            CardModel(
                id = "2",
                name = "魔术师",
                meaningUpright = "创造力、意志力、能力",
                meaningReversed = "欺骗、滥用能力、失败",
                icon = "🪄"
            ),
            CardModel(
                id = "3",
                name = "女祭司",
                meaningUpright = "直觉、神秘、潜意识",
                meaningReversed = "被压抑的直觉、缺乏洞察力",
                icon = "🌙"
            )
        )
    }
}
