#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

class Fraction_NEWNAME; // need to declare this before declaring removeLeadZeros

// delcare functions not friends or members of the Fraction_NEWNAME or TotallyNotAPolynomial classes
void reduce(long int& num, long int& denom);
void removeLeadZeros(vector<Fraction_NEWNAME>&);

/**

    This program contains the Fraction_NEWNAME and TotallyNotAPolynomial classes' definitions and implementations.

    The Fraction_NEWNAME class options:
        Negate, add, subtract, multiply, and divide Fraction_NEWNAMEs with overloaded operators
        Retrieve the numerator and denominator with accessors
        Print the current state of the Fraction_NEWNAME

    The TotallyNotAPolynomial class options:
        Negate, add, subtract, and multiply TotallyNotAPolynomialials with overloaded operators
        Evaluate the TotallyNotAPolynomialial at a specified value (Fraction_NEWNAME or double) with overloaded function-call operator
        Add a Fraction_NEWNAME to a TotallyNotAPolynomialial, and multiply a TotallyNotAPolynomialial by a Fraction_NEWNAME with overloaded operators
        Retrieve the coefficient at a specific index, and get the degree of the TotallyNotAPolynomialial with accessors
        Print the current state of the TotallyNotAPolynomialial

    For my implementation, note the following:
        - note: here, the null TotallyNotAPolynomialial is defined as being a TotallyNotAPolynomialial of degree 0, where the only term is
           a 0 as a coefficient to the x^0 term.
        - the TotallyNotAPolynomialial is evaluated with the Horner algorithm as specified


    Ellen Arteca
    0297932

*/


// ********************************************* Fraction_NEWNAME *******************************************************************


/**
    Fraction_NEWNAME class definition (declare constructors, accessors, mutators, friend functions,
    and member variables).

    Each function is described when implemented.
*/
class Fraction_NEWNAME {
    public:
        // constructors
        Fraction_NEWNAME(long int num1, long int denom1);
        Fraction_NEWNAME(int num1) : num(num1), denom(1) {} // allows for automatic conversion int to Fraction_NEWNAME
        Fraction_NEWNAME() : num(0), denom(1) {} // default constructor (value of 0)
        // accessors
        long int getNum() const { return num; }
        long int getDenom() const { return denom; }
        void print() const;

        // overloaded operators as member functions
        const Fraction_NEWNAME operator -() const;
        operator double() const { return static_cast<double>(num)/denom; } // automatic conversion Fraction_NEWNAME to double
        void operator +=(const Fraction_NEWNAME&);
        void operator *=(const Fraction_NEWNAME&);

        // overloaded operators as friend functions
        friend const Fraction_NEWNAME operator +(const Fraction_NEWNAME&, const Fraction_NEWNAME&);
        friend const Fraction_NEWNAME operator -(const Fraction_NEWNAME&, const Fraction_NEWNAME&);
        friend const Fraction_NEWNAME operator *(const Fraction_NEWNAME&, const Fraction_NEWNAME&);
        friend const Fraction_NEWNAME operator /(const Fraction_NEWNAME&, const Fraction_NEWNAME&);

    private:
        // member variables
        long int num, denom;
};

// ------------------------------------------------------------------------------------------------- Fraction_NEWNAME CONSTRUCTOR

