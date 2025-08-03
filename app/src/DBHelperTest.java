private DBHelper dbHelper;

@Before
public void setUp() {
    Context context = ApplicationProvider.getApplicationContext();
    dbHelper = new DBHelper(context);
    dbHelper.deleteAllData(); // clean slate
}

@After
public void tearDown() {
    dbHelper.close();
}

@Test
public void testInsertAndQuery() {
    boolean inserted = dbHelper.insertBMI("22.5", "2025-08-03 12:00");
    assertTrue(inserted);

    Cursor cursor = dbHelper.getAllData();
    assertNotNull(cursor);
    assertTrue(cursor.moveToFirst());

    String bmi = cursor.getString(cursor.getColumnIndexOrThrow("bmi"));
    String timestamp = cursor.getString(cursor.getColumnIndexOrThrow("timestamp"));

    assertEquals("22.5", bmi);
    assertEquals("2025-08-03 12:00", timestamp);

    cursor.close();
}

@Test
public void testDeleteAllData() {
    dbHelper.insertBMI("24.0", "2025-08-03 13:00");

    boolean deleted = dbHelper.deleteAllData();
    assertTrue(deleted);

    Cursor cursor = dbHelper.getAllData();
    assertNotNull(cursor);
    assertEquals(0, cursor.getCount());

    cursor.close();
}


