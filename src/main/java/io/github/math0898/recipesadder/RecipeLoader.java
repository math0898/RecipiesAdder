package io.github.math0898.recipesadder;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import static io.github.math0898.recipesadder.RecipesAdder.plugin;

/**
 * The recipe loader loads the recipes from the local yaml file. They're loaded into a list which can then be accessed.
 *
 * @author Sugaku
 */
public class RecipeLoader {

    /**
     * A counter to get the id of recipes with. Used to prevent conflicts that could arise using recipes.size().
     */
    private static final AtomicInteger nextId = new AtomicInteger(0);

    /**
     * The recipes that have been loaded by this recipe loader.
     */
    private static final List<Recipe> recipes = new ArrayList<>();

    /**
     * Accessor method for the recipes loaded by this loader.
     *
     * @return The recipes loaded.
     */
    public static List<Recipe> getRecipes () {
        return recipes;
    }

    /**
     * Using the given configuration section creates a shaped recipe.
     *
     * @param section The configuration section to create the shaped recipe from.
     * @return A shaped recipe with values pulled from the given configuration section.
     */
    public static ShapedRecipe getShapedRecipe (ConfigurationSection section) {
        if (section == null) return null;
        int amount = 1;
        Material mat = Material.valueOf(section.getString("target.material"));
        if (section.contains("target.amount")) amount = section.getInt("target.amount", 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "recipe" + nextId.getAndIncrement()), new ItemStack(mat, amount));
        recipe.shape(section.getString("pattern.line1"), section.getString("pattern.line2"), section.getString("pattern.line3"));
        ConfigurationSection items = section.getConfigurationSection("items");
        if (items == null) return null;
        for (String t : items.getKeys(false))
            recipe.setIngredient(t.toCharArray()[0], new RecipeChoice.MaterialChoice(Material.valueOf(items.getString(t))));
        return recipe;
    }

    public static ShapelessRecipe getShapelessRecipe (ConfigurationSection section) {
        // todo implement me!
        return null;
    }

    /**
     * Attempts to load Recipes from the local recipes.yml file. If the file is not present the default template will be
     * saved and loaded.
     */
    public static void load () {
        if (!new File("./plugins/RecipeLoader/recipes.yml").exists())
            plugin.saveResource("recipes.yml", false);
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load("./plugins/RecipeLoader/recipes.yml");
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Failed to load recipes.");
        }
        for (String s : config.getKeys(false)) {
            boolean shaped = true;
            if (config.contains(s + ".shaped")) shaped = config.getBoolean(s + ".shaped");
            Recipe r;
            if (shaped) r = getShapedRecipe(config.getConfigurationSection(s));
            else r = getShapelessRecipe(config.getConfigurationSection(s));
            if (r != null) recipes.add(r);
        }
    }
}