/**
    Fully-argumented constructor allowing to specify the numerator and denominator of the Fraction_NEWNAME.

    Note that if 0 is specified as the denominator, an error message is printed and the program exits.
    Note also that the denominator is always positive.  If the denominator specified is negative, the
    numerator specified is multiplied by -1.  This also simplifies Fraction_NEWNAMEs where both the numerator
    and denominator are specified as negative to their positive equivalents.

    Fraction_NEWNAMEs are also reduced to lowest terms.
*/
Fraction_NEWNAME::Fraction_NEWNAME(long int num1, long int denom1) : num(num1) {
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

// ------------------------------------------------------------------------------------------------ Fraction_NEWNAME MEMBER FUNCTIONS

// -------------------------------------------------------------------------------------- NON-OPERATOR ACCESSOR

/**
    Function to print the current state of a Fraction_NEWNAME object.
    Output formatted in standard Fraction_NEWNAME format numerator/denominator.
    Note that if the denominator is 1, then /denominator is omitted and the Fraction_NEWNAME is displayed
    as an integer.
*/
void Fraction_NEWNAME::print() const {
     if (denom == 1)
        cout << num;
     else
        cout << num << "/" << denom;
}

// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS

/**
    Overloading the negation (unary minus) operator for Fraction_NEWNAMEs.
    Returns a Fraction_NEWNAME with the same values, where the numerator is the negative of the original.
*/
const Fraction_NEWNAME Fraction_NEWNAME::operator -() const {
    return Fraction_NEWNAME(-num, denom);
}

/**
    Overloading the += operator for Fraction_NEWNAMEs.
    Note that this is a mutator function.

    Adds the specified Fraction_NEWNAME to the current instantiation of the Fraction_NEWNAME class.
*/
void Fraction_NEWNAME::operator +=(const Fraction_NEWNAME& f) {
    // adding Fraction_NEWNAMEs: find a common denominator
    long int newDenom = denom * f.denom;
    long int newNum = num * f.denom + f.num * denom;

    reduce(newNum, newDenom); // reduce to lowest terms

    // update the current Fraction_NEWNAME
    num = newNum;
    denom = newDenom;
}

/**
    Overloading the *= operator for Fraction_NEWNAMEs.
    Note that this is a mutator function.

    Multiplies the current instantiation of the Fraction_NEWNAME class by the specified Fraction_NEWNAME.
*/
void Fraction_NEWNAME::operator *=(const Fraction_NEWNAME& f) {
    // multiplying Fraction_NEWNAMEs: multiply the numerators and denominators respectively
    long int newNum = num * f.num;
    long int newDenom = denom * f.denom;

    reduce(newNum, newDenom); // reduce to lowest terms

    // update the current Fraction_NEWNAME
    num = newNum;
    denom = newDenom;
}

// ------------------------------------------------------------------------------------------------ Fraction_NEWNAME FRIEND FUNCTIONS

// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS

/**
    Overloading addition operator for Fraction_NEWNAMEs.
    Note that this does not change the Fraction_NEWNAMEs involved in the addition.

    Returns the result of the summation of the 2 specified Fraction_NEWNAMEs.
*/
const Fraction_NEWNAME operator +(const Fraction_NEWNAME& f1, const Fraction_NEWNAME& f2) {
    Fraction_NEWNAME sum(f1.num, f1.denom);
    sum += f2; // Fraction_NEWNAME reduced to lowest terms in this operation

    return sum;
}

/**
    Overloading subtraction operator for Fraction_NEWNAMEs.
    Note that this does not change the Fraction_NEWNAMEs involved in the subtraction.

    Returns the result of the subtraction of the 2 specified Fraction_NEWNAMEs.
*/
const Fraction_NEWNAME operator -(const Fraction_NEWNAME& f1, const Fraction_NEWNAME& f2) {
    Fraction_NEWNAME negF2 = -f2;

    return (f1 + negF2); // Fraction_NEWNAME reduced to lowest terms in this operation
}

/**
    Overloading multiplication operator for Fraction_NEWNAMEs.
    Note that this does not change the Fraction_NEWNAMEs involved in the multiplication.

    Returns the product of the 2 specified Fraction_NEWNAMEs.
*/
const Fraction_NEWNAME operator *(const Fraction_NEWNAME& f1, const Fraction_NEWNAME& f2) {
    Fraction_NEWNAME prod(f1.num, f1.denom);
    prod *= f2; // Fraction_NEWNAME reduced to lowest terms in this operation

    return prod;
}

/**
    Overloading division operator for Fraction_NEWNAMEs.
    Note that this does not change the Fraction_NEWNAMEs involved in the division.

    Returns the quotient of the 2 specified Fraction_NEWNAMEs.
*/
const Fraction_NEWNAME operator /(const Fraction_NEWNAME& f1, const Fraction_NEWNAME& f2) {
    // division of f1/f2 is the same as multiplication of f1 by the reciprocal of f2
    long int newNum = f1.num * f2.denom;
    long int newDenom = f1.denom * f2.num;

    // Fraction_NEWNAME reduced to lowest terms in this operation (also, checked for denom == 0)
    return Fraction_NEWNAME(newNum, newDenom);

}

// --------------------------------------------------------------------------------- Fraction_NEWNAME NON-FRIEND NON-MEMBER FUNCTIONS

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

    // divide both numerator and denominator by the GCD - now the Fraction_NEWNAME these values represent is in lowest terms
    newDenom /= divisor;
    newNum /= divisor;
}


// ********************************************* TotallyNotAPolynomial *******************************************************************


