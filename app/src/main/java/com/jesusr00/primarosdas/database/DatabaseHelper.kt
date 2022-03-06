package com.jesusr00.primarosdas.database

import android.content.Context
import com.jesusr00.primarosdas.models.GuideTeachers
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseHelper(context: Context?): SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun getAllGuideTeachers(): ArrayList<GuideTeachers>{
        val result = ArrayList<GuideTeachers>()
        val queryCursor = this.readableDatabase.query("guide_teachers", null, null, null, null, null, null)
        while (queryCursor.moveToNext()){
            result.add(GuideTeachers(queryCursor.getString(0), queryCursor.getString(1), queryCursor.getString(2), queryCursor.getInt(3), queryCursor.getBlob(4)))
        }
        queryCursor.close()
        close()
        return result
    }

    companion object {
        private const val DATABASE_NAME = "info.db"
        private const val DATABASE_VERSION = 1
    }
}