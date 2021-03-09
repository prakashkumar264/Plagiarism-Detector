#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

class Fraction; // need to declare this before declaring removeLeadZeros

// delcare functions not friends or members of the Fraction or Polynom classes
void reduce(long int& num, long int& denom);
void removeLeadZeros(vector<Fraction>&);

/**

    This program contains the Fraction and Polynom classes' definitions and implementations.

    The Fraction class options:
        Negate, add, subtract, multiply, and divide fractions with overloaded operators
        Retrieve the numerator and denominator with accessors
        Print the current state of the fraction

    The Polynom class options:
        Negate, add, subtract, and multiply polynomials with overloaded operators
        Evaluate the polynomial at a specified value (fraction or double) with overloaded function-call operator
        Add a fraction to a polynomial, and multiply a polynomial by a fraction with overloaded operators
        Retrieve the coefficient at a specific index, and get the degree of the polynomial with accessors
        Print the current state of the polynomial

    For my implementation, note the following:
        - note: here, the null polynomial is defined as being a polynomial of degree 0, where the only term is
           a 0 as a coefficient to the x^0 term.
        - the polynomial is evaluated with the Horner algorithm as specified


    Ellen Arteca
    0297932

*/


// ********************************************* FRACTION *******************************************************************


/**
    Fraction class definition (declare constructors, accessors, mutators, friend functions,
    and member variables).

    Each function is described when implemented.
*/
class Fraction {
    public:
        // constructors
        Fraction(long int num1, long int denom1);
        Fraction(int num1) : num(num1), denom(1) {} // allows for automatic conversion int to Fraction
        Fraction() : num(0), denom(1) {} // default constructor (value of 0)
        // accessors
        long int getNum() const { return num; }
        long int getDenom() const { return denom; }
        void print() const;

        // overloaded operators as member functions
        const Fraction operator -() const;
        operator double() const { return static_cast<double>(num)/denom; } // automatic conversion Fraction to double
        void operator +=(const Fraction&);
        void operator *=(const Fraction&);

        // overloaded operators as friend functions
        friend const Fraction operator +(const Fraction&, const Fraction&);
        friend const Fraction operator -(const Fraction&, const Fraction&);
        friend const Fraction operator *(const Fraction&, const Fraction&);
        friend const Fraction operator /(const Fraction&, const Fraction&);

    private:
        // member variables
        long int num, denom;
};

// ------------------------------------------------------------------------------------------------- FRACTION CONSTRUCTOR

/**
    Fully-argumented constructor allowing to specify the numerator and denominator of the fraction.

    Note that if 0 is specified as the denominator, an error message is printed and the program exits.
    Note also that the denominator is always positive.  If the denominator specified is negative, the
    numerator specified is multiplied by -1.  This also simplifies fractions where both the numerator
    and denominator are specified as negative to their positive equivalents.

    Fractions are also reduced to lowest terms.
*/
Fraction::Fraction(long int num1, long int denom1) : num(num1) {
    if (denom1 == 0) {
        cout << "Error!! Can't divide by 0\n";
        exit(1);
    }

    // denominator is always positive
    denom = abs(denom1);

    if (denom1 < 0) {
        num = -num;
    }

    reduce(num, denom); // lowest terms
}

// ------------------------------------------------------------------------------------------------ FRACTION MEMBER FUNCTIONS

// -------------------------------------------------------------------------------------- NON-OPERATOR ACCESSOR


// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS

/**
    Overloading the negation (unary minus) operator for fractions.
    Returns a fraction with the same values, where the numerator is the negative of the original.
*/
const Fraction Fraction::operator -() const {
    return Fraction(-num, denom);
}

/**
    Function to print the current state of a fraction object.
    Output formatted in standard fraction format numerator/denominator.
    Note that if the denominator is 1, then /denominator is omitted and the fraction is displayed
    as an integer.
*/
void Fraction::print() const {
     if (denom == 1)
        cout << num;
     else
        cout << num << "/" << denom;
}

/**
    Overloading the += operator for fractions.
    Note that this is a mutator function.

    Adds the specified fraction to the current instantiation of the fraction class.
*/
void Fraction::operator +=(const Fraction& f) {
    // adding fractions: find a common denominator
    long int newDenom = denom * f.denom;
    long int newNum = num * f.denom + f.num * denom;

    reduce(newNum, newDenom); // reduce to lowest terms

    // update the current fraction
    num = newNum;
    denom = newDenom;
}


// ------------------------------------------------------------------------------------------------ FRACTION FRIEND FUNCTIONS

// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS

