package com.example.data.model

import com.google.gson.annotations.SerializedName

class ResultPojo {

    @SerializedName("show")
    val show: ShowPojo? = null

    class ShowPojo{
        @SerializedName("name")
        var name: String = ""

        @SerializedName("genres")
        var genres: List<String> = emptyList()

        @SerializedName("image")
        var image: ImagePojo? = null

    }

    class ImagePojo{
        @SerializedName("medium")
        var mediumImageUrl = ""

        @SerializedName("original")
        var originalImageUrl = ""
    }

}