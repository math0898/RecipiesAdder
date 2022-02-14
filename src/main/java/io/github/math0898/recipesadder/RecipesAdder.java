package io.github.math0898.recipesadder;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the RecipesAdder plugin. It's designed so that server owners can add and register as many item
 * recipes as they desire. Does not support exact choice.
 *
 * @author Sugaku
 */
public final class RecipesAdder extends JavaPlugin {

    /**
     * A static reference to the RecipesAdder instance being used during runtime.
     */
    public static JavaPlugin plugin = null;

    /**
     * Called on enable to load the recipes and then register them with spigot so that they can be used in game.
     */
    @Override
    public void onEnable () {
        plugin = this;
        RecipeLoader.load();
        for (Recipe r : RecipeLoader.getRecipes())
            Bukkit.addRecipe(r);
    }
}
