package com.tranquilinside.logoquiz.network.serverResponse

data class LogoQuizItem(
    @SerializedName("imgUrl") val imageUrl: String,
    @SerializedName("name") val name: String
)