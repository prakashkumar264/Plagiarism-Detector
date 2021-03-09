
#ifndef W2_H
#define W2_H

#ifndef V_H
#include "V.h"
#include "V_fct_swap.cpp"
#endif

/**
    Class to represent a vector from an 2D space with addition and scalar multiplication defined
    in a non-standard way.
    Here we go changing the comments
    The base class is V.

    Note: instantiations of this class are immutable. NEW COMMENTS NEW COMMENTS
*/






template <typename eltType, typename scalType> class W2 : public V<eltType, scalType, 2> 
{ // hello comments 

    // new comments, changing the whtespace too

    public:

        W2(const eltType ar[2]) : V<eltType, scalType, 2>(ar) 
                {}; // constructor to specify initial element values
            W2() : V<eltType, scalType, 2>() {}; // default no-arg constructor

        const W2<eltType, scalType> operator + (const W2<eltType, scalType>&) const; 
        const W2<eltType, scalType> operator * (const scalType&) const; // scalar multiplication

// get sample vector in symbolic form
        const W2<SymbCheck_Real,   SymbCheck_Real>      getSampleRest() const; 

};

#endif
