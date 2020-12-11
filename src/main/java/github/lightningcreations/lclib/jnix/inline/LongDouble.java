package github.lightningcreations.lclib.jnix.inline;

import java.io.NotSerializableException;
import java.io.Serial;

import github.lightningcreations.lclib.jnix.annotations.PackedType;
import github.lightningcreations.lclib.jnix.annotations.PotentiallyUndefined;
import github.lightningcreations.lclib.jnix.annotations.Type;
import github.lightningcreations.lclib.jnix.annotations.TypeMapsToPrimitive;
import github.lightningcreations.lclib.jnix.annotations.UseObjectRepresentationWherePossible;

@Type(name="long double")
@PackedType
@TypeMapsToPrimitive
@UseObjectRepresentationWherePossible
public final class LongDouble extends Number implements Comparable<LongDouble> {

	static {
		registerNatives();
	}

	private static native void registerNatives();

	@Serial
    private static final long serialVersionUID = -1329019263573057455L;
	private final byte[] allocSpace;
	private static final int sizeof = sizeof();

	public static final LongDouble ZERO = new LongDouble(0.0);
	public static final LongDouble ONE = new LongDouble(1.0);
	public static final LongDouble MONE = new LongDouble(-1.0);
	public static final LongDouble Infinity = new LongDouble(Double.POSITIVE_INFINITY);
	public static final LongDouble NegativeInfinity = new LongDouble(Double.NEGATIVE_INFINITY);
	public static final LongDouble NaN = new LongDouble(Double.NaN);

	private LongDouble() {
		allocSpace = new byte[sizeof];
		init(allocSpace);
	}

	private static native void init(byte[] targetSpace);
	private static native void init(byte[] targetSpace,double val);
	private static native void copy(byte[] targetSpace,byte[] srcSpace);
	private static native int hash(byte[] targetSpace);
	private static native boolean lessCmp(byte[] a,byte[] b);
	private static native boolean eqCmp(byte[] a,byte[] b);
	private static native int sizeof();

	public LongDouble(double value) {
		allocSpace = new byte[sizeof];
		init(allocSpace,value);
	}
	@Override
	public native double doubleValue();
	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return (float)doubleValue();
	}
	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return (int)doubleValue();
	}
	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return (long)doubleValue();
	}

	public native String toString();
	public LongDouble clone() {
		LongDouble nldouble = new LongDouble();
		copy(nldouble.allocSpace,this.allocSpace);
		return nldouble;
	}

	public native static LongDouble add(LongDouble a,LongDouble b);
	public native static LongDouble subtract(LongDouble a,LongDouble b);
	public native static LongDouble negate(LongDouble a);
	public native static LongDouble multiply(LongDouble a,LongDouble b);
	@PotentiallyUndefined
	public native static LongDouble divide(LongDouble a,LongDouble b);
	public native static boolean isNaN(LongDouble a);
	public native static boolean isInfinite(LongDouble a);
	public native static boolean isFinite(LongDouble a);
	@Override
	public int compareTo(LongDouble arg0) {
		if(lessCmp(allocSpace,arg0.allocSpace))
			return -1;
		else if(lessCmp(arg0.allocSpace,allocSpace))
			return 1;
		else
			return 0;
	}

	public int hashCode() {
		return hash(allocSpace);
	}

	public boolean equals(Object o) {
		if(o==null)
			return false;
		else if(o==this)
			return true;
		else if(!(o instanceof LongDouble))
			return false;
		else
			return eqCmp(allocSpace,((LongDouble)o).allocSpace);
	}

	@Serial
    private Object writeReplace() throws NotSerializableException {
		throw new NotSerializableException();
	}
	@Serial
    private Object readResolve() throws NotSerializableException{
		throw new NotSerializableException();
	}

}
