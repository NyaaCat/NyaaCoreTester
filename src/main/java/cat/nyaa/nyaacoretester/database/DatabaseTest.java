package cat.nyaa.nyaacoretester.database;

import cat.nyaa.nyaacore.database.SQLiteDatabase;
import cat.nyaa.nyaacoretester.NyaaCoreTester;
import cat.nyaa.nyaacoretester.Test;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cat.nyaa.nyaacoretester.Assertions.*;

public class DatabaseTest extends SQLiteDatabase{

    public DatabaseTest() {
        super();
        connect();
    }

    private void cleardb() {
        for (Class<?> c : getTables()) query(c).delete();
    }

    @Override
    protected String getFileName() {
        mark("db_filename_got");
        return "testdb.db";
    }

    @Override
    protected JavaPlugin getPlugin() {
        return NyaaCoreTester.instance;
    }

    @Override
    protected Class<?>[] getTables() {
        return new Class[] {
                TableRecord.class,
                TableRecord2.class,
                TableRecord3.class,
                TableRecord4.class
        };
    }

    @Test
    public void db_test1() {
        assertMarked("db_filename_got");
        cleardb();
        TableRecord r = new TableRecord();
        r.id = 42;
        r.item = new ItemStack(Material.WOOD);
        query(TableRecord.class).insert(r);
        ItemStack ret = query(TableRecord.class).whereEq("id", 42).selectUnique().item;
        assertEq(ret, r.item);
    }

    @Test
    public void db_test2() {
        cleardb();
        TableRecord2 r = new TableRecord2(33);
        query(TableRecord2.class).insert(r);
        TableRecord2 ret = query(TableRecord2.class).whereEq("id", 33).selectUnique();
        assertEq(ret, r);
    }

    @Test
    public void db_test3() {
        cleardb();
        Map<Long, TableRecord2> map = new HashMap<>();
        for (long i=0;i<30;i++) {
            TableRecord2 r = new TableRecord2(i);
            query(TableRecord2.class).insert(r);
            map.put(i, r);
        }

        for (TableRecord2 rr : query(TableRecord2.class).select()) {
            assertEq(rr, map.get(rr.lp));
        }
    }

    @Test
    public void db_test4() {
        cleardb();
        TableRecord2 r = new TableRecord2(42);
        r.dp = 42D;
        query(TableRecord2.class).insert(r);
        assertEq(query(TableRecord2.class).whereEq("id", (short)42).selectUnique(), r);
        assertEq(query(TableRecord2.class).whereEq("id", (byte)42).selectUnique(), r);
        assertEq(query(TableRecord2.class).whereEq("id", (int)42).selectUnique(), r);
        assertEq(query(TableRecord2.class).whereEq("id", (long)42).selectUnique(), r);

        assertEq(query(TableRecord2.class).whereEq("dp", 42D).selectUnique(), r);
        assertEq(query(TableRecord2.class).whereEq("dp", 42F).selectUnique(), r);
    }

    @Test
    public void db_test5() throws SQLException{
        cleardb();
        TableRecord2 r = new TableRecord2(42);
        r.dp = 42D;
        query(TableRecord2.class).insert(r);
        TableRecord2 rr = queryBundledAs("db_test5.sql", null, TableRecord2.class, 42).get(0);
        assertEq(r, rr);
    }

    @Test
    public void db_test6() {
        cleardb();
        long mod = 6;
        Map<Long, TableRecord2> map = new HashMap<>();
        for (long i=0;i<100;i++) {
            TableRecord2 r = new TableRecord2(i);
            r.lp %= mod;
            query(TableRecord2.class).insert(r);
            map.put(i, r);
        }

        for (long checksum=0;checksum<mod;checksum++) {
            List<Test6Result> list = queryBundledAs("db_test6.sql", null, Test6Result.class, checksum);
            for (Test6Result rr : list) {
                assertEq(rr.item, map.get(rr.id).i);
                assertEq(rr.checksum, checksum);
            }
        }
    }

    @Test
    public void db_test7() {
        cleardb();
        for (long i=0;i<100;i++) {
            queryBundled("db_test7_insert.sql", Collections.singletonMap("table_name", "test_table3"), i, i*2, i*3);
            queryBundled("db_test7_insert.sql", Collections.singletonMap("table_name", "test_table4"), i, i*4, i*5);
        }
        queryBundled("db_test7_update.sql", null);
        List<TableRecord3> list = query(TableRecord3.class).select();
        for (TableRecord3 rr : list) {
            assertEq(rr.data2, rr.key*3);
            if (rr.key % 4 == 0) {
                assertEq(rr.data1, rr.key*3/4);
            } else {
                assertEq(rr.data1, rr.key*2);
            }
        }
    }
}
