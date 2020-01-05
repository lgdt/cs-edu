#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using std::string;
using std::vector;

int edit_distance(const string &str1, const string &str2) {
  vector<vector<int>> edits(str1.length() + 1, vector<int>(str2.length() + 1, 0));
  for (int k = 1; k <= str1.length(); k++) {
    edits[k][0] = edits[k - 1][0] + 1;
  }
  for (int k = 1; k <= str2.length(); k++) {
    edits[0][k] = edits[0][k - 1] + 1;
  }

  for (int i = 1; i <= str1.length(); i++) {
    for (int j = 1; j <= str2.length(); j++) {
      if (str1[i - 1] != str2[j - 1]) {
        edits[i][j] = 1 + std::min(edits[i - 1][j], std::min(edits[i - 1][j - 1], edits[i][j - 1]));
      } else {
        edits[i][j] = edits[i - 1][j - 1];
      }
    }
  }

  return edits[str1.length()][str2.length()];
}

int main() {
  string str1;
  string str2;
  std::cin >> str1 >> str2;
  std::cout << edit_distance(str1, str2) << std::endl;
  return 0;
}
