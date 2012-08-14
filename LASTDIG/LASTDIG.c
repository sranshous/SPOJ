#include <stdio.h>

int main() {
    int numCases, base, i, j, lastDig;
    int ldh; // to hold the last digit to keep multiplying by
    long index;

    scanf("%d", &numCases);

    for(i = 0; i < numCases; i++) {
        scanf("%d%ld", &base, &index);
        lastDig = base % 10;

        // number raised to 0 or 1
        if(index == 0) {
            printf("1\n");
            continue;
        }
        else if(index == 1) {
            printf("%d", lastDig);
            continue;
        }

        /*
         * Perform the multiplications by pulling out numbers
         * and dividing the index every time.
         * 4 ^ 20 -> (4 ^ 2) ^ 10 -> (6^2) ^ 5
         * Remember we only keep the last digit
         */
        j = 2;
        while(index > 1) {
            ldh = lastDig;

            // time saving cases
            if(lastDig == 0 || lastDig == 1) {
                break;
            }
            else {
                if(index % j == 0) {
                    index /= j;
                    for(; j > 1; j--) {
                        lastDig *= ldh;
                        lastDig %= 10;
                    }
                    j = 2;
                }
                else {
                    j++;
                }
            }
        }

        printf("%d\n", lastDig);
    }

    return 0;
}
