
#include "SymbCheck_Real.h"
#include <iostream>
#include <sstream>

/*
    Argumented constructor, taking in a string representation of an expression to parse out.
*/
SymbCheck_Real::SymbCheck_Real(const std::string& s) {
    vars = splitBy('+', s); // list of variables initialized to elements in the sum making up s
    coeffs = colRemCoeff(vars); // get coefficients for each variable in vars, remove these coefficients from the strings in vars
    simpVars(vars, coeffs); // collect like terms (and their coefficients), and remove duplicates
    remZeros(vars, coeffs); // remove any variables with coefficients of 0
}

/*
    Default constructor - initialize this expression to 0.
*/
SymbCheck_Real::SymbCheck_Real() { // default is 0
    vars.push_back("1");
    coeffs.push_back(0);
}

/*
    Constructor to initialize this expression to a specified variable (character).
*/
SymbCheck_Real::SymbCheck_Real(char c) {
    // go through all this string-parsing mess since this char could be numerical or not
    std::stringstream sstm("");
    sstm << c;
    vars = splitBy('+', sstm.str());
    coeffs = colRemCoeff(vars);
    simpVars(vars, coeffs);
    remZeros(vars, coeffs);
}

/*
    Constructor to initialize this expression to a specified integer value.
*/
SymbCheck_Real::SymbCheck_Real(int val) {
    vars.push_back("1");
    coeffs.push_back(val);
}

/*
    Constructor to initialize this expression to a specified double value.
*/
SymbCheck_Real::SymbCheck_Real(double val) {
    vars.push_back("1");
    coeffs.push_back(val);
}

/*
    Comparison operator to check if SymbCheck_Real passed in is equal to "this" expression.
    Note: they are equal if every variable in scr is contained and has the same coefficient
    in "this", and vice-versa.
*/
bool SymbCheck_Real::operator == (const SymbCheck_Real& scr) const {
    if (scr.vars.size() != vars.size()) // if they have different numbers of variables
        return false;
    for (int i = 0; i < vars.size(); ++ i) {
        int index = i;
        if (!contains(scr.vars, vars[i], index)) // if one variable in "this" is not contained in scr
            return false;
        else if (coeffs[i] != scr.coeffs[index]) // if the variable in in "this" and scr, but the coefficients are different
            return false;
    }
    return true;
}

/*
    Addition operator to another SymbCheck_Real object.
*/
const SymbCheck_Real SymbCheck_Real::operator + (const SymbCheck_Real& scr) const {
    // just add the string representations of each of the SymbCheck_Real's in the sum
    std::string sum = getRep();
    sum += "+" + scr.getRep();

    return SymbCheck_Real(sum);
}

/*
    Multiplication operator by another SymbCheck_Real object.
    Normal distributive property is used here.
*/
const SymbCheck_Real SymbCheck_Real::operator * (const SymbCheck_Real& scr) const {
    std::stringstream sstm("");
    for (int i = 0; i < vars.size(); ++ i) { // for every element in "this" expression
        if (i != 0)
            sstm << "+";
        for (int j = 0; j < scr.vars.size(); ++ j) { // multiply current element in "this" by every element in scr
            if (j != 0)
                sstm << "+";
            int cMult = coeffs[i] * scr.coeffs[j]; // multiply coefficients
            if (cMult != 1)
                sstm << cMult << "*";
            sstm << vars[i] << "*" << scr.vars[j]; // multiply variables
        }
    }

    return SymbCheck_Real(sstm.str());
}

/*
    Print a nice representation of "this" expression - this one cannot be reparsed
    into a SymbCheck_Real constructor later.
*/
void SymbCheck_Real::print() const {
    std::cout << "\n";
    for (int i = 0; i < vars.size(); ++ i) { // every element in vars is part of a sum
        if (i != 0)
            std::cout << " + ";
        if (coeffs[i] != 1) // don't show coeff if it's 1
            std::cout << "(" << coeffs[i] << ")";
        std::cout << vars[i];
    }
}

/*
    Return a string representation of "this" expression - note that this expression can
    be passed into SymbCheck_Real constructor and reparsed successfully.
*/
std::string SymbCheck_Real::getRep() const {
    std::stringstream sstm("");
    for (int i = 0; i < vars.size(); ++ i) { // every element in vars is part of a sum
        if (i != 0)
            sstm << "+";
        if (coeffs[i] != 1) // don't show the coefficient it it's 1
            sstm << coeffs[i] << "*";
        sstm << vars[i];
    }
    return sstm.str();
}

/*
    Function to split a given string by a specified character.  Returns a vector where the elements are
    consecutive delimited sections of the string to split (delimiter not included).
*/
std::vector<std::string> splitBy(char delim, const std::string& toSplit) {
    std::vector<std::string> div;

    std::string temp = "";
    for (int i = 0; i < toSplit.size(); ++ i) {
        if (toSplit[i] != delim) // if the current character is not the delimiter, it's part of the current section
            temp += toSplit[i];
        if ((toSplit[i] == delim || i == toSplit.size() - 1) && temp != "") {
            // if the current character is the delimiter, or it's the last character, and the current section is non-empty,
            // then add it to the vector and reset the current section to ""
            div.push_back(temp);
            temp = "";
        }
    }
    return div;
}

