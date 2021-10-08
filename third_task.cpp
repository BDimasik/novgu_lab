#include <iostream>
#include <string>
#include <cstring>
#include <unordered_map>
 
using namespace std;

unordered_map<string, int> calc() 
{
    unordered_map<string, int> dictionary;
    char* ans;
	string s;
	getline(cin, s);
	char* cstr = &s[0];
	char* dim = strtok(cstr, " ");
	
	while (dim != NULL) 
	{
		dictionary[string(dim)]++;
		dim = strtok(NULL, " ");
	}
	return dictionary;
}
 
int main() 
{
    while (true) 
    {
    	unordered_map<string, int> dictionary = calc();
    	
    	int max = -1;
    	string maxS = "";
    	for (const auto& kv : dictionary) 
    	{
    	    if (kv.second > max) {
    	        max = kv.second;
    	        maxS = kv.first;
    	    }
        }
    
    	cout << maxS << endl;
    }
    return 0;
}
