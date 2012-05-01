#include <iostream>
#include <math.h>
using namespace std;

int** Allocate2D(int rows, int cols) {
    int** array = new int* [rows];
    for(int i = 0; i < rows; i++) {
        array[i] = new int[cols];
    }

    return array;
}

void Deallocate2D(int** &array, int rows, int cols) {
    for(int i = 0; i < rows; i++) {
        delete [] array[i];
    }
    delete [] array;
    array = NULL;
}

void knapsack(int** &partyFees, int partyBudget, int numParties) {
    int** solution = Allocate2D(numParties+1, partyBudget+1);
    /*
    for(int i = 0; i < numParties; i++) {
        for(int j = 0; j < 2; j++) {
            cout << partyFees[i][j] << " ";
        }
        cout << endl;
    }
    */

    for(int i = 0; i < partyBudget+1; i++) {
        solution[0][i] = 0;
    }
    for(int i = 1; i < numParties+1; i++) {
        solution[i][0] = 0;
    }

    for(int i = 1; i < numParties+1; i++) {
        for(int j = 1; j < partyBudget+1; j++) {
            //cout << "solution[i-1][j] " << solution[i-1][j] << "\t" << "solution[i-1][j-partyFees[i-1][0] + new " << (solution[i-1][j-partyFees[i-1][0]] + partyFees[i-1][1]) << endl;
            //cout << "j " << j << "\tpartyfees[i-1][0] " << partyFees[i-1][0] << endl;
            if(partyFees[i-1][0] <= j) {
                solution[i][j] = max(solution[i-1][j], solution[i-1][j-partyFees[i-1][0]] + partyFees[i-1][1]);
            }
            else {
                solution[i][j] = solution[i-1][j];
            }
        }
    }

    /*
    for(int i = 0; i < numParties+1; i++) {
        for(int j = 0; j < partyBudget+1; j++) {
            cout << solution[i][j] << " ";
        }
        cout << endl;
    }
    */

    //cout << "\n--------- Solution --------" << endl;
    int i = partyBudget;
    while(solution[numParties][i-1] == solution[numParties][i]) {
        i--;
    }
    cout << i << " " << solution[numParties][i] << endl;

    Deallocate2D(solution, numParties+1, partyBudget+1);
}

int main() {
    int partyBudget, numParties;
    cin >> partyBudget;
    cin >> numParties;

    int iteration = 1;
    while(partyBudget != 0 && numParties != 0) {

        int** partyFacts = Allocate2D(numParties, 2);

        //cout << "Iteration #" << iteration++ << "\n-------------------------" << endl;

        for(int i = 0; i < numParties; i++) {
            // first column is the party entrance fee, i.e. weight
            // second column is the party estimated fun value
            cin >> partyFacts[i][0];
            cin >> partyFacts[i][1];
            //cout << "i = " << i << ": " << partyFacts[i][0] << " " << partyFacts[i][1] << endl;
        }

        knapsack(partyFacts, partyBudget, numParties);

        Deallocate2D(partyFacts, numParties, 2);

        cin >> partyBudget;
        cin >> numParties;
    }
}