/**
    Overloading addition operator for fractions.
    Note that this does not change the fractions involved in the addition.

    Returns the result of the summation of the 2 specified fractions.
*/
const Fraction operator +(const Fraction& f1, const Fraction& f2) {
    Fraction sum(f1.num, f1.denom);
    sum += f2; // fraction reduced to lowest terms in this operation

    return sum;
}

/**
    Overloading the *= operator for fractions.
    Note that this is a mutator function.

    Multiplies the current instantiation of the fraction class by the specified fraction.
*/
void Fraction::operator *=(const Fraction& f) {
    // multiplying fractions: multiply the numerators and denominators respectively
    long int newNum = num * f.num;
    long int newDenom = denom * f.denom;

    reduce(newNum, newDenom); // reduce to lowest terms

    // update the current fraction
    num = newNum;
    denom = newDenom;
}


/**
    Overloading multiplication operator for fractions.
    Note that this does not change the fractions involved in the multiplication.

    Returns the product of the 2 specified fractions.
*/
const Fraction operator *(const Fraction& f1, const Fraction& f2) {
    Fraction prod(f1.num, f1.denom);
    prod *= f2; // fraction reduced to lowest terms in this operation

    return prod;
}



/**
    Overloading subtraction operator for fractions.
    Note that this does not change the fractions involved in the subtraction.

    Returns the result of the subtraction of the 2 specified fractions.
*/
const Fraction operator -(const Fraction& f1, const Fraction& f2) {
    Fraction negF2 = -f2;

    return (f1 + negF2); // fraction reduced to lowest terms in this operation
}


/**
    Overloading division operator for fractions.
    Note that this does not change the fractions involved in the division.

    Returns the quotient of the 2 specified fractions.
*/
const Fraction operator /(const Fraction& f1, const Fraction& f2) {
    // division of f1/f2 is the same as multiplication of f1 by the reciprocal of f2
    long int newNum = f1.num * f2.denom;
    long int newDenom = f1.denom * f2.num;

    // fraction reduced to lowest terms in this operation (also, checked for denom == 0)
    return Fraction(newNum, newDenom);

}

// --------------------------------------------------------------------------------- FRACTION NON-FRIEND NON-MEMBER FUNCTIONS

/**
    Function to reduce a given numerator and denominator to lowest terms.
    Since the numerator and denominator are passed by reference, they are changed in the function although
    there is no return value.
*/
void reduce(long int& newNum, long int& newDenom) {
    // find the minimum of the 2 values passed in (greatest common divisor can't be greater than the min value)
    long int minVal = newDenom;
    if (minVal < newDenom)
        minVal = newNum;

    int divisor = 1;
    for (int i = minVal; i > 0; -- i) {
        if (abs(newDenom) % i == 0 && abs(newNum) % i == 0) { // find the greatest common divisor
            divisor = i;
            break;
        }
    }

    // divide both numerator and denominator by the GCD - now the fraction these values represent is in lowest terms
    newDenom /= divisor;
    newNum /= divisor;
}


// ********************************************* POLYNOM *******************************************************************


/**
    Polynom class definition (declare constructors, accessors, mutators, friend functions,
    and member variables).

    Each function is described when implemented.
*/
class Polynom {
    public:
        // constructors
        Polynom();
        Polynom(const vector<Fraction>& v);
        // accessors
        int getDegree() const { return coeff.size() - 1; } // degree is the highest exponent in the polynomial
        const Fraction getCoeff(int index) const;
        void print() const;

        // overloaded operators as member functions
        const Polynom operator -() const;
        void operator +=(const Polynom&);
        double operator ()(double) const;
        const Fraction operator ()(const Fraction&) const;

        // overloaded operators as friend functions
        friend const Polynom operator +(const Polynom&, const Fraction&);
        friend const Polynom operator *(const Fraction&, const Polynom&);
        friend const Polynom operator +(const Polynom&, const Polynom&);
        friend const Polynom operator -(const Polynom&, const Polynom&);
        friend const Polynom operator *(const Polynom&, const Polynom&);

    private:
        // member variables
        vector<Fraction> coeff;

};

// ------------------------------------------------------------------------------------------------- POLYNOM CONSTRUCTORS

/**
    Argumented constructor for the Polynom class, specifying a vector of fraction coefficients.  This also
    specifies the degree of the polynomial.
    Note that any leading zeroes are removed from the vector of coefficients.  These do not contribute to
    the degree of the polynomial.
*/
Polynom::Polynom(const vector<Fraction>& v) {
    // copy over the vector of coefficients specified to the datafield of the polynom object
    coeff.resize(v.size());
    for (int i = 0; i < v.size(); ++ i)
        coeff[i] = v[i];
    removeLeadZeros(coeff);
}


