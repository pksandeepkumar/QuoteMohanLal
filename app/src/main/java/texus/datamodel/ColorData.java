package texus.datamodel;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by sandeep on 18/2/16.
 */
public class ColorData {

//    <color name="brown">#795548</color>
//    <color name="blue_ray">#607D8B</color>
//    <color name="teal">#009688</color>
//    <color name="indigo">#3F51B5</color>
//    <color name="deep_purple">#673AB7</color>
//    <color name="lime">#827717</color>
//
    public static final int COLOR_BROWN = Color.parseColor("#795548");
    public static final int COLOR_BLUE_RAY = Color.parseColor("#607D8B");
    public static final int COLOR_TEAL = Color.parseColor("#009688");
    public static final int COLOR_INDIGO = Color.parseColor("#3F51B5");
    public static final int COLOR_DEEP_PURPLE = Color.parseColor("#673AB7");
    public static final int COLOR_LIME = Color.parseColor("#827717");


    public static final int []colors = {COLOR_BROWN , COLOR_BLUE_RAY, COLOR_TEAL,
                                        COLOR_INDIGO, COLOR_DEEP_PURPLE, COLOR_LIME};

    public static int getAColor(){
        Random random = new Random(colors.length);
        return  colors[random.nextInt()];
    }
}