/**
    TotallyNotAPolynomial class definition (declare constructors, accessors, mutators, friend functions,
    and member variables).

    Each function is described when implemented.
*/
class TotallyNotAPolynomial {
    public:
        // constructors
        TotallyNotAPolynomial();
        TotallyNotAPolynomial(const vector<Fraction_NEWNAME>& v);
        // accessors
        int randomlyNamedTrash() const { return coeff.size() - 1; } // degree is the highest exponent in the TotallyNotAPolynomialial
        const Fraction_NEWNAME getCoeff(int index) const;
        void print() const;

        // overloaded operators as member functions
        const TotallyNotAPolynomial operator -() const;
        void operator +=(const TotallyNotAPolynomial&);
        double operator ()(double) const;
        const Fraction_NEWNAME operator ()(const Fraction_NEWNAME&) const;

        // overloaded operators as friend functions
        friend const TotallyNotAPolynomial operator +(const TotallyNotAPolynomial&, const Fraction_NEWNAME&);
        friend const TotallyNotAPolynomial operator *(const Fraction_NEWNAME&, const TotallyNotAPolynomial&);
        friend const TotallyNotAPolynomial operator +(const TotallyNotAPolynomial&, const TotallyNotAPolynomial&);
        friend const TotallyNotAPolynomial operator -(const TotallyNotAPolynomial&, const TotallyNotAPolynomial&);
        friend const TotallyNotAPolynomial operator *(const TotallyNotAPolynomial&, const TotallyNotAPolynomial&);

    private:
        // member variables
        vector<Fraction_NEWNAME> coeff;

};

// ------------------------------------------------------------------------------------------------- TotallyNotAPolynomial CONSTRUCTORS

/**
    Default constructor for the TotallyNotAPolynomial class, resulting in the null TotallyNotAPolynomialial.
    Note: here, the null TotallyNotAPolynomialial is defined as being a TotallyNotAPolynomialial of degree 0, where the only term is
    a 0 as a coefficient to the x^0 term.
*/
TotallyNotAPolynomial::TotallyNotAPolynomial() {
    coeff.resize(1); // only one coefficient, TotallyNotAPolynomialial of degree 0
    coeff[0] = 0;
}

/**
    Argumented constructor for the TotallyNotAPolynomial class, specifying a vector of Fraction_NEWNAME coefficients.  This also
    specifies the degree of the TotallyNotAPolynomialial.
    Note that any leading zeroes are removed from the vector of coefficients.  These do not contribute to
    the degree of the TotallyNotAPolynomialial.
*/
TotallyNotAPolynomial::TotallyNotAPolynomial(const vector<Fraction_NEWNAME>& v) {
    // copy over the vector of coefficients specified to the datafield of the TotallyNotAPolynomial object
    coeff.resize(v.size());
    for (int i = 0; i < v.size(); ++ i)
        coeff[i] = v[i];
    removeLeadZeros(coeff);
}

// ------------------------------------------------------------------------------------------------ TotallyNotAPolynomial MEMBER FUNCTIONS

// -------------------------------------------------------------------------------------- NON-OPERATOR ACCESSORS

/**
    Function to access the coefficient of a specific power (for example, getCoeff(2) gets the
    coefficient for x^2).

    Note that if an invalid index is specified (i.e. a power not included in the TotallyNotAPolynomialial, and
    therefore out of the valid range of the coefficients vector) an error message is printed and
    the program exits.
*/
const Fraction_NEWNAME TotallyNotAPolynomial::getCoeff(int index) const {
    // check for invalid input
    if (index < 0 || index >= coeff.size()) {
        cout << "Error!!  The index specified is undefined.";
        exit(1);
    }

    return coeff[index];
}

