package cat.nyaa.nyaacoretester.database;

import cat.nyaa.nyaacore.database.DataColumn;
import cat.nyaa.nyaacore.database.DataTable;
import org.bukkit.inventory.ItemStack;

@DataTable("test6_result_table")
public class Test6Result {
    @DataColumn
    public Long id;

    @DataColumn
    public ItemStack item;

    @DataColumn
    public Long checksum;
}
