package com.alucardulad.tarotcards

import android.content.Context
import androidx.lifecycle.ViewModel

// 占卜师选择 ViewModel
class ReaderSelectViewModel : ViewModel() {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("tarot_cards_pref", Context.MODE_PRIVATE)
    }

    var readers by mutableStateOf(readerList)

    init {
        loadFavorites()
    }

    // 加载收藏的占卜师
    private fun loadFavorites() {
        val favoriteIds = sharedPreferences.getStringSet("favorite_readers", emptySet()) ?: emptySet()
        readers = readers.map { reader ->
            reader.copy(isFavorite = favoriteIds.contains(reader.id))
        }
    }

    // 切换收藏状态
    fun toggleFavorite(readerId: String) {
        val favoriteIds = sharedPreferences.getStringSet("favorite_readers", emptySet())?.toMutableSet() ?: mutableSetOf()

        if (favoriteIds.contains(readerId)) {
            favoriteIds.remove(readerId)
        } else {
            favoriteIds.add(readerId)
        }

        sharedPreferences.edit()
            .putStringSet("favorite_readers", favoriteIds)
            .apply()

        loadFavorites()
    }

    // 选择占卜师
    fun selectReader(readerId: String) {
        sharedPreferences.edit()
            .putString("current_reader_id", readerId)
            .apply()
    }

    // 获取当前选择的占卜师
    fun getCurrentReaderId(): String {
        return sharedPreferences.getString("current_reader_id", "reader_1") ?: "reader_1"
    }

    companion object {
        private lateinit var context: Context

        fun init(context: Context) {
            this.context = context.applicationContext
        }
    }
}