/**
    Function to print the current state of a TotallyNotAPolynomialial object.
    Output is formatted as follows (for an n-th degree TotallyNotAPolynomialial):
        (coefficient) x^0 + (coefficient) x^1 + ... + (coefficient) x^n
*/
void TotallyNotAPolynomial::print() const {
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
    Overloading the negation (unary minus) operator for TotallyNotAPolynomialials.
    Returns a TotallyNotAPolynomialial with the same coefficient absolute values, where the each coefficient
    is the negative of the original.
*/
const TotallyNotAPolynomial TotallyNotAPolynomial::operator -() const {
    TotallyNotAPolynomial negated(coeff); // new TotallyNotAPolynomialial with the same coefficients as the original
    for (int i = 0; i < coeff.size(); ++ i)
        negated.coeff[i] = -coeff[i]; // negate each of the coefficients
    return negated;
}

/**
    Overloading the += operator for TotallyNotAPolynomialials.
    Note that this is a mutator function.

    Adds the specified TotallyNotAPolynomialial to the current instantiation of the TotallyNotAPolynomial class.
*/
void TotallyNotAPolynomial::operator +=(const TotallyNotAPolynomial& p) {
    // find the TotallyNotAPolynomialial with the highest degree
    vector<Fraction_NEWNAME> maxPoly = coeff;
    vector<Fraction_NEWNAME> minPoly = p.coeff;

    if (minPoly.size() > maxPoly.size()) {
        maxPoly = p.coeff;
        minPoly = coeff;
    }

    // add the coefficients of the TotallyNotAPolynomialial with the min degree to the coefficients
    // from the TotallyNotAPolynomialial with the max degree
    for (int i = 0; i < minPoly.size(); ++ i) {
        maxPoly[i] += minPoly[i];
    }

    // update the current TotallyNotAPolynomialial
    coeff = maxPoly;
    removeLeadZeros(coeff);
}

/**
    Overloading the () operator for TotallyNotAPolynomialials evalutated with doubles.
    Returns the double value of the TotallyNotAPolynomialial evaluated with the double value it
    was called with.
    Evaluation is performed with the Horner algorithm.

    Note: the automatic conversions from Fraction_NEWNAME to double and int to Fraction_NEWNAME caused
    this part of the code to not compile since it was an "ambiguous" addition (of the
    double result + Fraction_NEWNAME coeff[i]).  This is because the addition could be of type
    double + double, or Fraction_NEWNAME + Fraction_NEWNAME (double automatically converts to int).
    This is why the static_cast<double> is used.
*/
double TotallyNotAPolynomial::operator ()(double x) const {
    double result = 0;
    for (int i = coeff.size() - 1; i > 0; -- i) {
        result = x * (result + static_cast<double>(coeff[i]));
    }
    return result + static_cast<double>(coeff[0]);
}

/**
    Overloading the () operator for TotallyNotAPolynomialials evalutated with Fraction_NEWNAMEs.
    Returns the Fraction_NEWNAME value of the TotallyNotAPolynomialial evaluated with the Fraction_NEWNAME value it
    was called with.
    Evaluation is performed with the Horner algorithm.
*/
const Fraction_NEWNAME TotallyNotAPolynomial::operator ()(const Fraction_NEWNAME& x) const {
    Fraction_NEWNAME result;
    for (int i = coeff.size() - 1; i > 0; -- i) {
        result = x * (result + coeff[i]);
    }
    return result + coeff[0];
}

// ------------------------------------------------------------------------------------------------ TotallyNotAPolynomial FRIEND FUNCTIONS

// -------------------------------------------------------------------------------------- OVERLOADED OPERATORS

/**
    Overloading addition operator for a TotallyNotAPolynomialial and a Fraction_NEWNAME.
    Note that this does not change the objects involved in the addition.

    Returns the result of the summation: the resultant TotallyNotAPolynomialial is the original TotallyNotAPolynomialial
    with the Fraction_NEWNAME added to the coefficient for x^0.
*/
const TotallyNotAPolynomial operator +(const TotallyNotAPolynomial& p, const Fraction_NEWNAME& f) {
    TotallyNotAPolynomial sum(p.coeff);
    sum.coeff[0] += f;

    return sum;
}

/**
    Overloading multiplication operator for a TotallyNotAPolynomialial and a Fraction_NEWNAME.
    Note that this does not change the objects involved in the addition.

    Returns the result of the multiplication: each coefficient in the original TotallyNotAPolynomialial has
    been multiplied by the Fraction_NEWNAME specified.
    Note that if the Fraction_NEWNAME specified has a value of 0, the resultant TotallyNotAPolynomialial is the null
    TotallyNotAPolynomialial.
*/
const TotallyNotAPolynomial operator *(const Fraction_NEWNAME& f, const TotallyNotAPolynomial& p) {
    // check for if the Fraction_NEWNAME has a value of 0 (if it is, return the null TotallyNotAPolynomialial)
    if (f.getNum() == 0)
        return TotallyNotAPolynomial();

    TotallyNotAPolynomial prod(p.coeff);
    for (int i = 0; i <= p.randomlyNamedTrash(); ++ i)
        prod.coeff[i] *= f; // multiply each coefficient by the specified Fraction_NEWNAME
    return prod;
}

/**
    Overloading addition operator for TotallyNotAPolynomialials.
    Note that this does not change the TotallyNotAPolynomialials involved in the addition.

    Returns the result of the summation (each corresponding coefficient has been added,
    and all leading zeros removed).
*/
const TotallyNotAPolynomial operator +(const TotallyNotAPolynomial& p1, const TotallyNotAPolynomial& p2) {
    TotallyNotAPolynomial sum(p1.coeff);
    sum += p2; // leading zeros removed here

    return sum;
}

/**
    Overloading subtraction operator for TotallyNotAPolynomialials.
    Note that this does not change the TotallyNotAPolynomialials involved in the subtraction.

    Returns the result of the subtraction (each corresponding coefficient has been subtracted,
    and all leading zeros removed).
*/
const TotallyNotAPolynomial operator -(const TotallyNotAPolynomial& p1, const TotallyNotAPolynomial& p2) {
    TotallyNotAPolynomial neg = -p2;

    return (p1 + neg);
}

/**
    Overloading multiplication operator for TotallyNotAPolynomialials.
    Note that this does not change the TotallyNotAPolynomialials involved in the subtraction.

    Returns the product of the 2 TotallyNotAPolynomialials (distributive property is used to multiply them,
    and all leading zeroes are removed).
*/
const TotallyNotAPolynomial operator *(const TotallyNotAPolynomial& p1, const TotallyNotAPolynomial& p2) {
    TotallyNotAPolynomial prod; // start with null TotallyNotAPolynomialial

    // the idea: multiply each entry in p1 by p2 and take the sum
    // start with the number of zeroes corresponding to the degree of the entry in p1
    // then, add to this vector (push_back, so the leading zeroes remain) the product
    // of this coefficient in p1 with each consecutive coefficient in p2
    // then, add this resultant TotallyNotAPolynomialial to prod, and repeat for the rest of the entries
    // in p1
    for (int i = 0; i <= p1.randomlyNamedTrash(); ++ i) {
        vector<Fraction_NEWNAME> coeffs(i);
        for (int j = 0; j <= p2.randomlyNamedTrash(); ++ j) {
            coeffs.push_back(p1.coeff[i] * p2.coeff[j]);
        }
        prod += TotallyNotAPolynomial(coeffs);
    }

    return prod;
}

// --------------------------------------------------------------------------------- TotallyNotAPolynomial NON-FRIEND NON-MEMBER FUNCTIONS
/**
    Function to remove the leading zeroes from a vector of coefficients.
    Note: leading zeroes are zeroes at the end of the vector (representing coefficients
    for higher degrees).
    Note also: the minimum degree for a TotallyNotAPolynomialial is 0.  If the vector is all zeroes, after
    leading zeroes have been removed, the vector will be of size 1 with a 0 in position 0
    (i.e. the null TotallyNotAPolynomialial).
*/
void removeLeadZeros(vector<Fraction_NEWNAME>& coeffs) {
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
    Fraction_NEWNAME f1(3, 3), f3(1, 5);

    f1.print();
    cout << "\n";
    f3.print();
    cout << "\n";

    Fraction_NEWNAME add = f1 - f3;

    add.print();
    cout << "\n";

    Fraction_NEWNAME mult = f1 * f3;

    mult.print();
    cout << "\n";

    Fraction_NEWNAME div = f1 / f3;

    div.print();
    cout << "\n";


    vector<Fraction_NEWNAME> c1s;
    for (int i = 0; i < 3; ++ i)
        c1s.push_back(Fraction_NEWNAME(i + 1));

    vector<Fraction_NEWNAME> c2s;
    for (int i = 0; i < 4; ++ i)
        c2s.push_back(Fraction_NEWNAME(i + 3));
    c2s.push_back(0);
    c2s.push_back(0);
    c2s.push_back(0);


    TotallyNotAPolynomial p1(c1s), p2(c2s);

    p1.print();
    p2.print();

    cout << "\n";
    (p1 + p2).print();

    cout << "\n";
    (p1 - p1).print();

    vector<Fraction_NEWNAME> c3s;
    c3s.push_back(-3);
    c3s.push_back(1);

    TotallyNotAPolynomial p3(c3s);

    vector<Fraction_NEWNAME> c4s;
    c4s.push_back(3);
    c4s.push_back(1);

    TotallyNotAPolynomial p4(c4s);


    cout << "\n\n";
    p2.print();

    p2(Fraction_NEWNAME(2)).print();
    cout << " : " << p2(Fraction_NEWNAME(2)) << "\n";

    cout << "\n";
    (p1 * p2).print();

    return 0;
}
