package com.jesusr00.primerosdias.models

class DirectionCouncilMember(
    name: String,
    lastName: String,
    username: String,
    val position: String,
    photo: Photo
): Person(name, lastName, username, photo)
