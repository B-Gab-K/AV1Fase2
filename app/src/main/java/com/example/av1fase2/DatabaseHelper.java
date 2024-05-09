package com.example.av1fase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AlunosDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "alunos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_TELEFONE = "telefone";
    public static final String COLUMN_CAMPUS = "campus";
    public static final String COLUMN_GENERO = "genero";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_TELEFONE + " TEXT,"
            + COLUMN_CAMPUS + " TEXT,"
            + COLUMN_GENERO + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int addAluno(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, aluno.getNome());
        values.put(COLUMN_EMAIL, aluno.getEmail());
        values.put(COLUMN_TELEFONE, aluno.getTelefone());
        values.put(COLUMN_CAMPUS, aluno.getCampus());
        values.put(COLUMN_GENERO, aluno.getGenero());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return (int) id;
    }

    public Aluno getAluno(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        Aluno aluno = null;
        if (cursor != null && cursor.moveToFirst()) {
            aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)));
            aluno.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE)));
            aluno.setCampus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAMPUS)));
            aluno.setGenero(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO)));
            cursor.close();
        }
        db.close();
        return aluno;
    }

    public List<Aluno> getAllAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                aluno.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                aluno.setNome(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME)));
                aluno.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)));
                aluno.setTelefone(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TELEFONE)));
                aluno.setCampus(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CAMPUS)));
                aluno.setGenero(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GENERO)));
                alunos.add(aluno);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return alunos;
    }

    public int updateAluno(Aluno aluno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, aluno.getNome());
        values.put(COLUMN_EMAIL, aluno.getEmail());
        values.put(COLUMN_TELEFONE, aluno.getTelefone());
        values.put(COLUMN_CAMPUS, aluno.getCampus());
        values.put(COLUMN_GENERO, aluno.getGenero());
        int rowsAffected = db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(aluno.getId())});
        db.close();
        return rowsAffected;
    }

    public void deleteAluno(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
