package org.ispirer.models;

import org.ispirer.exceptions.DivideByZeroComplexNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumber {
    double real;
    double imaginary;
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public ComplexNumber add(ComplexNumber complexNumber){
        double real = this.real + complexNumber.real;
        double imaginary = this.imaginary + complexNumber.imaginary;
        return new ComplexNumber(real,imaginary);
    }
    public ComplexNumber subtract(ComplexNumber complexNumber){
        double real = this.real - complexNumber.real;
        double imaginary = this.imaginary - complexNumber.imaginary;
        return new ComplexNumber(real,imaginary);
    }
    public ComplexNumber multiply(ComplexNumber complexNumber){
        double real = (this.real * complexNumber.real - this.imaginary * complexNumber.imaginary);
        double imaginary = (this.real * complexNumber.imaginary + this.imaginary * complexNumber.real);
        return new ComplexNumber(real,imaginary);
    }
    public ComplexNumber divide(ComplexNumber complexNumber) throws DivideByZeroComplexNumberException {
        double sameDenominator = Math.pow(complexNumber.real, 2) + Math.pow(complexNumber.imaginary, 2);

        if(sameDenominator == 0) throw new DivideByZeroComplexNumberException("Division by zero");

        double realNumerator = (this.real * complexNumber.real) + (this.imaginary * complexNumber.imaginary);
        double imaginaryNumerator = (this.imaginary * complexNumber.real) - (this.real * complexNumber.imaginary);

        double real = realNumerator / sameDenominator;
        double imaginary = imaginaryNumerator / sameDenominator;
        return new ComplexNumber(real,imaginary);
    }
    @Override
    public String toString() {
        return this.imaginary >= 0 ? real + " + " + imaginary + "i" : real + " - " + (-imaginary) + "i";
    }

    public static ComplexNumber parseComplexNumber(String complexString) {
        complexString = complexString.replaceAll("\\s+", "");

        Pattern pattern = Pattern.compile("([-+]?[0-9]*\\.?[0-9]+)([-+])([-+]?[0-9]*\\.?[0-9]+)i");
        Matcher matcher = pattern.matcher(complexString);

        if (matcher.find()) {
            double realPart = Double.parseDouble(matcher.group(1));
            double imagPart = Double.parseDouble(matcher.group(3));

            if (matcher.group(2).equals("-")) {
                imagPart = -imagPart;
            }
            return new ComplexNumber(realPart, imagPart);
        } else {
            throw new IllegalArgumentException("Invalid complex number: " + complexString);
        }
    }
}
