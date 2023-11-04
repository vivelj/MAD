package com.example.lr4

data class Player(var name: String, var isChecked: Boolean = false) {
    var teamName: String = ""
        get() = field
        set(value) {
            field = value
        }
}