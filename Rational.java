public class Rational {
    private int nominator;
    private int denominator;

    public Rational(int nominator, int denominator) {
        this.nominator = nominator / gcd(nominator, denominator);
        this.denominator = denominator / gcd(nominator, denominator);
    }

    public int getNominator() {
        return this.nominator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public Rational add(Rational r1, Rational r2) {
        return new Rational(r1.getNominator() * r2.getDenominator() + r1.getDenominator() * r2.getNominator(), r1.getDenominator() * r2.getDenominator());
    }
    
    public Rational add(Rational other) {
        return new Rational(this.getNominator() * other.getDenominator() + this.getDenominator() * other.getNominator(), this.getDenominator() * other.getDenominator());
    }
    
    public Rational substract(Rational r1, Rational r2) {
        return new Rational(r1.getNominator() * r2.getDenominator() - r1.getDenominator() * r2.getNominator(), r1.getDenominator() * r2.getDenominator());
    }
    
    public Rational substract(Rational other) {
        return new Rational(this.getNominator() * other.getDenominator() - this.getDenominator() * other.getNominator(), this.getDenominator() * other.getDenominator());
    }

    public Rational multiply(Rational r1, Rational r2) {
        return new Rational(r1.getNominator() * r2.getNominator(), r1.getDenominator() * r2.getDenominator());
    }

    public Rational multiply(Rational other) {
        return new Rational(this.getNominator() * other.getNominator(), this.getDenominator() * other.getDenominator());
    }

    public Rational divide(Rational r1, Rational r2) {
        return new Rational(r1.getNominator() * r2.getDenominator(), r1.getDenominator() * r2.getNominator());
    }
    
    public Rational divide(Rational other) {
        return new Rational(this.getNominator() * other.getDenominator(), this.getDenominator() * other.getNominator());
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }

    public static void test() {
        Rational r1 = new Rational(5, 8);
        Rational r2 = new Rational(7, 16);
        System.out.printf("%s + %s = %s\n", r1, r2, r1.add(r2));
        System.out.printf("%s - %s = %s\n", r1, r2, r1.substract(r2));
        System.out.printf("%s * %s = %s\n",r1, r2, r1.multiply(r2));
        System.out.printf("%s / %s = %s\n",r1, r2, r1.divide(r2));
    }

    @Override
    public String toString() {
        return this.getNominator() + "/" + this.getDenominator();
    }
}

class Test {
    public static void main(String[] args) {
        Rational.test();
    }
}
