#include <stdio.h>

#define y2 y_coord*2

int main() {
    int numCases, x_coord, y_coord, i;

    scanf("%d", &numCases);

    for(i = 0; i < numCases; i++) {
        scanf("%d%d", &x_coord, &y_coord);

        if(x_coord == y_coord && y_coord % 2 == 0) {
            printf("%d\n", y2);
        }
        else if(x_coord == y_coord && y_coord % 2 != 0) {
            printf("%d\n", y2-1);
        }
        else if(x_coord == y_coord + 2 && y_coord % 2 == 0) {
            printf("%d\n", y2+2);
        }
        else if(x_coord == y_coord + 2 && y_coord % 2 != 0) {
            printf("%d\n", y2+1);
        }
        else {
            printf("No Number\n");
        }
    }

    return 0;
}
