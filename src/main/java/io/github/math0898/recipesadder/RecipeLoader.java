package io.github.math0898.recipesadder;

import org.bukkit.inventory.Recipe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.github.math0898.recipesadder.RecipesAdder.plugin;

/**
 * The recipe loader loads the recipes from the local yaml file. They're loaded into a list which can then be accessed.
 *
 * @author Sugaku
 */
public class RecipeLoader {

    /**
     * The recipes that have been loaded by this recipe loader.
     */
    private static final List<Recipe> recipes = new ArrayList();

    /**
     * Accessor method for the recipes loaded by this loader.
     *
     * @return The recipes loaded.
     */
    public static List<Recipe> getRecipes () {
        return recipes;
    }

    /**
     * Attempts to load Recipes from the local recipes.yml file. If the file is not present the default template will be
     * saved and loaded.
     */
    public static void load () {
        if (!new File("./plugins/RecipeLoader/recipes.yml").exists()) plugin.saveResource("recipes.yml", false);
    }
}
