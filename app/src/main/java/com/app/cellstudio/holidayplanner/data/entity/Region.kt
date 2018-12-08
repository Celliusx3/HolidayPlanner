package com.app.cellstudio.holidayplanner.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by coyan on 12/10/2018.
 */
enum class Region(val region: String) {
    @SerializedName("Perak")
    PERAK("Perak"),
    @SerializedName("Kedah")
    KEDAH("Kedah"),
    @SerializedName("Penang")
    PENANG("Penang"),
    @SerializedName("Perlis")
    PERLIS("Perlis"),
    @SerializedName("Selangor")
    SELANGOR("Selangor"),
    @SerializedName("Negeri Sembilan")
    NEGERISEMBILAN("Negeri Sembilan"),
    @SerializedName("Melaka")
    MELAKA("Melaka"),
    @SerializedName("Johor")
    JOHOR("Johor"),
    @SerializedName("Pahang")
    PAHANG("Pahang"),
    @SerializedName("Terengganu")
    TERENGGANU("Terengganu"),
    @SerializedName("Kelantan")
    KELANTAN("Kelantan"),
    @SerializedName("Sabah")
    SABAH("Sabah"),
    @SerializedName("Sarawak")
    SARAWAK("Sarawak"),
    @SerializedName("Kuala Lumpur")
    KUALALUMPUR("Kuala Lumpur"),
    @SerializedName("Labuan")
    LABUAN("Labuan"),
    @SerializedName("Putrajaya")
    PUTRAJAYA ("Putrajaya")
}