package cat.nyaa.nyaacoretester.database;

import cat.nyaa.nyaacore.database.DataColumn;
import cat.nyaa.nyaacore.database.DataTable;
import cat.nyaa.nyaacore.database.PrimaryKey;
import org.bukkit.inventory.ItemStack;

@DataTable("test_table")
public class TableRecord {
    @DataColumn
    @PrimaryKey
    public long id;

    @DataColumn
    public ItemStack item;
}
