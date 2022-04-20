package com.jesusr00.primerosdias.database

import android.content.Context
import android.database.CursorIndexOutOfBoundsException
import com.jesusr00.primerosdias.models.DirectionCouncilMember
import com.jesusr00.primerosdias.models.Event
import com.jesusr00.primerosdias.models.GuideTeachers
import com.jesusr00.primerosdias.models.Photo
import com.jesusr00.primerosdias.models.*
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


    fun getFeuSecretariatMembers(): ArrayList<FeuSecretariatMember> {
        val result = ArrayList<FeuSecretariatMember>()
        val queryCursor = readableDatabase.rawQuery(FEU_SECRETARIAT, null);
        while (queryCursor.moveToNext()){
            result.add(FeuSecretariatMember(queryCursor.getString(0), queryCursor.getString(1), queryCursor.getString(2), queryCursor.getString(3), queryCursor.getInt(4) ,Photo(queryCursor.getInt(5),queryCursor.getBlob(6))))
        }
        queryCursor.close()
        close()
        return result
    }

    fun getCloisterByGroup(groupId: Int): ArrayList<Professor>{
        val professors = ArrayList<Professor>()
        val queryCursor = readableDatabase.rawQuery("$CLOISTER$groupId", null);
        while (queryCursor.moveToNext()){
            professors.add(Professor(queryCursor.getString(0), queryCursor.getString(1), queryCursor.getString(2), queryCursor.getString(3), queryCursor.getString(4), queryCursor.getString(5) ,Photo(queryCursor.getInt(6),queryCursor.getBlob(7))))
        }
        queryCursor.close()
        close()
        return professors
    }
    
    companion object {
        private const val DATABASE_NAME = "info.db"
        private const val DATABASE_VERSION = 1
        private const val GUIDE_TEACHERS = "SELECT name, last_name, username, brigade, photo_id, photo FROM main.r_guide_teachers INNER JOIN main.t_people ON r_guide_teachers.peopleId = t_people.id INNER JOIN main.t_brigade ON r_guide_teachers.groupId = t_brigade.id LEFT JOIN t_photo ON t_people.photo_id = t_photo.id"
        private const val DIRECTION_COUNCIL = "SELECT name, last_name, username, charge, photo_id, photo FROM main.r_direction_council INNER JOIN main.t_people ON r_direction_council.peopleId = t_people.id INNER JOIN main.t_charges ON r_direction_council.chargesId = t_charges.id LEFT JOIN t_photo ON t_people.photo_id = t_photo.id"
        private const val EVENTS = "SELECT day, start_time, end_time, title, description, location, type FROM t_events INNER JOIN t_day ON t_events.day_id = t_day.id"
        private const val PHOTO = "SELECT id, photo FROM main.t_photo"
        private const val FEU_SECRETARIAT = "SELECT name, last_name, username, charge, phone_number, photo_id, photo FROM r_feu_secretariat INNER JOIN t_people ON r_feu_secretariat.people_id = t_people.id INNER JOIN t_charges ON r_feu_secretariat.charge_id = t_charges.id LEFT JOIN t_photo ON t_people.photo_id = t_photo.id"
        private const val CLOISTER = "SELECT t_people.name, last_name, username, class_type, teacher_type, t_subjects.name, photo_id, photo FROM r_cloister INNER JOIN t_people ON r_cloister.people_id = t_people.id INNER JOIN t_class_type ON r_cloister.class_type_id = t_class_type.id INNER JOIN t_teacher_type ON r_cloister.teacher_type_id = t_teacher_type.id INNER JOIN t_subjects ON r_cloister.subjects_id = t_subjects.id LEFT JOIN t_photo ON t_people.photo_id = t_photo.id WHERE r_cloister.brigade_id = "
    }
}