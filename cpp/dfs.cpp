#include<bits/stdc++.h> 
using namespace std; 

list<int> *adj;
bool *visited;

void dfs(int u) {
    printf("--> %d ", u);
    for(int v : adj[u]) {
        if (!visited[v]) {
            visited[v] = true;
            dfs(v);
        }
    }
} 

int main() {
		ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    // input processing
    int V, E;
    cin >> V;
    cin >> E;
    
    adj = new list<int>[V];
    visited = new bool[V];
    fill(visited, visited+V, false);
    
    for(int i = 0; i < E; i++) {
        int a, b;
        cin >> a;
        cin >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    
    visited[0] = true;
    dfs(0);
}
