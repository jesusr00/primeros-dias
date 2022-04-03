package com.jesusr00.primerosdias.database

import android.content.Context
import android.database.CursorIndexOutOfBoundsException
import com.jesusr00.primerosdias.models.DirectionCouncilMember
import com.jesusr00.primerosdias.models.Event
import com.jesusr00.primerosdias.models.GuideTeachers
import com.jesusr00.primerosdias.models.Photo
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseHelper(context: Context): SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    fun getAllGuideTeachers(): ArrayList<GuideTeachers>{
        val result = ArrayList<GuideTeachers>()
        val queryCursor = readableDatabase.rawQuery(GUIDE_TEACHERS, null);
        while (queryCursor.moveToNext()){
            result.add(GuideTeachers(queryCursor.getString(0), queryCursor.getString(1), queryCursor.getString(2), queryCursor.getInt(3), Photo(queryCursor.getInt(4),queryCursor.getBlob(5))))
        }
        queryCursor.close()
        close()
        return result
    }

    fun getAllDirectionCouncilMembers(): ArrayList<DirectionCouncilMember>{
        val result = ArrayList<DirectionCouncilMember>()
        val queryCursor = readableDatabase.rawQuery(DIRECTION_COUNCIL, null);
        while (queryCursor.moveToNext()){
            result.add(DirectionCouncilMember(queryCursor.getString(0), queryCursor.getString(1), queryCursor.getString(2), queryCursor.getString(3), Photo(queryCursor.getInt(4),queryCursor.getBlob(5))))
        }
        queryCursor.close()
        close()
        return result
    }

    fun getEventsByDayId(dayId: Int):ArrayList<Event> {
        val result = ArrayList<Event>()
        val queryCursor = readableDatabase.rawQuery("$EVENTS WHERE t_events.day_id = $dayId", null)
        while (queryCursor.moveToNext()) {
            result.add(Event(queryCursor.getInt(0), queryCursor.getLong(1), queryCursor.getLong(2), queryCursor.getString(3), queryCursor.getString(4), queryCursor.getString(5), queryCursor.getString(6)))
        }
        queryCursor.close()
        close()
        return result
    }

    fun getPhotoById(photoId: Int): Photo {
        var photo: Photo
        try {
            val queryCursor = readableDatabase.rawQuery("$PHOTO WHERE t_photo.id = $photoId", null)
            queryCursor.moveToFirst()
            photo = Photo(queryCursor.getInt(0), queryCursor.getBlob(1))
            queryCursor.close()
            close()
        } catch (e: CursorIndexOutOfBoundsException) {
            photo = Photo(null, null)
        }

        return photo
    }

    companion object {
        private const val DATABASE_NAME = "info.db"
        private const val DATABASE_VERSION = 1
        private const val GUIDE_TEACHERS = "SELECT name, last_name, username, brigade, photo_id, photo FROM main.r_guide_teachers INNER JOIN main.t_people ON r_guide_teachers.peopleId = t_people.id INNER JOIN main.t_brigade ON r_guide_teachers.groupId = t_brigade.id LEFT JOIN t_photo ON t_people.photo_id = t_photo.id"
        private const val DIRECTION_COUNCIL = "SELECT name, last_name, username, charge, photo_id, photo FROM main.r_direction_council INNER JOIN main.t_people ON r_direction_council.peopleId = t_people.id INNER JOIN main.t_charges ON r_direction_council.chargesId = t_charges.id LEFT JOIN t_photo ON t_people.photo_id = t_photo.id"
        private const val EVENTS = "SELECT day, start_time, end_time, title, description, location, type FROM t_events INNER JOIN t_day ON t_events.day_id = t_day.id"
        private const val PHOTO = "SELECT id, photo FROM main.t_photo"
    }
}