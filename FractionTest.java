/**
 * @author Kevin Chen
 * @version KC 202301
 * Created on 2023-01-06
 */
class Fraction {


    /**
     * @author Kevin Chen
     * <p>
     *   Exception of class "Fraction",
     *   thrown when field "b" of class "Fraction" is 0.
     * </p>
     */
    private class DenominatorIsZeroException extends ArithmeticException {
        DenominatorIsZeroException() {
            super("Denominator (b) mustn't be 0");
        }
    }


    /**
     * <p>
     *   a: type: int, the molecule of the fraction<br/>
     *   b: type: int, the denominator of the fraction (b mustn't be 0)<br/>
     * </p>
     */
    private int a, b;

    /**
     * @param a type: int, field "a" will be set to this (default 0, optional)
     * @param b type: int, field "b" will be set to this (default 1, optional)
     * @exception DenominatorIsZeroException thrown when denominator is 0
     */
    Fraction() {
        setFrac(0, 1);
    }

    Fraction(int a) {
        setFrac(a, 1);
    }

    Fraction(int a, int b) throws DenominatorIsZeroException {
        setFrac(a, b);
    }

    /**
     * @param a type: int, field "a" will be set to this
     * @param b type: int, field "b" will be set to this
     * @exception DenominatorIsZeroException thrown when denominator is 0
     */
    public void setFrac(int a, int b) throws DenominatorIsZeroException {
        if (b != 0) {
            this.a = a;
            this.b = b;
            cancel();
        } else {
            throw new DenominatorIsZeroException();
        }
    }

    /**
     * @param a type: int, field "a" will be set to this
     */
    public void setA(int a) {
        this.a = a;
        cancel();
    }

    /**
     * @param b type: int, field "b" will be set to this
     * @exception DenominatorIsZeroException thrown when denominator is 0
     */
    public void setB(int b) throws DenominatorIsZeroException {
        if (b != 0) {
            this.b = b;
            cancel();
        } else {
            throw new DenominatorIsZeroException();
        }
    }

    /**
     * @param i type: int, field "a" will be increased by it
     */
    void increaseA(int i) {
        this.a += i;
        cancel();
    }

    /**
     * @param i type: int, field "a" will be increased by it
     * @exception DenominatorIsZeroException thrown when denominator is zero
     */
    void increaseB(int i) {
        if (this.b + i != 0) {
            this.b += i;
            cancel();
        } else {
            throw new DenominatorIsZeroException();
        }
    }



    /**
     * @return type: int, field "a"
     */
    public int getA() {
        return a;
    }

    /**
     * @return type: int, field "b"
     */
    public int getB() {
        return b;
    }

    /**
     * @return type: int[2], {a, b} means (a/b)
     */
    public int[] getFrac() {
        // a/b   {a, b}
        int[] ab = {this.a, this.b};

        return ab;
    }

    /**
     * @return type: int[3], {a, b, c} means (c + a/b)
     */
    public int[] getMixedFrac() {
        // c + a/b   {a, b, c}
        int a = this.a % this.b;
        int c = this.a / this.b;
        int[] abc = {a, this.b, c};
        
        return abc;
    }

    /**
     * @return type: double, the value of the fraction (a/b)
     */
    public double getResult() {
        return (double)a / b;
    }

    /**
     * @return type: Fraction, the opposite number of the fraction (-a/b)
     */
    public Fraction getOppositeNumber() {
        a = - this.a;
        return new Fraction(a, this.b);
    }

    /**
     * @return type: Fraction, the reciproal of the fraction (b/a)
     */
    public Fraction getReciprocal() throws DenominatorIsZeroException {
        return new Fraction(this.b, this.a);
    }



    /**
     * <p>
     *   This function will be called automatically when the fraction<br/>
     *   &ensp;&ensp;&ensp;&ensp;is created or modified.
     * </p>
     */
    private void cancel() {
        int gcdAB = gcd(this.a, this.b);
        this.a /= gcdAB;
        this.b /= gcdAB;

        if (this.b < 0) {
            this.a = -this.a;
            this.b = -this.b;
        }
    }

