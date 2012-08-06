#include <iostream>
#include <algorithm>
using namespace std;

void findIt(int*, int);

int main(void) {
    int numCases, numInts;
    int* numbers;
    cin >> numCases;

    for(int i = 0; i < numCases; i++) {
        cin >> numInts;
        numbers = new int[numInts];
        for(int j = 0; j < numInts; j++) {
            cin >> numbers[j];
        }
        findIt(numbers, numInts);
    }
}

void findIt(int* numbers, int length) {
    int* sums = new int[length+1];
    int largest = -100000, count = 0;

    // finds the largest subsequence
    for(int i = 1; i < length+1; i++) {
        sums[i] = max(sums[i-1] + numbers[i-1], numbers[i-1]);

        if(sums[i] > largest)
            largest = sums[i];
    }

    // find how many times a sequence of that max sum occurs
    for(int i = 1; i < length+1; i++) {
        if(sums[i] == largest)
            count++;
        if(i != 1 && sums[i] == largest && sums[i-1] == 0)
            count++;
    }

    cout << largest << " " << count << endl;
}
