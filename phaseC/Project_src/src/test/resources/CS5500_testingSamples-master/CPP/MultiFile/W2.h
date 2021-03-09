
#ifndef W2_H
#define W2_H

#ifndef V_H
#include "V.h"
#include "V.cpp"
#endif

/**
    Class to represent a vector from an 2D space with addition and scalar multiplication defined
    in a non-standard way.
        x1 + x2 = x1 + x2 + 1
        y1   y2   y1 + y2

    And:
            s * x = s * x + s - 1
                y   s * y

    This is a template where you can specify the element type and scalar type - but the dimension
    is fixed at 2.
    The base class is V.

    Note: instantiations of this class are immutable.
*/

template <typename eltType, typename scalType> class W2 : public V<eltType, scalType, 2> {

    public:
        W2(const eltType ar[2]) : V<eltType, scalType, 2>(ar) {}; // constructor to specify initial element values
        W2() : V<eltType, scalType, 2>() {}; // default no-arg constructor

        const W2<eltType, scalType> operator + (const W2<eltType, scalType>&) const; // addition
        const W2<eltType, scalType> operator * (const scalType&) const; // scalar multiplication

        const W2<SymbCheck_Real, SymbCheck_Real> getSampleRest() const; // get sample vector in symbolic form

};

#endif
