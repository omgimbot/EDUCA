package omgimbot.app.lsmapps.Utils;

import android.graphics.drawable.Drawable;
import android.text.Html;

import omgimbot.app.sidangapps.R;

public class ImageGetter implements Html.ImageGetter {
    @Override
    public Drawable getDrawable(String source) {
        return null;
    }

//    public Drawable getDrawable(String source) {
//        int id;
//
//        id = getResources().getIdentifier(source, "drawable", getPackageName());
//
//        if (id == 0) {
//            // the drawable resource wasn't found in our package, maybe it is a stock android drawable?
//            id = getResources().getIdentifier(source, "drawable", "android");
//        }
//
//        if (id == 0) {
//            // prevent a crash if the resource still can't be found
//            return null;
//        }
//        else {
//            Drawable d = getResources().getDrawable(id);
//            d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
//            return d;
//        }
//    }
}