/**
    Default constructor for the Polynom class, resulting in the null polynomial.
    Note: here, the null polynomial is defined as being a polynomial of degree 0, where the only term is
    a 0 as a coefficient to the x^0 term.
*/
Polynom::Polynom() {
    coeff.resize(1); // only one coefficient, polynomial of degree 0
    coeff[0] = 0;
}


// ------------------------------------------------------------------------------------------------ POLYNOM MEMBER FUNCTIONS

// -------------------------------------------------------------------------------------- NON-OPERATOR ACCESSORS

/**
    Function to access the coefficient of a specific power (for example, getCoeff(2) gets the
    coefficient for x^2).

    Note that if an invalid index is specified (i.e. a power not included in the polynomial, and
    therefore out of the valid range of the coefficients vector) an error message is printed and
    the program exits.
*/
const Fraction Polynom::getCoeff(int index) const {
    // check for invalid input
    if (index < 0 || index >= coeff.size()) {
        cout << "Error!!  The index specified is undefined.";
        exit(1);
    }

    return coeff[index];
}

/**
    Function to print the current state of a polynomial object.
    Output is formatted as follows (for an n-th degree polynomial):
        (coefficient) x^0 + (coefficient) x^1 + ... + (coefficient) x^n
*/
void Polynom::print() const {
    for (int i = 0; i < coeff.size(); ++ i) {
        if (i > 0 )
            cout << " + ";
        cout << "(";
        coeff[i].print(); // print coefficient
        cout << ") x^" << i << " ";
    }
    cout << "\n";
}

// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS


/**
    Overloading the += operator for polynomials.
    Note that this is a mutator function.

    Adds the specified polynomial to the current instantiation of the polynom class.
*/
void Polynom::operator +=(const Polynom& p) {
    // find the polynomial with the highest degree
    vector<Fraction> maxPoly = coeff;
    vector<Fraction> minPoly = p.coeff;

    if (minPoly.size() > maxPoly.size()) {
        maxPoly = p.coeff;
        minPoly = coeff;
    }

    // add the coefficients of the polynomial with the min degree to the coefficients
    // from the polynomial with the max degree
    for (int i = 0; i < minPoly.size(); ++ i) {
        maxPoly[i] += minPoly[i];
    }

    // update the current polynomial
    coeff = maxPoly;
    removeLeadZeros(coeff);
}

/**
    Overloading the negation (unary minus) operator for polynomials.
    Returns a polynomial with the same coefficient absolute values, where the each coefficient
    is the negative of the original.
*/
const Polynom Polynom::operator -() const {
    Polynom negated(coeff); // new polynomial with the same coefficients as the original
    for (int i = 0; i < coeff.size(); ++ i)
        negated.coeff[i] = -coeff[i]; // negate each of the coefficients
    return negated;
}

/**
    Overloading the () operator for polynomials evalutated with doubles.
    Returns the double value of the polynomial evaluated with the double value it
    was called with.
    Evaluation is performed with the Horner algorithm.

    Note: the automatic conversions from Fraction to double and int to Fraction caused
    this part of the code to not compile since it was an "ambiguous" addition (of the
    double result + Fraction coeff[i]).  This is because the addition could be of type
    double + double, or Fraction + Fraction (double automatically converts to int).
    This is why the static_cast<double> is used.
*/
double Polynom::operator ()(double x) const {
    double result = 0;
    for (int i = coeff.size() - 1; i > 0; -- i) {
        result = x * (result + static_cast<double>(coeff[i]));
    }
    return result + static_cast<double>(coeff[0]);
}

/**
    Overloading the () operator for polynomials evalutated with fractions.
    Returns the fraction value of the polynomial evaluated with the fraction value it
    was called with.
    Evaluation is performed with the Horner algorithm.
*/
const Fraction Polynom::operator ()(const Fraction& x) const {
    Fraction result;
    for (int i = coeff.size() - 1; i > 0; -- i) {
        result = x * (result + coeff[i]);
    }
    return result + coeff[0];
}

// ------------------------------------------------------------------------------------------------ POLYNOM FRIEND FUNCTIONS

// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS

/**
    Overloading multiplication operator for a polynomial and a fraction.
    Note that this does not change the objects involved in the addition.

    Returns the result of the multiplication: each coefficient in the original polynomial has
    been multiplied by the fraction specified.
    Note that if the fraction specified has a value of 0, the resultant polynomial is the null
    polynomial.
*/
const Polynom operator *(const Fraction& f, const Polynom& p) {
    // check for if the fraction has a value of 0 (if it is, return the null polynomial)
    if (f.getNum() == 0)
        return Polynom();

    Polynom prod(p.coeff);
    for (int i = 0; i <= p.getDegree(); ++ i)
        prod.coeff[i] *= f; // multiply each coefficient by the specified fraction
    return prod;
}

