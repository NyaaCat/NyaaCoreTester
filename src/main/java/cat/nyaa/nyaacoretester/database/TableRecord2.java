package cat.nyaa.nyaacoretester.database;

import cat.nyaa.nyaacore.database.DataColumn;
import cat.nyaa.nyaacore.database.DataTable;
import cat.nyaa.nyaacore.database.PrimaryKey;
import cat.nyaa.nyaacoretester.Assertions;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.ZonedDateTime;
import java.util.Random;

/*
 * A database column can be:
 *   1. A field of acceptable type: long/double/bool/Enum/String/ItemStack
 *   2. A field can be serialized/deserialized using toString() and fromString()/parse() (e.g. ZonedDateTime)
 *   3. A pair of getter/setter returning/accepting type listed in (1)
 */
@DataTable("test_table2")
public class TableRecord2 {
    private static Random rnd = new Random();
    public TableRecord2() {}
    public TableRecord2(long id) {
        this.lp = id;
        dp = rnd.nextInt();
        bp = rnd.nextBoolean();
        L = id;
        D = Double.valueOf(rnd.nextInt());
        B = rnd.nextBoolean();

        Material[] ms = Material.values();

        m = ms[rnd.nextInt(ms.length)];
        s = D.toString();
        i = new ItemStack(Material.DIAMOND_SWORD);
        i.setDurability((short)rnd.nextInt(255));
        z = ZonedDateTime.now().minusDays(rnd.nextInt(800));

        l2p = id;
        d2p = rnd.nextInt();
        b2p = rnd.nextBoolean();
        L2 = id;
        D2 = Double.valueOf(rnd.nextInt());
        B2 = rnd.nextBoolean();

        m2 = ms[rnd.nextInt(ms.length)];
        s2 = D2.toString();
        i2 = new ItemStack(Material.DIAMOND_SWORD);
        i2.setDurability((short)rnd.nextInt(255));
    }

    @Override
    public boolean equals(Object obj) {
        TableRecord2 o = (TableRecord2)obj;
        Assertions.assertEq(lp, o.lp);

        Assertions.assertEq(lp, o.lp);
        Assertions.assertEq(dp, o.dp);
        Assertions.assertEq(bp, o.bp);
        Assertions.assertEq(L, o.L);
        Assertions.assertEq(D, o.D);
        Assertions.assertEq(B, o.B);
        Assertions.assertEq(m, o.m);
        Assertions.assertEq(s, o.s);
        Assertions.assertEq(i, o.i);
        Assertions.assertEq(z, o.z);

        Assertions.assertEq(l2p, o.l2p);
        Assertions.assertEq(d2p, o.d2p);
        Assertions.assertEq(b2p, o.b2p);
        Assertions.assertEq(L2, o.L2);
        Assertions.assertEq(D2, o.D2);
        Assertions.assertEq(B2, o.B2);
        Assertions.assertEq(m2, o.m2);
        Assertions.assertEq(s2, o.s2);
        Assertions.assertEq(i2, o.i2);

        return true;
    }

    //@PrimaryKey
    //@DataColumn("id")
    @DataColumn
    public long lp;
    @DataColumn public double dp;
    @DataColumn public boolean bp;

    //@PrimaryKey
    //@DataColumn("id")
    @DataColumn
    public Long L;
    @DataColumn public Double D;
    @DataColumn public Boolean B;

    @DataColumn public Material m;
    @DataColumn public String s;
    @DataColumn public ItemStack i;
    @DataColumn public ZonedDateTime z;

    private long l2p;
    private double d2p;
    private boolean b2p;
    private Long L2;
    private Double D2;
    private Boolean B2;

    private Material m2;
    private String s2;
    private ItemStack i2;

    //@PrimaryKey
    //@DataColumn("id")
    @DataColumn
    public long getL2p() {
        return l2p;
    }

    public void setL2p(long l2p) {
        this.l2p = l2p;
    }

    @DataColumn
    public double getD2p() {
        return d2p;
    }

    public void setD2p(double d2p) {
        this.d2p = d2p;
    }

    @DataColumn
    public boolean getB2p() {
        return b2p;
    }

    public void setB2p(boolean b2p) {
        this.b2p = b2p;
    }

    @PrimaryKey
    @DataColumn("id")
    //@DataColumn
    public Long getL2() {
        return L2;
    }

    public void setL2(Long l2) {
        L2 = l2;
    }

    @DataColumn
    public Double getD2() {
        return D2;
    }

    public void setD2(Double d2) {
        D2 = d2;
    }

    @DataColumn
    public Boolean getB2() {
        return B2;
    }

    public void setB2(Boolean b2) {
        B2 = b2;
    }

    @DataColumn
    public Material getM2() {
        return m2;
    }

    public void setM2(Material m2) {
        this.m2 = m2;
    }

    @DataColumn
    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    @DataColumn
    public ItemStack getI2() {
        return i2;
    }

    public void setI2(ItemStack i2) {
        this.i2 = i2;
    }
}