    /**
     * <p>This function will be used by function "cancel".</p>
     * @param a type: int, a number
     * @param b type: int, another number
     * @return type: int, the greatest common divisor of the two given numbers
     */
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }



    /**
     * <p>This function will add the two given fraction.</p>
     * @param x type: Fraction, a fraction
     * @param y type: Fraction, another fraction
     * @return type: Fraction, the result
     */
    public static Fraction addFrac(Fraction x, Fraction y) {
        //   a/b + c/d = (ad+bc) / bd

        int a = x.a * y.b + x.b * y.a;
        int b = x.b * y.b;

        return new Fraction(a, b);
    }

    /**
     * <p>This function will subtract the two given fraction.</p>
     * @param x type: Fraction, a fraction
     * @param y type: Fraction, another fraction
     * @return type: Fraction, the result
     */
    public static Fraction subtractFrac(Fraction x, Fraction y) {
        //   a/b - c/d = (ad-bc) / bd

        int a = x.a * y.b - x.b * y.a;
        int b = x.b * y.b;

        return new Fraction(a, b);
    }

    /**
     * <p>This function will multiply the two given fraction.</p>
     * @param x type: Fraction, a fraction
     * @param y type: Fraction, another fraction
     * @return type: Fraction, the result
     */
    public static Fraction multiplyFrac(Fraction x, Fraction y) {
        //   (a/b) * (c/d) = (ac) / (bd)

        int a = x.a * y.a;
        int b = x.b * y.b;

        return new Fraction(a, b);
    }

    /**
     * <p>This function will devide the two given fraction.</p>
     * @param x type: Fraction, a fraction
     * @param y type: Fraction, another fraction
     * @return type: Fraction, the result
     */
    public static Fraction devideFrac(Fraction x, Fraction y) {
        //   (a/b) / (c/d) = (a/b) * (d/c) = (ad) / (bc)

        int a = x.a * y.b;
        int b = x.b * y.a;

        return new Fraction(a, b);
    }
}




public class FractionTest {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(1, 4);

        Fraction f3 = Fraction.addFrac(f1, f2);
        Fraction f4 = Fraction.subtractFrac(f1, f2);
        Fraction f5 = Fraction.multiplyFrac(f1, f2);
        Fraction f6 = Fraction.devideFrac(f1, f2);

        Fraction f7 = new Fraction(5, 2);
        int[] f7Mix = f7.getMixedFrac();

        System.out.printf("f1 = %d/%d = %f\n",
            f1.getA(), f1.getB(), f1.getResult());
            
        System.out.printf("f2 = %d/%d = %f\n\n",
            f2.getA(), f2.getB(), f2.getResult());

        System.out.printf("f3 = f1 + f2 = %d/%d = %f\n",
            f3.getA(), f3.getB(), f3.getResult());

        System.out.printf("f4 = f1 - f2 = %d/%d = %f\n",
            f4.getA(), f4.getB(), f4.getResult());

        System.out.printf("f5 = f1 * f2 = %d/%d = %f\n",
            f5.getA(), f5.getB(), f5.getResult());

        System.out.printf("f6 = f1 / f2 = %d/%d = %f\n\n",
            f6.getA(), f6.getB(), f6.getResult());

        System.out.printf("f7 = %d/%d = %d + %d/%d = %f\n",
            f7.getA(), f7.getB(),
            f7Mix[2], f7Mix[0], f7Mix[1],
            f7.getResult()
        );

        /*
        Exception in thread "main"
        Fraction$DenominatorIsZeroException:
        Denominator (b) mustn't be 0
            at Fraction.setFrac(FractionTest.java:xx)
            at Fraction.<init>(FractionTest.java:xx)
            at FractionTest.main(FractionTest.java:xx)
        */
    }
}
