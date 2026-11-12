package app.extendedplaylists.extension;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

/** The patched app itself: its strings and bundled assets, found by name at run time. */
final class App {
    private static final String TAG = "HiddenPlaylists";

    static Context context() throws Exception {
        return (Context) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
    }

    /** A string of the app in the user's language, or null when the app has no such string. */
    static String string(String name) {
        try {
            Context c = context();
            Resources r = c.getResources();
            // The resource package may differ from the renamed app package; a resource of the app names it.
            int id = r.getIdentifier(name, "string", r.getResourcePackageName(c.getApplicationInfo().icon));
            return id == 0 ? null : r.getString(id);
        } catch (Exception e) {
            Log.w(TAG, "no string " + name + ": " + e);
            return null;
        }
    }

    /** Name of the first asset in a folder that starts with the prefix, or null. */
    static String asset(String dir, String prefix) {
        try {
            for (String n : context().getAssets().list(dir)) if (n.startsWith(prefix)) return n;
        } catch (Exception e) {
            Log.w(TAG, "no asset " + dir + "/" + prefix + ": " + e);
        }
        return null;
    }

    private App() {}
}
