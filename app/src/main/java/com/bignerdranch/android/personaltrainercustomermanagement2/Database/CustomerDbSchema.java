package com.bignerdranch.android.personaltrainercustomermanagement2.Database;

/**
 * Created by mmedina4 on 9/25/2017.
 */

public class CustomerDbSchema {
    public static final class CustomerTable{
        public static final String NAME = "customers";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String FNAME = "fname";
            public static final String LNAME = "lname";
        }
    }
}
