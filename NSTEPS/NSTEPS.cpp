#include <iostream>
using namespace std;

int main() {
    int numCases, x_coord, y_coord;

    cin >> numCases;

    for(int i = 0; i < numCases; i++) {
        cin >> x_coord >> y_coord;

        if(x_coord == y_coord && y_coord % 2 == 0) {
            cout << y_coord * 2 << endl;
        }
        else if(x_coord == y_coord && y_coord % 2 != 0) {
            cout << (y_coord * 2) - 1 << endl;
        }
        else if(x_coord == y_coord + 2 && y_coord % 2 == 0) {
            cout << (y_coord * 2) + 2 << endl;
        }
        else if(x_coord == y_coord + 2 && y_coord % 2 != 0) {
            cout << (y_coord * 2) + 1 << endl;
        }
        else {
            cout << "No Number" << endl;
        }
    }

    return 0;
}
