#include <bits/stdc++.h>
using namespace std;

int n = 7;
int t[4 * 7];

// inclusive
void build(int ar[], int i, int left, int right)
{
  if (left == right)
  {
    t[i] = ar[i];
    return;
  }

  int mid = (left + right) / 2;
  build(ar, i * 2, left, mid);
  build(ar, i * 2 + 1, mid + 1, right);
  t[i] = t[i * 2] + t[i * 2 + 1];
}

void update(int i, int left, int right, int pos, int new_val)
{
  if (left == right)
  {
    t[i] = new_val;
    return;
  }

  int mid = (left + right) / 2;
  if (pos <= mid)
    update(i * 2, left, mid, pos, new_val);
  else
    update(i * 2 + 1, mid + 1, right, pos, new_val);
  t[i] = t[i * 2] + t[i * 2 + 1];
}

int query_sum(int i, int left, int right, int l, int r)
{
  if (l > r)
    return 0;
  if (l == left && r == right)
    return t[i];

  int mid = (left + right) / 2;
  return query_sum(i * 2, left, mid, l, min(r, mid)) + query_sum(i * 2 + 1, mid + 1, right, max(l, mid + 1), r);
}

int main()
{
  int ar[7] = {1, 4, 2, 9, 3, 3, 5};

  build(ar, 1, 0, 7);
  update(1, 0, 7, 2, 10);
}
