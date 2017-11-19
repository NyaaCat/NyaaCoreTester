package cat.nyaa.nyaacoretester;

import cat.nyaa.nyaacoretester.database.DatabaseTest;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Method;

public class NyaaCoreTester extends JavaPlugin {
    public static NyaaCoreTester instance;
    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        saveConfig();
        File dbf = new File(getDataFolder(),"testdb.db");
        if (dbf.exists()) dbf.delete();
        instance = this;
        if (Boolean.parseBoolean(System.getProperty("nyaacore.tester.enabled", "false"))) {
            int wait_time = 1;
            getLogger().info(String.format("Testing start in %d seconds", wait_time));
            getServer().getScheduler().runTaskLater(
                    this,
                    this::test,
                    wait_time * 20L
            );
        } else {
            getLogger().info("NyaaCoreTester not enabled.");
            getLogger().info("Please use -Dnyaacore.tester.enabled=true");
        }
    }

    private void test() {
        getLogger().info("\n\n\n\n\n\n\n\n\n\n\n\n");
        getLogger().info("NyaaCore Testing ...");
        try {
            // List your test classes here
            runTestOn(new ItemStackUtilsTest());
            runTestOn(new DatabaseTest());

            getLogger().info("####################");
            getLogger().info("# ALL TESTS PASSED #");
            getLogger().info("####################");
        } catch (Exception ex) {
            ex.printStackTrace();

            getLogger().info("!!!!!!!!!!!!!!!");
            getLogger().info("! TEST FAILED !");
            getLogger().info("!!!!!!!!!!!!!!!");
        }
        getServer().shutdown();
    }

    public void runTestOn(Object classInstance) throws ReflectiveOperationException {
        for (Method m : classInstance.getClass().getMethods()) {
            if (m.getAnnotation(Test.class) != null) {
                m.invoke(classInstance);
                getLogger().info(m.getName()+"() passed.");
            }
        }
    }
}
