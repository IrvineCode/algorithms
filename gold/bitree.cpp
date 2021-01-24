#include <bits/stdc++.h>

using namespace std;

int BIT[1000], n;

void update(int x, int delta)       //add "delta" at index "x"
{
    for(; x <= n; x += x&-x)
          BIT[x] += delta;
}

int query(int x)
{
     int sum = 0;
     for(; x > 0; x -= x&-x)
         sum += BIT[x];
     return sum;
}

int main() {
    n = 5;
    
    int ar[] = {1, 2, 3, 0, 4};
    
    for(int i=0; i< 5; i++)
        update(i+1, ar[i]);
    
    cout << query(1) << "\n";   //
    cout << query(2) << "\n";   //
    cout << query(3) << "\n";   //
    cout << query(4) << "\n";   //
    cout << query(5) << "\n";   //
    
    update(2, 1);
    
    cout << query(1) << "\n";   //
    cout << query(2) << "\n";   //
    cout << query(3) << "\n";   //
    cout << query(4) << "\n";   //
    cout << query(5) << "\n";   //
}
