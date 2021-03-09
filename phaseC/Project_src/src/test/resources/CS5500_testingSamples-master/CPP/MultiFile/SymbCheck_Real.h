
#ifndef SYMB_CHECK_REAL_H
#define SYMB_CHECK_REAL_H

#include <string>
#include <vector>

/**
    This is the main class which makes this whole project work.  Working with strings, this class allows
    for the symbolic representation of an expression, and for standard algebra to be performed i.e. working
    with variables, expressions can be added, or multiplied by other expressions (including scalars).

    The variables in the expression are stored in an array of strings (note: if there are any numerical values
    in the expression, they are stored as "1" in the array of variables), with their coefficients stored in an
    array of doubles.
    The variables are defined as the "like terms" in the expression (so, x and x*y and y are all separate variables).
    So: for example, given: x + z*y + 12*x*y + 14.5
    Array of variables:     {x, z*y, x*y, 1}
    Array of coefficients:  {1, 1, 12, 14.5}
*/

std::vector<std::string> splitBy(char, const std::string&);
std::vector<double> colRemCoeff(std::vector<std::string>& v);
void simpVars(std::vector<std::string>& v, std::vector<double>& c);
bool contains(const std::vector<std::string>& v, const std::string& check, int& index);
void remZeros(std::vector<std::string>& v, std::vector<double>& c);

class SymbCheck_Real {

    public:
        // constructors
        SymbCheck_Real(const std::string& s);
        SymbCheck_Real();
        SymbCheck_Real(int);
        SymbCheck_Real(double);
        SymbCheck_Real(char);

        bool operator == (const SymbCheck_Real&) const; // check for equality
        // add and multiply by other expressions
        const SymbCheck_Real operator + (const SymbCheck_Real&) const;
        const SymbCheck_Real operator * (const SymbCheck_Real&) const;

        void print() const;
        std::string getRep() const; // get string representation of the expression (can be reparsed later)
        bool isValue() const;

    private:
        std::vector<std::string> vars; // set of variables in the expression
        std::vector<double> coeffs; // list of coefficients (corresponding to the variables in the set above)

};

#endif
