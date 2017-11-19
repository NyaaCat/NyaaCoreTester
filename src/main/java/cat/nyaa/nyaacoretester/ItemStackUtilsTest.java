package cat.nyaa.nyaacoretester;

import cat.nyaa.nyaacore.utils.ItemStackUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static cat.nyaa.nyaacoretester.Assertions.*;

public class ItemStackUtilsTest {
    @Test
    public void testItemStackUtils1() throws Exception{
        ItemStack i = new ItemStack(Material.WOOD);
        byte[] b = ItemStackUtils.itemToBinary(i);
        ItemStack ii = ItemStackUtils.itemFromBinary(b);
        assertEq(i, ii);
    }

    @Test
    public void testItemStackUtils2() throws Exception{
        ItemStack i = new ItemStack(Material.WOOD);
        String s = ItemStackUtils.itemToBase64(i);
        ItemStack ii = ItemStackUtils.itemFromBase64(s);
        assertEq(i, ii);
    }

    @Test
    public void testItemStackUtils3() throws Exception{
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0;i<127;i++) {
            ItemStack t = new ItemStack(Material.DIAMOND_SWORD);
            t.setDurability((short)i);
            list.add(t);
        }
        String s = ItemStackUtils.itemsToBase64(list);
        List<ItemStack> ii = ItemStackUtils.itemsFromBase64(s);
        assertEq(list, ii);
    }

    @Test
    public void testItemStackUtils4() throws Exception{
        ItemStack t = new ItemStack(Material.DIAMOND_SWORD);
        t.setDurability((short)223);
        String s=ItemStackUtils.itemToJson(t);
        assertNotEq(s, null);
        assertNotEq(s, "");
    }
}
