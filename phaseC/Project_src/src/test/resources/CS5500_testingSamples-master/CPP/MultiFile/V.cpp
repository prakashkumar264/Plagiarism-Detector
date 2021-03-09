
#include "V.h"
#include <iostream>

/*
    Constructor to initialize elements to specified initial values.
*/
template <typename eltType, typename scalType, const int len> V<eltType, scalType, len>::V(const eltType ar[len]) {
    for (int i = 0; i < len; ++ i)
        vect[i] = ar[i];
}

/*
    Return the element from the specified index.
*/
template <typename eltType, typename scalType, const int len> const eltType V<eltType, scalType, len>::operator[](int k) const {
    return vect[k];
}

/*
    Addition of another vector to "this" vector - same type.  Defined as standard element-wise addition.
*/
template <typename eltType, typename scalType, const int len> const V<eltType, scalType, len> V<eltType, scalType, len>::operator +
                                                                                (const V<eltType, scalType, len>& v) const {

    V<eltType, scalType, len> sum(vect);

    for (int i = 0; i < len; ++ i)
        sum.vect[i] = sum.vect[i] + v.vect[i];

    return sum;
}

/*
    Scalar multiplication of "this" vector by a scalar of specified type (scalType).  Defined as standard element-wise
    propagation of the scalar.  Note: scalar appears after the vector in the operation i.e.
        vector * scalar --> OK
        scalar * vector --> not defined
*/
template <typename eltType, typename scalType, const int len> const V<eltType, scalType, len> V<eltType, scalType, len>::operator *
                                                                                (const scalType& s) const {

    V<eltType, scalType, len> prod(vect);

    for (int i = 0; i < len; ++ i)
        prod.vect[i] = prod.vect[i] * s;

    return prod;
}

/*
    Print the current state of the vector (element-wise).
    Note: only works in cases where eltType has << defined.
*/
template <typename eltType, typename scalType, const int len> void V<eltType, scalType, len>::print() const {
    std::cout << "\n";
    for (int i = 0; i < len; ++ i)
        std::cout << vect[i] << "  ";
}

/*
    Check if "this" vector is equal to another vector (equal if every element is the same, for each index).
*/
template <typename eltType, typename scalType, const int len> bool V<eltType, scalType, len>::operator == (const V<eltType, scalType, len>& v) const {
    bool isEqual = true;
    for (int i = 0; i < len; ++ i)
        isEqual = (isEqual && (vect[i] == v[i]));
    return isEqual;
}

/*
    Return a symbolic (i.e. type SymbCheck_Real) representation of what a vector belonging to this space looks like.
    This is to provide a restriction in order to check the closure axioms --> NOT DONE!
    This is a WORK IN PROGRESS.
*/
template <typename eltType, typename scalType, const int len> const V<SymbCheck_Real, SymbCheck_Real, len> V<eltType, scalType, len>::getSampleRest() const {
    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    return V<SymbCheck_Real, SymbCheck_Real, len>(vs);
}
