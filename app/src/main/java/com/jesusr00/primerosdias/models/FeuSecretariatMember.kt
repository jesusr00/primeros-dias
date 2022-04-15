package com.jesusr00.primerosdias.models

class FeuSecretariatMember(
    name: String,
    lastName: String,
    username: String,
    val charge: String,
    val phoneNumber: Int,
    photo: Photo
): Person(name, lastName, username, photo)