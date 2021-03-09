
#include "VectorSpaceCheck.h"
#include "SymbCheck_Real.h"
#include <iostream>

/*
    Axiom 1: x + y == y + x, for x and y in the space
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom1() const {

    // The following comments apply to all functions in this file:
    // SymbCheck_Real v1s[len] --> create an array of type SymbCheck_Real of the same dimension as the space
    //                             ... these values will then be used to initialize the elements of vector v1
    // The for loop: initialize each element in the array of SymbCheck_Real to one value represented by consecutive
    // letters, starting at 'A' (done through conversion of integers as ASCII values to their corresponding characters)
    // Then, build LS (left-side) and RS (right-side) of the identity, and check for equality
    // NOTE: all addition/multiplication of vector elements, in addition to the equality check (LS == RS) is handled
    //       by the mechanics in the SymbCheck_Real class and it implementation

    SymbCheck_Real v1s[len], v2s[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        v1s[i] = SymbCheck_Real(static_cast<char>(curChar));
        v2s[i] = SymbCheck_Real(static_cast<char>(curChar + 1));
        curChar += 2;
    }

    VS v1(v1s), v2(v2s);

    VS LS = v1 + v2;
    VS RS = v2 + v1;

    return (LS == RS);
}

/*
    Axiom 2: (x + y) + z == x + (y + z), for x, y, and z in the space
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom2() const {
    SymbCheck_Real v1s[len], v2s[len], v3s[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        v1s[i] = SymbCheck_Real(static_cast<char>(curChar));
        v2s[i] = SymbCheck_Real(static_cast<char>(curChar + 1));
        v3s[i] = SymbCheck_Real(static_cast<char>(curChar + 2));
        curChar += 3;
    }

    VS v1(v1s), v2(v2s), v3(v3s);

    VS LS = (v1 + v2) + v3;
    VS RS = v1 + (v2 + v3);

    return (LS == RS);
}

/*
    Axiom 3: The zero vector exists such that x + 0 = x for all x in the space
    Here: find the 0 vector by x + (-1) * x
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom3() const {
    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    VS v(vs);

    VS z = v + v * (-1); // if 0 exists, then x + (-x) = 0
    // the zero vector is also unique (simple algebraic proof)
    // so, the zero vector must be an actual value, and not contain any variables
    bool isInt = true;
    for (int i = 0; i < len; ++ i)
        isInt = (isInt && z[i].isValue()); // check to make sure every element in 0 is a value

    return isInt;
}

/*
    Return the symbolic representation of the zero vector for this space.
    Here: find the 0 vector by x + (-1) * x
    Note: if axiom 3 is false (i.e. if there is no 0 vector), then, return NULL
*/
template <class VS, const int len> const VS VectorSpaceCheck<VS, len>::getZeroVector() const {
    if (!checkAxiom3())
        return NULL; // if the 0 vector does not exist, return NULL

    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    VS v(vs);

    return v + v * (-1);

}

/*
    Axiom 4: For every x belongs to the space, there exists y such that x + y = 0
    Here take y = (-1) * x
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom4() const {
    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    VS v(vs);

    return (v + v * (-1) == getZeroVector());

}

/*
    Axiom 5: s * (x + y) = s * x + s * y, for x, y in the space, and scalar s
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom5() const {
    SymbCheck_Real v1s[len], v2s[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        v1s[i] = SymbCheck_Real(static_cast<char>(curChar));
        v2s[i] = SymbCheck_Real(static_cast<char>(curChar + 1));
        curChar += 2;
    }

    SymbCheck_Real s(static_cast<char>(curChar));

    VS v1(v1s), v2(v2s);

    VS LS = (v1 + v2) * s;
    VS RS = v1 * s + v2 * s;

    return (LS == RS);
}

/*
    Axiom 6: (a + b) * x = a * x + b * x, for x in the space, and scalars a and b
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom6() const {
    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    SymbCheck_Real s1(static_cast<char>(curChar)), s2(static_cast<char>(++ curChar));

    VS v(vs);

    VS LS = v * (s1 + s2);
    VS RS = v * s1 + v * s2;

    return (LS == RS);
}

/*
    Axiom 7: (a * b) * x = a * (b * x), for x in the space, and scalars a and b
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom7() const {
    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    SymbCheck_Real s1(static_cast<char>(curChar)), s2(static_cast<char>(++ curChar));

    VS v(vs);

    VS LS = v * (s1 * s2);
    VS RS = (v * s1) * s2;

    return (LS == RS);
}

/*
    Axiom 8: 1 * x = x for all x in the space
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAxiom8() const {
    SymbCheck_Real vs[len];
    int curChar = 65;
    for (int i = 0; i < len; ++ i) {
        vs[i] = SymbCheck_Real(static_cast<char>(curChar));
        ++ curChar;
    }

    SymbCheck_Real s1(1);

    VS v(vs);

    VS LS = v * s1;
    VS RS = v;

    return (LS == RS);
}

/*
    Check all 8 axioms.
    This function returns true if all axioms are true (i.e. if the space is a vector space),
    and false otherwise.

    Note: when the closure axioms are implemented, they will also be checked in this function.
*/
template <class VS, const int len> bool VectorSpaceCheck<VS, len>::checkAllAxioms() const {
    return checkAxiom1() && checkAxiom2() && checkAxiom3() && checkAxiom4() && checkAxiom5()
            && checkAxiom6() && checkAxiom7() && checkAxiom8();
}
