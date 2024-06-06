package com.example.buzifest.Data

data class News(
    val id:String,
    val newsLinkUrl:String,
    val newsTitle:String,
    val newsImageUrl:String,
)

val DUMMY_NEWSDATA = News(
    generateUUID(),
    "https://asset.kompas.com/crops/IvP4MHLM1rHQi6KxQB7puhnu3iA=/0x0:1280x853/750x500/data/photo/2022/07/15/62d1817377bb8.jpeg",
    "Jadi Tempat Berdayakan UMKM, Fasilitas Itu Bernama Rest Area",
    "https://www.kompas.com/properti/read/2023/04/25/200000321/jadi-tempat-berdayakan-umkm-fasilitas-itu-bernama-rest-area?page=all"
)