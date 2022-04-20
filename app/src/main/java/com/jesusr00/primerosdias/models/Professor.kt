package com.jesusr00.primerosdias.models

class Professor(
    name: String,
    lastName: String,
    username: String,
    val classType: String,
    val teacherType: String,
    val subject: String,
    photo: Photo
):Person(name, lastName, username, photo)