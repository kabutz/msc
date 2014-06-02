package safe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class UnsafeProvider {
	static Unsafe theUnsafe = null;

    public static Unsafe getUnsafe() {
        try {
        	if (theUnsafe == null) {
				Field f = Unsafe.class.getDeclaredField("theUnsafe");
				f.setAccessible(true);
				theUnsafe = (Unsafe)f.get(null);
			}
			return theUnsafe;
        } catch (Exception e) {}
        return null;
    }
}