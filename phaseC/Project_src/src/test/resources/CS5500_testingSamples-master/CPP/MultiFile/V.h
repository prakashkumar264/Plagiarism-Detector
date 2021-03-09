
#ifndef V_H
#define V_H

#include "SymbCheck_Real.h"

/**
    Class to represent a vector from an n-dimensional vector space with standard addition and scalar
    multiplication defined.
    This is a template, where you can specify the element type, scalar type, and dimension.
    For example, to get R3, defined with scalar multiplication by Z, this would be:
        V<double, int, 3>

    Note: instantiations of this class are immutable.
*/

template <typename eltType, typename scalType, const int len> class V {

    public:
        V(const eltType ar[len]); // constructor to specify initial element values
        V() {}; // default constructor (initialize elements both to 0 for their type)

        const V<eltType, scalType, len> operator + (const V<eltType, scalType, len>&) const; // addition
        const V<eltType, scalType, len> operator * (const scalType&) const; // scalar multiplication
        const eltType operator[](int k) const; // access an element by index
        bool operator == (const V<eltType, scalType, len>&) const; // compare vectors of this type

        void print() const;

        const V<SymbCheck_Real, SymbCheck_Real, len> getSampleRest() const; // get sample vector in symbolic form - not done yet

    protected:
        eltType vect[len]; // elements of the vector

};

#endif


