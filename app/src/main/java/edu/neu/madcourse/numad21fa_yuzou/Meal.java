package edu.neu.madcourse.numad21fa_yuzou;

public class Meal {
    private final String id;
    private final String meal;
    private final String category;
    private final String area;
    private final String instruction;
    private final String imagepath;


    /**
     *  Constructor.
     * @param id            the id of the meal in database.
     * @param meal          the name of the meal.
     * @param category      the category of the meal.
     * @param area          the area of the meal cuisine.
     * @param instruction   the instruction of how to cook this meal.
     */
    public Meal(String id, String meal, String category, String area, String instruction, String path) {
        this.id = id;
        this.meal = meal;
        this.category = "Category: " + category;
        this.area = "Cuisine: " + area;
        this.instruction = instruction;
        this.imagepath = path;
    }

    public String getId(){
        return this.id;
    }

    public String getMeal(){
        return this.meal;
    }

    public String getCategory(){
        return this.category;
    }

    public String getArea(){
        return this.area;
    }

    public String getInstruction(){
        return this.instruction;
    }

    public String getImagepath() { return this.imagepath; }

}
