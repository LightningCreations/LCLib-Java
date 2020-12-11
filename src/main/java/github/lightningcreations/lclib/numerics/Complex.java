package github.lightningcreations.lclib.numerics;

import github.lightningcreations.lclib.annotation.Numeric;
import github.lightningcreations.lclib.annotation.ValueBased;

import java.lang.constant.*;
import java.util.Optional;

/**
 * Class which represents an immutable, Complex Number.
 *
 * @author connor
 *
 */
@Numeric(double.class)
@ValueBased
public final class Complex implements Constable {

    @Override
    public Optional<? extends ConstantDesc> describeConstable() {
        return Optional.of(ComplexDesc.valueOf("describeConstantable",this.rr,this.ii));
    }

    public static final class ComplexDesc extends DynamicConstantDesc<Complex>{

        public static ComplexDesc valueOf(String constantName,double r){
            return new ComplexDesc(
                MethodHandleDesc.of(DirectMethodHandleDesc.Kind.STATIC,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),"valueOf","(D)Lgithub/lightningcreations/lclib/numerics/Complex;"),
                constantName,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),
                r
            );
        }

        public static ComplexDesc valueOf(String constantName,double r,double i){
            return new ComplexDesc(
                MethodHandleDesc.of(DirectMethodHandleDesc.Kind.STATIC,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),"valueOf","(DD)Lgithub/lightningcreations/lclib/numerics/Complex;"),
                constantName,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),
                r,i
            );
        }

        public static ComplexDesc valueOfImaginary(String constantName,double r){
            return new ComplexDesc(
                MethodHandleDesc.of(DirectMethodHandleDesc.Kind.STATIC,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),"valueOfImaginary","(D)Lgithub/lightningcreations/lclib/numerics/Complex;"),
                constantName,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),
                r
            );
        }

        public static ComplexDesc fromPolar(String constantName,double mag,double arg){
            return new ComplexDesc(
                MethodHandleDesc.of(DirectMethodHandleDesc.Kind.STATIC,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),"fromPolar","(DD)Lgithub/lightningcreations/lclib/numerics/Complex;"),
                constantName,ClassDesc.ofDescriptor("github/lightningcreations/lclib/numerics/Complex"),
                mag,arg
            );
        }

        protected ComplexDesc(DirectMethodHandleDesc bootstrapMethod, String constantName, ClassDesc constantType, ConstantDesc... bootstrapArgs) {
            super(bootstrapMethod, constantName, constantType, bootstrapArgs);
        }
    }

	private final double rr, ii;
	/**
	 * Contructs a Complex Number.
	 * Neither r nor i may be NaN.
	 * The behavior is undefined if either r or i is NaN
	 * @param r The real component of the Complex Number
	 * @param i The imaginary component of the complex number
	 */
	private Complex(double r,double i) {
		assert r==r&&i==i;//Neither r nor i may be NaN
		rr = r;
		ii = i;
	}
	/*
	 * The imaginary constant.
	 * Equal to Complex.sqrt(-1)
	 */
	public static final Complex i = new Complex(0,1);

	/**
	 * Constructs a Complex number from a real an imaginary component
	 * @param real the real component
	 * @param img the imaginary component
	 * @return r+i(img)
	 */
	public static Complex valueOf(double real,double img) {
		return new Complex(real,img);
	}
	/**
	 * Constructs a pure real Complex Number
	 * @param r the value
	 * @return r as a complex number
	 */
	public static Complex valueOf(double r) {
		return new Complex(r,0);
	}
	/**
	 * Constructs a pure imaginary Number
	 * @param v the imaginary component
	 * @return vi
	 */
	public static Complex valueOfImaginary(double v) {
		return new Complex(0,v);
	}
	/**
	 * Computes the Complex Number from Polar Co-ordinates.
	 * @param m the magnetude
	 * @param t the angle of the polar co-ordinate, in radians
	 */
	public static Complex fromPolar(double m,double t) {
		return expi(t).multiply(m);
	}
	/**
	 * Multiplies 2 complex numbers in the form (a+bi) and (c+di)
	 * @param k {@literal c+di}
	 * @return ac-bd+(ad+bc)i
	 */
	public Complex multiply(Complex k) {
		return new Complex(rr*k.rr+-(ii*k.ii),rr*k.ii+ii*k.rr);
	}

	/**
	 * Scales this complex number by a Real Number Multiple
	 * @param d
	 * @return da+dbi
	 */
	public Complex multiply(double d) {
		return new Complex(rr*d,ii*d);
	}

	/**
	 * Divides this complex number by a real number
	 * @param d the real to divide this by
	 * @return (a/d)+(b/d)i
	 */
	public Complex divide(double d) {
		return new Complex(rr/d,ii/d);
	}
	/**
	 * Computes the complex exponential of the given complex number.
	 * @param pow a+bi
	 * @return e^(a+bi)
	 * @see Complex#expi(double)
	 */
	public static Complex exp(Complex pow) {
		double m = Math.exp(pow.rr);
		return new Complex(m*Math.cos(pow.ii),m*Math.sin(pow.ii));
	}
	/**
	 * Computes the exponential of a pure imaginary number.
	 * e^iθ is defined as cosθ+isinθ by Eular's Formula
	 * @param d a pure imaginary number, devided by i.
	 * @return e^id or equivalently, cos(d)+isin(d)
	 */
	public static Complex expi(double d) {
		return new Complex(Math.cos(d),Math.sin(d));
	}

	/*
	 * Computes the natural logarithm of this number.
	 * The natural logaritm of a+bi is c+di, where c is the natural logarithm of |a+bi| and d is the phase angle of a+bi.
	 */
	public Complex log() {
		return valueOf(Math.log(getMagnetude()),getArgument());
	}

	/**
	 * Computes the complex square root of the real number d.
	 * @param d a real number, which may be negative
	 * @return sqrt(d) in the domain of Complex Numbers
	 */
	public static Complex sqrt(double d) {
		if(d>=0)
			return valueOf(Math.sqrt(d));
		else
			return valueOfImaginary(Math.sqrt(-d));
	}
	/**
	 * Adds 2 complex numbers together.
	 * @param c a complex number
	 * @return this+c
	 */
	public Complex add(Complex c) {
		return new Complex(rr+c.rr,ii+c.ii);
	}
	/**
	 * Adds a real number to a complex number
	 * @param d a real number
	 * @return this+d
	 */
	public Complex add(double d) {
		return new Complex(rr+d,ii);
	}

	public Complex sub(Complex c) {
		return new Complex(rr-c.rr,ii-c.ii);
	}
	public Complex sub(double d) {
		return new Complex(rr-d,ii);
	}

	/**
	 * Gets the real component of the complex number.
	 * @return a, if a+bi equals this number
	 */
	public double getReal() {
		return rr;
	}
	/**
	 * Returns the imaginary component of the complex number.
	 * @return b, if a+bi equals this number.
	 */
	public double getImaginary() {
		return ii;
	}

	/**
	 * Computes the phase angle of this complex number
	 */
	public double getArgument() {
		return Math.atan2(rr, ii);
	}
	/**
	 * Computes the magnetude of this complex number
	 */
	public double getMagnetude() {
		return Math.sqrt(rr*rr+ii*ii);
	}

	public Complex pow(double n) {
		return exp(log().multiply(n));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToRawLongBits(ii);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToRawLongBits(rr);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		if (ii != other.ii)
			return false;
        return rr == other.rr;
    }



}
