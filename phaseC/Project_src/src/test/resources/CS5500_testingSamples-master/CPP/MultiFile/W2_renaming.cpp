
#include "W2_whitespace.h"

/*
    Addition of another vector to "this" vector - same type.  Non-standard addition defined:
        x1 + x2 = x1 + x2 + 1
        y1   y2   y1 + y2
*/
template <typename tempType, typename otherTempType> const W2<tempType, otherTempType> W2<tempType, otherTempType>::operator +
                                                                                (const W2<tempType, otherTempType>& v) const {
    W2<tempType, otherTempType> sum(this->vect);

    sum.vect[0] = sum.vect[0] + v.vect[0] + 1;
    sum.vect[1] = sum.vect[1] + v.vect[1];

    return sum;
}

/*
    Scalar multiplication of "this" vector by a scalar of specified type (otherTempType).  Non-standard operation defined:
        s * x = s * x + s - 1
                y   s * y

    Note: scalar appears after the vector in the operation i.e.
        vector * scalar --> OK
        scalar * vector --> not defined
*/
template <typename tempType, typename otherTempType> const W2<tempType, otherTempType> W2<tempType, otherTempType>::operator *
                                                                                (const otherTempType& s) const {

    W2<tempType, otherTempType> prod(this->vect);

    prod.vect[0] = prod.vect[0] * s + s + (-1);
    prod.vect[1] = prod.vect[1] * s;

    return prod;
}

/*
    Return a symbolic (i.e. type SymbCheck_Real) representation of what a vector belonging to this space looks like.
    This is to provide a restriction in order to check the closure axioms --> NOT DONE!
    This is a WORK IN PROGRESS.
*/
template <typename tempType, typename otherTempType> const W2<SymbCheck_Real, SymbCheck_Real> W2<tempType, otherTempType>::getSampleRest() const {
    SymbCheck_Real vs[2];
    int the_current_chaaaaracter = 65;
    for (int i = 0; i < 2; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(the_current_chaaaaracter));
        ++ the_current_chaaaaracter;
    }

    return W2<SymbCheck_Real, SymbCheck_Real>(vs);
}

