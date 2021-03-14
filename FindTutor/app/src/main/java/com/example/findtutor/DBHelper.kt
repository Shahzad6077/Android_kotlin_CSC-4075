package com.example.findtutor

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(private val appContext: Context) :
    SQLiteOpenHelper(appContext, "TutorTesting.db", null, 2) {
    override fun onCreate(MyDB: SQLiteDatabase) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)")
        MyDB.execSQL("create TABLE tutors(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,course TEXT,courseCode TEXT,qualification TEXT,email TEXT,phoneNo TEXT,city TEXT)")
        MyDB.execSQL("create Table favourites(username TEXT, tutorId INTEGER,CONSTRAINT pk_fav PRIMARY key (username,tutorId))")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase, i: Int, i1: Int) {
        MyDB.execSQL("drop Table if exists users")
        MyDB.execSQL("drop Table if exists tutors")
        MyDB.execSQL("drop Table if exists favourites")
    }

    fun addToFavTutor(tutorId: Int?): Boolean {
        val sharedPref = appContext.getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
        val loginUsername = sharedPref.getString("username", "")
        val myDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", loginUsername)
        contentValues.put("tutorId", tutorId)
        val result = myDB.insert("favourites", null, contentValues)
        return result != -1L

    }

    fun insertData(username: String?, password: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val result = MyDB.insert("users", null, contentValues)
        return result != -1L
    }

    fun checkusername(username: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery("Select * from users where username = ?", arrayOf(username))
        return if (cursor.count > 0) true else false
    }

    fun checkusernamepassword(username: String, password: String): Boolean {
        val MyDB = this.writableDatabase
        val cursor = MyDB.rawQuery(
            "Select * from users where username = ? and password = ?",
            arrayOf(username, password)
        )
        return cursor.count > 0
    }

    fun insertTutorEntry(
        name: String?, course: String?, courseCode: String?,
        qualification: String?, email: String?, phoneNo: String?,
        city: String?
    ): Boolean {
        val DB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("course", course)
        contentValues.put("courseCode", courseCode)
        contentValues.put("qualification", qualification)
        contentValues.put("email", email)
        contentValues.put("phoneNo", phoneNo)
        contentValues.put("city", city)
        val result = DB.insert("tutors", null, contentValues)
        return result != -1L
    }

    val tutorsList: Cursor
        get() {
            val DB = this.writableDatabase
            return DB.rawQuery("Select * from tutors", null)
        }

    fun getTutorListBySearch(value: String): Cursor {
        val DB = this.writableDatabase
        val cond1 = "ID=$value OR "
        val cond2 = "name LIKE '%$value%' OR "
        val cond3 = "course LIKE '%$value%' OR "
        val cond4 = "courseCode LIKE '%$value%' OR "
        val cond5 = "qualification LIKE '%$value%' OR "
        val cond6 = "email LIKE '%$value%' OR "
        val cond7 = "phoneNo LIKE '%$value%' OR "
        val cond8 = "city LIKE '%$value%';"
        val allCond = cond2 + cond3 + cond4 + cond5 + cond6 + cond7 + cond8
        return DB.rawQuery("Select * from tutors WHERE $allCond", null)
    }

    fun deleteTutor(tutorId: Int): Boolean {
        val MyDB = this.writableDatabase
        val query = "select * from tutors where ID=$tutorId ;"
        val cursor = MyDB.rawQuery(query, null)
         if (cursor.count > 0) {
            val result = MyDB.delete("tutors", "ID=?", arrayOf(tutorId.toString())).toLong()
            val deleteFavAlso =
                MyDB.delete("favourites", "tutorId=?", arrayOf(tutorId.toString())).toLong()
             return result != -1L
        } else {
             return false
        }
    }

    fun facouriteTutors(): Cursor {

        val sharedPref = appContext.getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
            val loginUsername = sharedPref.getString("username", "")
            val DB = this.writableDatabase
            return DB.rawQuery(
                "SELECT ID ,name  ,course  ,courseCode  ,qualification  ,email ,phoneNo,city" +
                        " FROM tutors INNER JOIN favourites ON tutors.ID = favourites.tutorId" +
                        " where favourites.username=?", arrayOf(loginUsername) );
    }

//    val facouriteTutors: Cursor
//        get() {
//            val sharedPref = appContext.getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
//            val loginUsername = sharedPref.getString("username", "")
//            val DB = this.writableDatabase
//            return DB.rawQuery(
//                "SELECT ID ,name  ,course  ,courseCode  ,qualification  ,email ,phoneNo,city" +
//                        " FROM tutors INNER JOIN favourites ON tutors.ID = favourites.tutorId" +
//                        " where favourites.username=?", arrayOf(loginUsername)
//            )
//        }

    fun deleteFavourite(tutorId: Int): Boolean {
        val sharedPref = appContext.getSharedPreferences("CHECKER", Context.MODE_PRIVATE)
        val loginUsername = sharedPref.getString("username", "")
        val MyDB = this.writableDatabase
        val query = "select * from favourites where tutorId=? AND username=?"
        val cursor = MyDB.rawQuery(query, arrayOf(tutorId.toString(), loginUsername))
         if (cursor.count > 0) {
            val deleteFavAlso = MyDB.delete(
                "favourites",
                "tutorId=? AND username=?",
                arrayOf(tutorId.toString(), loginUsername)
            ).toLong()
             return deleteFavAlso != -1L
        } else {
             return false
        }
    }

    companion object {
        const val DBNAME = "TutorTesting.db"
    }

    init {
        readableDatabase
        writableDatabase
    }
}