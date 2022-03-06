package com.jesusr00.primarosdas.models

class GuideTeachers (val name: String, val lastName: String, val username: String, val group: Int, val photo: ByteArray?) {

    override fun toString(): String {
        return "GuideTeachers(name='$name', lastName='$lastName', username='$username', group=$group, photo=${photo?.contentToString()})"
    }
}