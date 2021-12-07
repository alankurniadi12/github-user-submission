package com.dicodingalan.sub2gituser.ui.main.dummy

import com.dicodingalan.sub2gituser.datamodel.ItemsItem
import com.dicodingalan.sub2gituser.datamodel.ResponseDetailUser

object DummyUsers {

    fun getSearchUser(): ArrayList<ItemsItem> {
        val users = ArrayList<ItemsItem>()

        users.add(ItemsItem(
            "alankurniadi",
            "https:image.com",
            123123
        ))

        users.add(ItemsItem(
            "alankurniadi2",
            "https:image.com",
            123124
        ))

        users.add(ItemsItem(
            "alankurniadi3",
            "https:image.com",
            123125
        ))

        users.add(ItemsItem(
            "alankurniadi4",
            "https:image.com",
            123126
        ))

        return users
    }

    fun getDetailUsers(): List<ResponseDetailUser>{
        val users = ArrayList<ResponseDetailUser>()
        users.add(ResponseDetailUser(
            "alankurniadi12",
            "AndroTech",
            123123,
            1000,
            1000,
            "https:image.com",
            1000,
            "Alan Kurniadi",
            "Riau"
        ))
        users.add(ResponseDetailUser(
            "alankurniadi12",
            "AndroTech",
            123123,
            1000,
            1000,
            "https:image.com",
            1000,
            "Alan Kurniadi",
            "Riau"
        ))
        users.add(ResponseDetailUser(
            "alankurniadi12",
            "AndroTech",
            123123,
            1000,
            1000,
            "https:image.com",
            1000,
            "Alan Kurniadi",
            "Riau"
        ))
        users.add(ResponseDetailUser(
            "alankurniadi12",
            "AndroTech",
            123123,
            1000,
            1000,
            "https:image.com",
            1000,
            "Alan Kurniadi",
            "Riau"
        ))
        return users
    }

}