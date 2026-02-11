package com.alucardulad.tarotcards

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// AI 解析请求模型
@Serializable
data class ChatRequest(
    val model: String = "glm-4-flash",
    val messages: List<Message>
)

@Serializable
data class Message(
    val role: String,
    val content: String
)

// AI 解析响应模型
@Serializable
data class ChatResponse(
    val choices: List<Choice>
)

@Serializable
data class Choice(
    val message: Message
)

// 错误响应模型
@Serializable
data class ErrorResponse(
    val error: Error
)

@Serializable
data class Error(
    val message: String,
    val type: String,
    val code: Int
)

// AI 服务类
class AIService {
    private val json = Json { ignoreUnknownKeys = true }
    private val client = okhttp3.OkHttpClient.Builder()
        .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    // 默认 API 端点（需要配置）
    private val apiUrl = "https://open.bigmodel.cn/api/paas/v4/chat/completions"

    /**
     * 生成 AI 解析结果
     */
    suspend fun generateResponse(
        question: String,
        cards: List<CardModel>,
        readerId: String
    ): Result<String> {
        return try {
            // 获取占卜师风格
            val readerTheme = ThemeManager.getReaderTheme(readerId)
            val style = readerTheme?.description ?: "温柔导师型"

            // 构建 system prompt
            val systemPrompt = buildSystemPrompt(style, readerTheme?.name ?: "陈柔")

            // 构建 user prompt
            val userPrompt = buildUserPrompt(question, cards)

            // 创建请求
            val request = ChatRequest(
                model = "glm-4-flash",
                messages = listOf(
                    Message(role = "system", content = systemPrompt),
                    Message(role = "user", content = userPrompt)
                )
            )

            // 发送请求
            val requestBody = json.encodeToString(request)
            val body = okhttp3.RequestBody.create(
                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                requestBody
            )

            val requestObj = okhttp3.Request.Builder()
                .url(apiUrl)
                .addHeader("Authorization", "Bearer YOUR_API_KEY") // 需要配置
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build()

            val response = client.newCall(requestObj).execute()

            if (!response.isSuccessful) {
                return Result.failure(Exception("HTTP ${response.code}: ${response.message}"))
            }

            // 解析响应
            val responseBody = response.body?.string() ?: throw Exception("Empty response")
            val chatResponse = json.decodeFromString<ChatResponse>(responseBody)

            val reply = chatResponse.choices.firstOrNull()?.message?.content
                ?: throw Exception("No response from AI")

            Result.success(reply)

        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    /**
     * 构建 System Prompt
     */
    private fun buildSystemPrompt(style: String, name: String): String {
        return """
            你是一位专业的塔罗牌占卜师，名字叫 $name，风格是 $style。
            请根据用户的问题和抽到的塔罗牌，给出温暖、详细、有深度的解读。
            注意：
            1. 语气要符合占卜师风格
            2. 解读要深入，不肤浅
            3. 要有情感温度
            4. 给出具体建议
            5. 逐张牌解读，最后总结
        """.trimIndent()
    }

    /**
     * 构建 User Prompt
     */
    private fun buildUserPrompt(question: String, cards: List<CardModel>): String {
        val cardList = cards.joinToString("\n") { card ->
            "- ${card.name}: 正位${card.meaningUpright}, 逆位${card.meaningReversed}"
        }

        return """
            用户的问题：$question
            抽到的塔罗牌：
            $cardList
            请给出详细的解读和建议。
        """.trimIndent()
    }
}
