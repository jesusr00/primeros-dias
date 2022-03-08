package com.jesusr00.primarosdas.database

import android.content.Context
import com.jesusr00.primarosdas.models.GuideTeachers
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseHelper(context: Context): SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun getAllGuideTeachers(): ArrayList<GuideTeachers>{
        val result = ArrayList<GuideTeachers>()
        val queryCursor = readableDatabase.rawQuery(GUIDE_TEACHERS, null);
        while (queryCursor.moveToNext()){
            result.add(GuideTeachers(queryCursor.getString(0), queryCursor.getString(1), queryCursor.getString(2), queryCursor.getInt(3), null))
        }
        queryCursor.close()
        close()
        return result
    }

    companion object {
        private const val DATABASE_NAME = "info.db"
        private const val DATABASE_VERSION = 1
        private const val GUIDE_TEACHERS = "SELECT name, last_name, username, brigade FROM main.r_guide_teachers INNER JOIN t_people ON r_guide_teachers.peopleId = t_people.id INNER JOIN main.t_brigade ON r_guide_teachers.groupId = t_brigade.id"
    }
}