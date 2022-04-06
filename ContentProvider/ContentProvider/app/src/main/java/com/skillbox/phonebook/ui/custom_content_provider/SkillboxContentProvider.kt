package com.skillbox.phonebook.ui.custom_content_provider

import android.content.*
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.skillbox.phonebook.BuildConfig
import com.skillbox.phonebook.data.Course
import com.skillbox.phonebook.data.User
import com.squareup.moshi.Moshi

class SkillboxContentProvider : ContentProvider() {

    private lateinit var userPrefs: SharedPreferences
    private lateinit var coursePrefs: SharedPreferences

    private val userAdapter = Moshi.Builder().build().adapter(User::class.java)
    private val courseAdapter = Moshi.Builder().build().adapter(Course::class.java)

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITIES, PATH_USERS, TYPE_USERS)
        addURI(AUTHORITIES, PATH_COURSES, TYPE_COURSES)
        addURI(AUTHORITIES, "${PATH_USERS}/#", TYPE_USERS_ID)
        addURI(AUTHORITIES, "${PATH_COURSES}/#", TYPE_COURSES_ID)
    }

    override fun onCreate(): Boolean {
        userPrefs = context!!.getSharedPreferences("user_shared_pref", Context.MODE_PRIVATE)
        coursePrefs = context!!.getSharedPreferences("course_shared_pref", Context.MODE_PRIVATE)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            TYPE_USERS -> getAllUserCursor()
            TYPE_COURSES -> getAllCourseCursor()
            TYPE_COURSES_ID -> getCourseFromId(uri)
            else -> error("")
        }
    }

    private fun getCourseFromId(uri: Uri): Cursor? {
        val id = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return null

        val courseJsonString = coursePrefs.all.entries.first{
            it.key == id
        }.key ?: return null

        val course = courseAdapter.fromJson(courseJsonString) ?: error("")

        val cursor = MatrixCursor(arrayOf(COLUMN_COURSE_ID, COLUMN_COURSE_TITLE))

        return cursor.apply {
            newRow()
                .add(course.id)
                .add(course.title)
        }
    }

    private fun getAllUserCursor(): Cursor {
        val allUsers = userPrefs.all.mapNotNull {
            val userJsonString = it.value as String
            userAdapter.fromJson(userJsonString)
        }

        val cursor = MatrixCursor(arrayOf(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_AGE))
        allUsers.forEach { user ->
            cursor.newRow()
                .add(user.id)
                .add(user.name)
                .add(user.age)
        }
        return cursor
    }

    private fun getAllCourseCursor(): Cursor {
        val allCourses = coursePrefs.all.mapNotNull {
            val courseJoinString = it.value as String
            courseAdapter.fromJson(courseJoinString)
        }

        val cursor = MatrixCursor(arrayOf(COLUMN_COURSE_ID, COLUMN_COURSE_TITLE))
        allCourses.forEach { course ->
            cursor.newRow()
                .add(course.id)
                .add(course.title)
        }

        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        values ?: return null
        return when (uriMatcher.match(uri)) {
            TYPE_USERS -> saveUser(values)
            TYPE_COURSES -> saveCourses(values)
            else -> null
        }
    }

    private fun saveUser(contentValues: ContentValues): Uri? {
        val id = contentValues.getAsLong(COLUMN_USER_ID) ?: return null
        val name = contentValues.getAsString(COLUMN_USER_NAME) ?: return null
        val age = contentValues.getAsInteger(COLUMN_USER_AGE) ?: return null

        val user = User(id, name, age)


        userPrefs.edit()
            .putString(id.toString(), userAdapter.toJson(user))
            .apply()

        return Uri.parse("content://$AUTHORITIES/$PATH_USERS/$id")
    }

    private fun saveCourses(contentValues: ContentValues): Uri? {
        val id = contentValues.getAsLong(COLUMN_COURSE_ID) ?: return null
        val title = contentValues.getAsString(COLUMN_COURSE_TITLE) ?: return null
        val course = Course(id, title)
        coursePrefs.edit()
            .putString(id.toString(), courseAdapter.toJson(course))
            .apply()
        return Uri.parse("content://$AUTHORITIES/$PATH_COURSES/$id")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            TYPE_USERS_ID -> deleteUser(uri)
            TYPE_COURSES_ID -> deleteCourseFromId(uri)
            TYPE_COURSES -> deleteAllCourses()
            else -> 0
        }
    }

    private fun deleteCourseFromId(uri: Uri): Int {
        val id = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
        return if (coursePrefs.contains(id)) {
            coursePrefs.edit()
                .remove(id)
                .apply()
            1
        } else {
            0
        }
    }

    private fun deleteAllCourses(): Int {
        val result = coursePrefs.edit()
            .clear()
            .commit()
        return if (result) {
            1
        } else {
            0
        }

    }

    private fun deleteUser(uri: Uri): Int {
        val id = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
        return if (userPrefs.contains(id)) {
            userPrefs.edit()
                .remove(id)
                .apply()
            1
        } else {
            0
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        values ?: return 0
        return when (uriMatcher.match(uri)) {
            TYPE_USERS_ID -> updateUsers(uri, values)
            TYPE_COURSES_ID -> updateCourses(uri, values)
            else -> 0
        }
    }

    private fun updateUsers(uri: Uri, contentValues: ContentValues): Int {
        val id = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
        return if (userPrefs.contains(id)) {
            saveUser(contentValues)
            1
        } else {
            0
        }
    }

    private fun updateCourses(uri: Uri, contentValues: ContentValues): Int {
        val id = uri.lastPathSegment?.toLongOrNull()?.toString() ?: return 0
        return if (coursePrefs.contains(id)) {
            saveCourses(contentValues)
            1
        } else {
            0
        }
    }

    companion object {
        const val AUTHORITIES = "${BuildConfig.APPLICATION_ID}.provider"
        const val PATH_USERS = "users"
        const val PATH_COURSES = "courses"

        const val TYPE_USERS = 1
        const val TYPE_COURSES = 2

        const val TYPE_USERS_ID = 3
        const val TYPE_COURSES_ID = 4

        const val COLUMN_USER_ID = "id"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_AGE = "age"

        const val COLUMN_COURSE_ID = "column_course_id"
        const val COLUMN_COURSE_TITLE = "column_course_title"
    }
}