package com.example.ftpnext.database;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static final String DATABASE_NAME = AppInfo.DATABASE_NAME;
    public static final int DATABASE_VERSION = AppInfo.DATABASE_VERSION;

    private static String TAG = "DATABASE : Database";

    private static TableTest1DAO sTableTest1Dao;

    private static DataBase sSingleton = null;
    private static boolean sDataBaseIsOpen = false;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (sSingleton != null)
            return sSingleton;
        return (sSingleton = new DataBase());
    }

    public static TableTest1DAO getTableTest1DAO() {
        return sTableTest1Dao;
    }

    public static void initDataDirectory() {

    }

    public static boolean isDataBaseIsOpen() {
        return sDataBaseIsOpen;
    }

    /**
     * @param iContext Context of the app
     * @return True if DataBase is opened.
     * @throws SQLException Exception during opening database.
     */
    public boolean open(Context iContext) throws SQLException {
        if (sDataBaseIsOpen)
            return true;

        LogManager.info(TAG, "Open database");

        List<String> lTableSchemaToCreate = new ArrayList<>();

        // Table Create
        lTableSchemaToCreate.add(ITableTest1Schema.TABLE_CREATE);

        DataBaseOpenHelper mDataBaseOpenHelper = new DataBaseOpenHelper(iContext, lTableSchemaToCreate);
        SQLiteDatabase lDataBase = mDataBaseOpenHelper.getWritableDatabase();

        //DAO list
        sTableTest1Dao = new TableTest1DAO(lDataBase);

        return (sDataBaseIsOpen = true);
    }
}
