package com.example.myquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.myquizapp.QuizContract.QuestionsTable;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 30;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("GENERAL_KNOWLEDGE");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {

        Question q1 = new Question("Who built the Jama masjid?", "Akbar", "Shahjahan", "Jahangir", 2,
                Question.DIFFICULTY_EASY, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q1);
        Question q2 = new Question("Bihu is the folk dance of which state?", "Assam", "Manipur", "Sikkim", 1,Question.DIFFICULTY_EASY, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q2);
        Question q3 = new Question("Who is the fastest man in the world?", "Milka singh", "Usain bolt", "Justin Gatlin", 2,Question.DIFFICULTY_EASY, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q3);
        Question q4 = new Question("Which is the World's smallest country?", "Maldives", "Indonesia", "Vatican city", 3,Question.DIFFICULTY_EASY, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q4);
        Question q5 = new Question("what is the color of octopus blood?", "Red", "Green", "Blue", 3,Question.DIFFICULTY_EASY, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q5);
        Question q6 = new Question("which is the highest peak in India?", "Kanchenjunga", "Mt.Everest", "K2", 1,Question.DIFFICULTY_MEDIUM, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q6);
        Question q7 = new Question("Who is known as the nightingale of India?", "Mother Teresa", "Indira Gandhi", "sarojini Naidu", 3,Question.DIFFICULTY_MEDIUM, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q2);
        Question q8 = new Question("Where is the biggest statue of Jesus Christ?", "Brazil", "USA", "Australia", 1,Question.DIFFICULTY_MEDIUM, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q8);
        Question q9 = new Question("who discovered zero?", "galilieo", "Aryabhatta", "Newton", 2,Question.DIFFICULTY_MEDIUM, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q9);
        Question q10 = new Question("who was the first man or women to go into space?", "Yuri gagarin", "Neil Armstrong", "kalpana chawla", 1,Question.DIFFICULTY_MEDIUM, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q10);
        Question q11 = new Question("who wrote the book Macbeth?", "J.K.Rowling", "Leo Tolstoy", "Shakespeare", 3,Question.DIFFICULTY_HARD, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q11);
        Question q12 = new Question("Titanic sank in which ocean?", "Atlantic Ocean", "Pacific ocean", "Antartica ocean", 1,Question.DIFFICULTY_HARD, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q12);
        Question q13 = new Question("what is the capital of Oman?", "Muscat", "Jeddah", "Riyadh", 1,Question.DIFFICULTY_HARD, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q13);
        Question q14 = new Question("Brass is an alloy of?", "Copper and Iron", "Zinc and Iron", "Copper and Zinc", 3,Question.DIFFICULTY_HARD, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q14);
        Question q15 = new Question("Durand Cup is associated with which Game?", "Volleyball", "Hockey", "Football", 3,Question.DIFFICULTY_HARD, Category.GENERAL_KNOWLEDGE);
        insertQuestion(q15);
        Question q16= new Question("Which of the following is not OOPS concept in Java?",
                " Inheritance", "Polymorphism", "Compilation", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q16);
        Question q17= new Question(" Which of the following is a type of polymorphism in Java?",
                " Compile time polymorphism", "Execution time polymorphism", "Multiple polymorphism", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q17);
        Question q18= new Question("When does method overloading is determined?",
                " At run time", "At compile time", " At coding time", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q18);
        Question q19= new Question("Which concept of Java is a way of converting real world objects in terms of class?",
                " Polymorphism", " Encapsulation", " Abstraction", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q19);
        Question q20= new Question("Which concept of Java is achieved by combining methods and attribute into a class?",
                " Encapsulation", "Inheritance", "Polymorphism", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q20);
        Question q21= new Question("What is it called if an object has its own lifecycle and there is no owner?",
                " Aggregation", " Composition", "Association", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q21);
        Question q22= new Question("What is it called where child object gets killed if parent object is killed?",
                "Aggregation", " Composition", "Encapsulation", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q22);
        Question q23= new Question("What is it called where object has its own lifecycle and child object cannot belong to another parent object?",
                " Aggregation", "Composition", "Encapsulation", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q23);
        Question q24= new Question(" Method overriding is combination of inheritance and polymorphism?",
                " True", "false", "Cannot Say", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q24);

        Question q25 = new Question("If AB × AC = 2i^−4j^+4k^, then the are of ΔABC is",
                "3 sq. units", " 4 sq. units", "16 sq. units", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q25);
        Question q26 = new Question("The area of parallelogram whose adjacent sides are i^−2j^+3k^ and 2i^+j^−4k^ is",
                "10√6", "5√6", "10√3", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q26);
        Question q27 = new Question("If |a × b| = 4 and |a.b| = 2, then |a|2 |b|2 is equal to",
                "2", " 6", "20", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q27);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}