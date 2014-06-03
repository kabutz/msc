package safe;

import sun.misc.*;

import java.lang.reflect.*;

public class UnsafeProvider {
    private static final Unsafe theUnsafe;

    static {
        Unsafe temp = null;
        try {
            temp = Unsafe.getUnsafe(); // first try if the class is in the boot class path
        } catch (SecurityException ex) {
            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                temp = (Unsafe) f.get(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        theUnsafe = temp;
    }

    public static Unsafe getUnsafe() {
        try {
            if (theUnsafe == null) {
            }
            return theUnsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}