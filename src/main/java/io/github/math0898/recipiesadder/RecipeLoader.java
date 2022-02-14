package io.github.math0898.recipiesadder;

import org.bukkit.inventory.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * The recipe loader loads the recipes from the local yaml file. They're loaded into a list which can then be accessed.
 *
 * @author Sugaku
 */
public class RecipeLoader {

    /**
     * The recipes that have been loaded by this recipe loader.
     */
    List<Recipe> recipes = new ArrayList();

    /**
     * Accessor method for the recipes loaded by this loader.
     *
     * @return The recipes loaded.
     */
    public List<Recipe> getRecipes () {
        return recipes;
    }

    /**
     *
     */
}
