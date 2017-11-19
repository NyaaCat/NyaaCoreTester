package cat.nyaa.nyaacoretester.database;

import cat.nyaa.nyaacore.database.DataColumn;
import cat.nyaa.nyaacore.database.DataTable;
import cat.nyaa.nyaacore.database.PrimaryKey;

@DataTable("test_table4")
public class TableRecord4 {
    @DataColumn
    @PrimaryKey
    public long key;

    @DataColumn
    public long data1;

    @DataColumn
    public long data2;
}