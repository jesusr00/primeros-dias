package com.jesusr00.primerosdias.models

class GuideTeachers (
    name: String,
    lastName: String,
    username: String,
    val group: Int,
    photo: Photo
): Person(name, lastName, username, photo)
