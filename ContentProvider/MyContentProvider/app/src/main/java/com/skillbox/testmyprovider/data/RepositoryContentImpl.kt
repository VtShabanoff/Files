package com.skillbox.testmyprovider.data

import android.app.Application
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.skillbox.testmyprovider.domain.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class RepositoryContentImpl(val app: Application) {

    suspend fun updateCourseById(id: Long): Int =
        withContext(Dispatchers.IO) {
            app.contentResolver.update(
                COURSES_URI_ID,
                ContentValues().apply {
                    put(COLUMN_COURSE_ID, id)
                    put(COLUMN_COURSE_TITLE, "NEW TITLE")
                },
                "$COLUMN_COURSE_ID = ?",
                arrayOf(id.toString())
            )
        }

    suspend fun getAllCourses(): List<Course> =
        withContext(Dispatchers.IO) {
            app.contentResolver.query(
                COURSES_URI,
                null,
                null,
                null,
                null
            )?.use { cursor ->
                getCoursesFromCursor(cursor)
            }.orEmpty()
        }

    private fun getCoursesFromCursor(cursor: Cursor): List<Course> {
        if (cursor.moveToFirst().not()) return emptyList()

        val courses = mutableListOf<Course>()
        do {
            val idIndex = cursor.getColumnIndex(COLUMN_COURSE_ID)
            val id = cursor.getLong(idIndex)

            val titleIndex = cursor.getColumnIndex(COLUMN_COURSE_TITLE)
            val title = cursor.getString(titleIndex)

            courses.add(
                Course(
                    id = id,
                    title = title
                )
            )
        } while (cursor.moveToNext())

        return courses
    }

    suspend fun getCourseById(id: Long): Course =
        withContext(Dispatchers.IO) {
            app.contentResolver.query(
                COURSES_URI,
                null,
                "$COLUMN_COURSE_ID = ?",
                arrayOf(id.toString()),
                null
            )?.use { cursor ->
                getCourseFromCursor(id, cursor)
            } ?: error("error getting table")
        }

    private fun getCourseFromCursor(id: Long, cursor: Cursor): Course {
        if (cursor.moveToFirst().not()) throw error("error getting cursor data")

        val courseTitleIndex = cursor.getColumnIndex(COLUMN_COURSE_TITLE)
        val courseTitle = cursor.getString(courseTitleIndex)

        return Course(id, courseTitle)
    }

    suspend fun deleteAllCourses(): Int =
        withContext(Dispatchers.IO) {
            app.contentResolver.delete(
                COURSES_URI,
                null,
                null
            )
        }

    suspend fun deleteCourseById(id: Long): Int =
        withContext(Dispatchers.IO) {
            app.contentResolver.delete(
                COURSES_URI,
                "$COLUMN_COURSE_ID = ?",
                arrayOf(id.toString())
            )
        }

    suspend fun addCourse(title: String) =
        withContext(Dispatchers.IO) {
            val courseId = COURSES_URI_ID.lastPathSegment?.toLongOrNull()
                ?: error("Error getting raw contact ID")
            saveCourseTitle(courseId, title)
        }


    private fun saveCourseTitle(id: Long, title: String) {
        val contentValues = ContentValues().apply {
            // saving id
            put(COLUMN_COURSE_ID, id)
            // saving title
            put(COLUMN_COURSE_TITLE, title)
        }

       app.contentResolver.insert(
            COURSES_URI,
            contentValues
        )
    }

    companion object {
        private const val URI = "content://SkillboxContentProvider"
        private const val TYPE_URI = "/courses"
        private const val TYPE_URI_ID = "/courses/#"

        private val COURSES_URI = Uri.parse("$URI$TYPE_URI")
        private val COURSES_URI_ID = Uri.parse("$URI$TYPE_URI_ID")

        const val COLUMN_COURSE_ID = "column_course_id"
        const val COLUMN_COURSE_TITLE = "column_course_title"
    }
}