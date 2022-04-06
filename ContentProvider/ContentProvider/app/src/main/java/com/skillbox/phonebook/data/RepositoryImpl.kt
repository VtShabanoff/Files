package com.skillbox.phonebook.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skillbox.phonebook.domain.Contact
import com.skillbox.phonebook.domain.RepositoryContacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class RepositoryImpl(private val context: Context) : RepositoryContacts {

    private val phonePattern = Patterns.PHONE

    override suspend fun readContacts(): List<Contact> = withContext(Dispatchers.IO) {
        context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null,
            null
        )?.use { cursor ->
            getContactsFromCursor(cursor)
        }.orEmpty()
    }

    override suspend fun saveContact(
        name: String,
        familyName: String,
        phone: String,
        email: String
    ) {
        if (phonePattern.matcher(phone).matches().not() || name.isBlank()) {
            throw Exception("Incorrect phone number or name")
        }
        val contactId = saveRawContact()
        saveNameAndFamilyName(contactId, name, familyName)
        savePhone(contactId, phone)
        saveEmail(contactId, email)
    }

    override suspend fun saveEmail(id: Long, email: String) {
        val contentValues = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, id)
            put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE
            )
            put(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
        }

        context.contentResolver.insert(
            ContactsContract.Data.CONTENT_URI,
            contentValues
        )
    }

    override suspend fun deleteContact(id: Long): Int =
        withContext(Dispatchers.IO) {
            context.contentResolver.delete(
                ContactsContract.RawContacts.CONTENT_URI,
                "${ContactsContract.RawContacts._ID} = ?",
                arrayOf(id.toString())
            )
        }

    private fun saveRawContact(): Long {
        val uri = context.contentResolver
            .insert(ContactsContract.RawContacts.CONTENT_URI, ContentValues())
        return uri?.lastPathSegment?.toLongOrNull() ?: error("cannot save raw contact")
    }

    private fun saveNameAndFamilyName(contactId: Long, name: String, familyName: String) {
        val contentValues = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
            )
            put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name)
            put(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, familyName)
        }
        context.contentResolver
            .insert(ContactsContract.Data.CONTENT_URI, contentValues)
    }

    override suspend fun savePhone(contactId: Long, phone: String) {
        val contentValues = ContentValues().apply {
            put(ContactsContract.Data.RAW_CONTACT_ID, contactId)
            put(
                ContactsContract.Data.MIMETYPE,
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
            )
            put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
        }
        context.contentResolver
            .insert(ContactsContract.Data.CONTENT_URI, contentValues)
    }

    private fun getContactsFromCursor(cursor: Cursor): List<Contact> {
        if (cursor.moveToFirst().not()) return emptyList()
        val contacts = mutableListOf<Contact>()
        do {
            val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
            val id = cursor.getLong(idIndex)
            val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
            val name = cursor.getString(nameIndex)
            val contact = Contact(
                name,
                phoneNumbers = emptyList(),
                id = id,
                emails = emptyList()
            )
            contacts.add(contact)
        } while (cursor.moveToNext())
        return contacts
    }

    fun getPhonesFromContacts(contactId: Long): List<String> {
        return context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
            arrayOf(contactId.toString()),
            null
        )?.use { cursor ->
            getPhonesFromCursor(cursor)
        }.orEmpty()
    }

    private fun getPhonesFromCursor(cursor: Cursor): List<String> {
        if (cursor.moveToFirst().not()) return emptyList()
        val phones = mutableListOf<String>()

        do {
            val phoneIndex = cursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )
            val phone = cursor.getString(phoneIndex)
            phones.add(phone)
        } while (cursor.moveToNext())
        return phones
    }

    fun getEmailsFromContacts(id: Long): List<String> {
        return context.contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            null,
            "${ContactsContract.CommonDataKinds.Email.CONTACT_ID} = ?",
            arrayOf(id.toString()),
            null
        )?.use { cursor ->
            getEmailsFromCursor(cursor)
        }.orEmpty()
    }

    private fun getEmailsFromCursor(cursor: Cursor): List<String> {
        if (cursor.moveToFirst().not()) return emptyList()
        val emails = mutableListOf<String>()

        do {
            val emailIndex = cursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Email.ADDRESS
            )
            val email = cursor.getString(emailIndex)
            emails.add(email)
        } while (cursor.moveToNext())
        return emails
    }
}