/**
    Overloading addition operator for a polynomial and a fraction.
    Note that this does not change the objects involved in the addition.

    Returns the result of the summation: the resultant polynomial is the original polynomial
    with the fraction added to the coefficient for x^0.
*/
const Polynom operator +(const Polynom& p, const Fraction& f) {
    Polynom sum(p.coeff);
    sum.coeff[0] += f;

    return sum;
}

/**
    Overloading addition operator for polynomials.
    Note that this does not change the polynomials involved in the addition.

    Returns the result of the summation (each corresponding coefficient has been added,
    and all leading zeros removed).
*/
const Polynom operator +(const Polynom& p1, const Polynom& p2) {
    Polynom sum(p1.coeff);
    sum += p2; // leading zeros removed here

    return sum;
}

/**
    Overloading multiplication operator for polynomials.
    Note that this does not change the polynomials involved in the subtraction.

    Returns the product of the 2 polynomials (distributive property is used to multiply them,
    and all leading zeroes are removed).
*/
const Polynom operator *(const Polynom& p1, const Polynom& p2) {
    Polynom prod; // start with null polynomial

    // the idea: multiply each entry in p1 by p2 and take the sum
    // start with the number of zeroes corresponding to the degree of the entry in p1
    // then, add to this vector (push_back, so the leading zeroes remain) the product
    // of this coefficient in p1 with each consecutive coefficient in p2
    // then, add this resultant polynomial to prod, and repeat for the rest of the entries
    // in p1
    for (int i = 0; i <= p1.getDegree(); ++ i) {
        vector<Fraction> coeffs(i);
        for (int j = 0; j <= p2.getDegree(); ++ j) {
            coeffs.push_back(p1.coeff[i] * p2.coeff[j]);
        }
        prod += Polynom(coeffs);
    }

    return prod;
}

/**
    Overloading subtraction operator for polynomials.
    Note that this does not change the polynomials involved in the subtraction.

    Returns the result of the subtraction (each corresponding coefficient has been subtracted,
    and all leading zeros removed).
*/
const Polynom operator -(const Polynom& p1, const Polynom& p2) {
    Polynom neg = -p2;

    return (p1 + neg);
}

// --------------------------------------------------------------------------------- POLYNOM NON-FRIEND NON-MEMBER FUNCTIONS
/**
    Function to remove the leading zeroes from a vector of coefficients.
    Note: leading zeroes are zeroes at the end of the vector (representing coefficients
    for higher degrees).
    Note also: the minimum degree for a polynomial is 0.  If the vector is all zeroes, after
    leading zeroes have been removed, the vector will be of size 1 with a 0 in position 0
    (i.e. the null polynomial).
*/
void removeLeadZeros(vector<Fraction>& coeffs) {
    int newDegree = 1;
    bool foundNonZero = false;
    // run from the end of the vector until either the beginning has been reached
    // or a non-zero value has been found
    for (int i = coeffs.size() - 1; i >= 0 && !foundNonZero; -- i) {
        if (coeffs[i] != 0) {
            newDegree = i + 1;
            foundNonZero = true;
        }
    }
    coeffs.resize(newDegree); // resize to remove the zeroes at the end
}


int main() {
    Fraction f1(3, 3), f3(1, 5);

    f1.print();
    cout << "\n";
    f3.print();
    cout << "\n";

    Fraction add = f1 - f3;

    add.print();
    cout << "\n";

    Fraction mult = f1 * f3;

    mult.print();
    cout << "\n";

    Fraction div = f1 / f3;

    div.print();
    cout << "\n";


    vector<Fraction> c1s;
    for (int i = 0; i < 3; ++ i)
        c1s.push_back(Fraction(i + 1));

    vector<Fraction> c2s;
    for (int i = 0; i < 4; ++ i)
        c2s.push_back(Fraction(i + 3));
    c2s.push_back(0);
    c2s.push_back(0);
    c2s.push_back(0);


    Polynom p1(c1s), p2(c2s);

    p1.print();
    p2.print();

    cout << "\n";
    (p1 + p2).print();

    cout << "\n";
    (p1 - p1).print();

    vector<Fraction> c3s;
    c3s.push_back(-3);
    c3s.push_back(1);

    Polynom p3(c3s);

    vector<Fraction> c4s;
    c4s.push_back(3);
    c4s.push_back(1);

    Polynom p4(c4s);


    cout << "\n\n";
    p2.print();

    p2(Fraction(2)).print();
    cout << " : " << p2(Fraction(2)) << "\n";

    cout << "\n";
    (p1 * p2).print();

    return 0;
}
