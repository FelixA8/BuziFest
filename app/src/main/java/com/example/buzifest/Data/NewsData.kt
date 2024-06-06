package com.example.buzifest.Data

data class News(
    val id:String,
    val newsLinkUrl:String,
    val newsTitle:String,
    val newsImageUrl:String,
)

val DUMMY_NEWSDATA = News(
    generateUUID(),
    "https://www.pajak.com/komunitas/opini-pajak/serba-serbi-pajak-umkm-yang-perlu-diketahui/",
    "Serba-Serbi Pajak UMKM yang Perlu Diketahui",
    "https://www.pajak.com/storage/2022/09/UMKM-1024x641.png"
)