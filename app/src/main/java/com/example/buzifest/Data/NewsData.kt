package com.example.buzifest.Data

data class News(
    val id:String,
    val newsLinkUrl:String,
    val newsTitle:String,
    val newsImageUrl:String,
)

val DUMMY_NEWSDATA = News(
    generateUUID(),
    "https://ukmindonesia.id/baca-deskripsi-posts/potret-umkm-indonesia-si-kecil-yang-berperan-besar",
    "Potret UMKM Indonesia: Si Kecil yang Berperan Besar",
    "https://ukmindonesia.id/uploads/assets/cover_image/ee389f4409dca5fabaaf0ad105406799.jpg"
)