/*
    Function to determine whether or not "this" expression represents a numerical value.
    Return true if yes, false otherwise.
*/
bool SymbCheck_Real::isValue() const {
    // in this case, the length of the vars vector is 1, and the only value is it is 1 ("1" is the only variable)
    return (vars.size() == 1 && vars[0] == "1");
}

/*
    Function to determine whether or not a given vector of strings contains the specified
    string.  Returns true if the variable is contained, false if not.
    Note: an integer representing the index in the vector is passed in by reference.  If the
    value is found, this index is changed to the index where the value is (if not found, this
    is not modified).
*/
bool contains(const std::vector<std::string>& v, const std::string& check, int& index) {
    std::vector<std::string> checkSet = splitBy('*', check); // vector of variables making up the product to check
    for (int i = 0; i < v.size(); ++ i) {
        if (v[i] == check) { // simple case - check is the same as the current element in the vector
            index = i;
            return true;
        }

        // non-simple case: this is to solve the problem that (for example):
        // a*b*c is equivalent to b*a*c

        std::vector<std::string> viSet = splitBy('*', v[i]); // vector of variables making up the current element in v

        if (checkSet.size() != viSet.size()) // if the number of elements in the product is different, the products are different
            continue;

        bool has = true; // does the vector v contain an equivalent value to check? true or false
        for (int k = 0; k < checkSet.size(); ++ k) { // for every element in the product check
            bool hasSet = false; // does viSet contain the current element in check? true or false
            for (int j = 0; j < viSet.size(); ++ j) { // check every element in the product making up the current element of v
                hasSet = (hasSet || (checkSet[k] == viSet[j]));
            }
            has = has && hasSet;
        }

        if (has) { // here, check is the same as the current element in the vector
            index = i;
            return true;
        }
    }
    return false;
}

/*
    Function which takes in a vector of strings (representing an expression, where each element in the
    vector is an element in the sum making up the expression), and removes the coefficients from each
    entry.  Then, it returns a vector of these coefficients (each coefficient corresponds to the entry
    with the same index in the vector of strings).
*/
std::vector<double> colRemCoeff(std::vector<std::string>& v) {
    std::vector<double> coeffs(v.size());

    for (int i = 0; i < v.size(); ++ i) { // for every entry in the array of strings
        std::vector<std::string> vSplit = splitBy('*', v[i]); // split current entry, delimited by *
        double curCoeff = 1; // coefficient of the current entry in v
        std::string curVar = ""; // current variable
        for (int j = 0; j < vSplit.size(); ++ j) { // for every entry in the *-delimited split
            std::stringstream sstm(vSplit[j]);
            double val = 0;
            sstm >> val; // try to read in a value from the string
            if (sstm.fail()) { // if it wasn't a numerical value, then it must be part of the variable
                if (curVar != "") // if it's not the first variable in the product making up the current variable
                    curVar += "*";
                curVar += vSplit[j];
            }
            else // otherwise, it's a value, which makes it part of the coefficient
                curCoeff *= val; // this whole mess is a product... so is the coefficient
        }
        coeffs[i] = curCoeff; // this is the coefficient for the current variable
        if (curVar == "") // if all the entries in the sum were numerical, this current variable is actually a number
            curVar = "1"; // represent numbers by the variable "1"
        v[i] = curVar; // update the vector of variables
    }

    return coeffs;
}

/*
    Function to simplify the vector of variables so it contains no duplicates (i.e. turn it into a set).
    And, if duplicates occur, update the vector of coefficients so the coefficient for this variable is
    the sum of the coefficients for each separate instance of the variable.
    So, for example:
    Variables:    {x, y, x}
    Coefficients: {3, 2, 2}
    Becomes: Vars: {x, y}
           Coeffs: {5, 2}
*/
void simpVars(std::vector<std::string>& v, std::vector<double>& c) {
    // this is to remove duplicates.  the vector should contain a list of all the variables, as a set
    std::vector<std::string> vSet;
    std::vector<double> cSet;
    for (int i = 0; i < v.size(); ++ i) {
        int index = i; // index will be changed to the value of where the value is stored in vSet
        if (!contains(vSet, v[i], index)) {
            vSet.push_back(v[i]);
            cSet.push_back(c[i]);
        }
        else  // value is found at position index
            cSet[index] += c[i]; // sum the coefficients for occurences of the same variable
    }
    // reset the vectors passed in (actually modified since passed in by reference)
    v = vSet;
    c = cSet;
}

/*
    Function to remove variables with coefficients of 0 from the set (since, if
    the coefficient is 0, the variable is not actually included in the expression).

    Note: if all the coefficients are 0, instead of having an empty set, have one
    entry in the set of variables -> 1, with a coefficient of 0.
*/
void remZeros(std::vector<std::string>& v, std::vector<double>& c) {
    std::vector<std::string> vNO;
    std::vector<double> cNO;

    for (int i = 0; i < v.size(); ++ i) {
        if (c[i] != 0) { // only include the variable in the set if the coefficient is non-zero
            vNO.push_back(v[i]);
            cNO.push_back(c[i]);
        }
    }

    if (vNO.empty()) { // if all coefficients are 0...
        vNO.push_back("1");
        cNO.push_back(0);
    }
    // reset the vectors passed in (actually modified since passed in by reference)
    v = vNO;
    c = cNO;
}
