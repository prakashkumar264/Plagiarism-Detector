
#ifndef VECTOR_SPACE_CHECK_H
#define VECTOR_SPACE_CHECK_H

/**
    Class representing a mechanism to check the vector space axioms for a given space.
    On creating an instantiation of this class, provide a space and the dimension of this
    space (it's a template).
    Then, the 8 axioms can be checked.

    Note: the space provided should be of element type SymbCheck_Real so the axioms can be
    checked algebraically.

    Note: the closure axioms are a work-in-progress.
*/

template <class VS, const int len> class VectorSpaceCheck {

    public:

        // no constructor b/c default one is ok (this class has no datafields)

        // check closure axioms  -- not done yet!
        bool checkClosure1() const;
        bool checkClosure2() const;

        // check axioms
        bool checkAxiom1() const;
        bool checkAxiom2() const;
        bool checkAxiom3() const;
        bool checkAxiom4() const;
        bool checkAxiom5() const;
        bool checkAxiom6() const;
        bool checkAxiom7() const;
        bool checkAxiom8() const;

        bool checkAllAxioms() const; // check all axioms -- returns true if all are true, false otherwise

        const VS getZeroVector() const; // returns NULL if the 0 vector doesn't exist (otherwise, symbolic)
};

#endif
