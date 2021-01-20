#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <queue>
#include <deque>
#include <bitset>
#include <iterator>
#include <list>
#include <stack>
#include <map>
#include <set>
#include <functional>
#include <numeric>
#include <utility>
#include <limits>
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#define MAXN 100005
#define ll long long
#define MP make_pair
using namespace std;
typedef pair<int, int> pii;

int n, m;
vector<pii> adj[MAXN];
ll dist[MAXN];

int main()
{
  cin >> n >> m;

  int u, v, w;
  for (int i = 0; i < m; i++)
  {
    cin >> u >> v >> w;
    adj[u].push_back(make_pair(v, w));
    adj[v].push_back(make_pair(u, w));
  }
  fill(dist, dist + n + 1, -1);

  // dijkstra
  priority_queue<pii, vector<pii>, greater<pii>> pq;
  dist[1] = 0;
  pq.push(MP(0, 1));

  while (pq.empty() == false)
  {
    int u = pq.top().second;
    pq.pop();

    for (auto next : adj[u])
    {
      int v = next.first;
      int w = next.second  + dist[u];

      if (dist[v] == -1 || dist[v] > w)
      {
        dist[v] = w;
        pq.push(MP(w, v));
      }
    }
  }

  for (int v = 1; v <= n; v++)
  {
    cout << dist[v] << " " ;
  }
  cout << "\n";
  


}
