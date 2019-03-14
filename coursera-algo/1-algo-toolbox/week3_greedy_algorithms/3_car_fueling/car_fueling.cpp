#include <iostream>
#include <vector>

using std::cin;
using std::cout;
using std::vector;
using std::max;

int compute_min_refills(int dist, int tank, vector<int> & stops) {
    int n = stops.size();
    vector<int> all_points(n + 2);
    all_points[0] = 0;
    for (size_t i = 0; i < n; ++i) {
        all_points[i + 1] = stops.at(i);
    }
    all_points[n + 1] = dist;

    int num_refills = 0;
    int cur_refill = 0;

    while (cur_refill <= n) {
        int last_refill = cur_refill;
        while (cur_refill <= n && (all_points[cur_refill + 1] - all_points[last_refill] <= tank))
            ++cur_refill;

        if (cur_refill == last_refill)
            return -1;
        
        if (cur_refill <= n)
            ++num_refills;
    }

    return num_refills;
}


int main() {
    int d = 0;
    cin >> d;
    int m = 0;
    cin >> m;
    int n = 0;
    cin >> n;

    vector<int> stops(n);
    for (size_t i = 0; i < n; ++i)
        cin >> stops.at(i);

    cout << compute_min_refills(d, m, stops) << "\n";

    return 0;
}
