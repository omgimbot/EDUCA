//package omgimbot.app.lsmapps.Utils;
//
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.text.Spannable;
//import android.text.style.ImageSpan;
//import android.util.DisplayMetrics;
//
//import java.io.File;
//
//public class ImageLoader extends AsyncTask {
//
//
//
//        DisplayMetrics metrics = new DisplayMetrics();
//
//        @Override
//        protected void onPreExecute() {
//
//            // we need this to properly scale the images later
//            getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//
//            // iterate over all images found in the html
//            for (ImageSpan img : htmlSpannable.getSpans(0,
//                    htmlSpannable.length(), ImageSpan.class)) {
//
//                if (!getImageFile(img).isFile()) {
//
//                    // here you have to download the file
//
//                }
//
//                // we use publishProgress to run some code on the
//                // UI thread to actually show the image
//                // -> onProgressUpdate()
//                publishProgress(img);
//
//            }
//
//            return null;
//
//        }
//
//        @Override
//        protected void onProgressUpdate(ImageSpan... values) {
//
//            // save ImageSpan to a local variable just for convenience
//            ImageSpan img = values[0];
//
//            // now we get the File object again. so remeber to always return
//            // the same file for the same ImageSpan object
//            File cache = getImageFile(img);
//
//            // if the file exists, show it
//            if (cache.isFile()) {
//
//                // first we need to get a Drawable object
//                Drawable d = new BitmapDrawable(getResources(),
//                        cache.getAbsolutePath());
//
//                // next we do some scaling
//                int width, height;
//                int originalWidthScaled = (int) (d.getIntrinsicWidth() * metrics.density);
//                int originalHeightScaled = (int) (d.getIntrinsicHeight() * metrics.density);
//                if (originalWidthScaled > metrics.widthPixels) {
//                    height = d.getIntrinsicHeight() * metrics.widthPixels
//                            / d.getIntrinsicWidth();
//                    width = metrics.widthPixels;
//                } else {
//                    height = originalHeightScaled;
//                    width = originalWidthScaled;
//                }
//
//                // it's important to call setBounds otherwise the image will
//                // have a size of 0px * 0px and won't show at all
//                d.setBounds(0, 0, width, height);
//
//                // now we create a new ImageSpan
//                ImageSpan newImg = new ImageSpan(d, img.getSource());
//
//                // find the position of the old ImageSpan
//                int start = htmlSpannable.getSpanStart(img);
//                int end = htmlSpannable.getSpanEnd(img);
//
//                // remove the old ImageSpan
//                htmlSpannable.removeSpan(img);
//
//                // add the new ImageSpan
//                htmlSpannable.setSpan(newImg, start, end,
//                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                // finally we have to update the TextView with our
//                // updates Spannable to display the image
//                htmlTextView.setText(htmlSpannable);
//            }
//        }
//
//        private File getImageFile(ImageSpan img) {
//
//            // you need to implement this method yourself.
//            // it must return a unique File object (or something
//            // different if you also change the rest of the code)
//            // for every image tag. use img.getSource() to get
//            // the src="" attribute. you might want to use some
//            // hash of the url as file name
//
//        }
//
//
//}
