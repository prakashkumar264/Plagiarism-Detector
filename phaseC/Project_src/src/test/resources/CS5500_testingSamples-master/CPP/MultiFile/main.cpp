#include <iostream>
#include "SymbCheck_Real.h"
#include <string>
#include <vector>
#include <sstream>
#include "VectorSpaceCheck.h"
#include "VectorSpaceCheck.cpp"
#include "W2.h"
#include "W2.cpp"

using namespace std;

std::vector<std::string> split(char delim, const std::string& toSplit) {
    std::vector<std::string> div;

    std::string temp = "";
    for (int i = 0; i < toSplit.size(); ++ i) {
        if (toSplit[i] != delim)
            temp += toSplit[i];
        if (toSplit[i] == delim || (i == toSplit.size() - 1 && temp != "")) {
            div.push_back(temp);
            temp = "";
        }
    }
    return div;
}

/*bool contains(const std::vector<std::string>& v, const std::string& check, int& index) {
    std::vector<std::string> checkSet = split('*', check);
    for (int i = 0; i < v.size(); ++ i) {
        if (v[i] == check) { // simple case
            index = i;
            return true;
        }
        std::vector<std::string> viSet = split('*', v[i]);

        if (checkSet.size() != viSet.size())
            continue;

        bool has = true;
        for (int k = 0; k < checkSet.size(); ++ k) {
            bool hasSet = false;
            for (int j = 0; j < viSet.size(); ++ j) {
                hasSet = (hasSet || (checkSet[k] == viSet[j]));
            }
            has = has && hasSet;
        }

        if (has) {
            index = i;
            return true;
        }
    }
    return false;
}

void simpVars(std::vector<std::string>& v, std::vector<int>& c) {
    // this is to remove duplicates.  the vector should contain a list of all the variables, as a set
    std::vector<std::string> vSet;
    std::vector<int> cSet;
    for (int i = 0; i < v.size(); ++ i) {
        int index = i; // index will be changed to the value of where the value is stored in vSet
        if (!contains(vSet, v[i], index)) {
            vSet.push_back(v[i]);
            cSet.push_back(c[i]);
        }
        else  // value is found at position index
            cSet[index] += c[i];
    }
    v = vSet;
    c = cSet;
}

std::vector<int> colRemCoeff(std::vector<std::string>& v) {
    std::vector<int> coeffs(v.size());

    for (int i = 0; i < v.size(); ++ i) {
        std::vector<std::string> vSplit = split('*', v[i]);
        int curCoeff = 1;
        std::string curVar = "";
        for (int j = 0; j < vSplit.size(); ++ j) {
            std::stringstream sstm(vSplit[j]);
            int val = 0;
            sstm >> val;
            if (sstm.fail()) {
                curCoeff *= 1;
                if (curVar != "")
                    curVar += "*";
                curVar += vSplit[j];
            }
            else
                curCoeff *= val;
        }
        coeffs[i] = curCoeff;
        if (curVar == "")
            curVar = "1";
        v[i] = curVar;
    }

    return coeffs;
}*/

template <typename T> void printVect(const vector<T>& t) {
    cout << "\n";
    for (int i = 0; i < t.size(); ++ i) {
        if (i != 0)
            cout << " + ";
        cout << t[i];
    }
}

void printV2(const V<SymbCheck_Real, SymbCheck_Real, 2> v) {
    cout << "\nstarting v";
    v[0].print();
    v[1].print();
}

int main()
{
    int vals[] = {1, 3};
    int vals2[] = {-1, 4};
    int scal = 5;

    V<int, int, 2> v1(vals), v2(vals2);

    v1.print();
    v2.print();

    (v1 + v2).print();

    (v1 * scal).print();

    cout << "\n\n";

    string s = "1+a+2*b*3+c*d*a*z+3";
    vector<string> rep = split('+', s);

    for (int i = 0; i < rep.size(); ++ i)
        cout << rep[i] << ", ";

    cout << "\n\n";

    string a = "1+3*c+d+e+-1*a";
    SymbCheck_Real scr(s), scr2(a);
    scr.print();
    scr2.print();
    (scr + scr2).print();

    cout << "\n\n";

   /* int index = 0;
    bool yes = contains(rep, "d*z*a*c", index);
    if (yes)
        cout << "here";
    else
        cout << "not here";

    cout << "\n";

    string sum = s + "+" + a;
    vector<string> sumV = split('+', sum);
    printVect(sumV);
    vector<int> coeffs = colRemCoeff(sumV);
    printVect(sumV);
    printVect(coeffs);
    simpVars(sumV, coeffs);
    printVect(sumV);
    printVect(coeffs);*/

//    printVect(vect);

    string t1 = "1+3*x";
    string t2 = "x+2*y";

    SymbCheck_Real T1(t1), T2(t2);

    cout << "\n\n\n";

    (T1 + T2).print();
    (T1 * T2).print();

    cout << "\n\n\n";

    SymbCheck_Real A("a"), B("b"), C("c"), D("d");
    SymbCheck_Real S("s");

    SymbCheck_Real v1scr[] = {A, B};
    SymbCheck_Real v2scr[] = {C, D};

    V<SymbCheck_Real, SymbCheck_Real, 2> v1s(v1scr), v2s(v2scr);

    printV2(v1s);
    printV2(v2s);

    printV2(v1s + v2s);

    printV2(v1s * S);

    cout << "\n\n\n";

    VectorSpaceCheck<W2<SymbCheck_Real, SymbCheck_Real>, 2> CHECK;

    bool ax1 = CHECK.checkAxiom1();
    if (ax1)
        cout << "Axiom 1 confirmed";
    else
        cout << "Axiom 1 disproved";

    bool ax2 = CHECK.checkAxiom2();
    if (ax2)
        cout << "\nAxiom 2 confirmed";
    else
        cout << "\nAxiom 2 disproved";

    bool ax3 = CHECK.checkAxiom3();
    if (ax3)
        cout << "\nAxiom 3 confirmed";
    else
        cout << "\nAxiom 3 disproved";

    bool ax4 = CHECK.checkAxiom4();
    if (ax4)
        cout << "\nAxiom 4 confirmed";
    else
        cout << "\nAxiom 4 disproved";

    bool ax5 = CHECK.checkAxiom5();
    if (ax5)
        cout << "\nAxiom 5 confirmed";
    else
        cout << "\nAxiom 5 disproved";

    bool ax6 = CHECK.checkAxiom6();
    if (ax6)
        cout << "\nAxiom 6 confirmed";
    else
        cout << "\nAxiom 6 disproved";

    bool ax7 = CHECK.checkAxiom7();
    if (ax7)
        cout << "\nAxiom 7 confirmed";
    else
        cout << "\nAxiom 7 disproved";

    bool ax8 = CHECK.checkAxiom8();
    if (ax8)
        cout << "\nAxiom 8 confirmed";
    else
        cout << "\nAxiom 8 disproved";

    return 0;
}
