package cat.nyaa.nyaacoretester.database;

import cat.nyaa.nyaacore.database.DataColumn;
import cat.nyaa.nyaacore.database.DataTable;
import cat.nyaa.nyaacore.database.PrimaryKey;

@DataTable("test_table3")
public class TableRecord3 {
    @DataColumn
    @PrimaryKey
    public long key;

    @DataColumn
    public long data1;

    @DataColumn
    public long data2;
}