#include <cstring>
#include <vector>
#include <iostream>
#include<fstream>

using namespace std;

char tolower_char(char c) 
{
    return (c <= 'Z' && c >= 'A') ? c + 32 : c;
}

char toupper_char(char c) 
{
    return ('a' <= c && c <= 'z') ? c - 32 : c;
}

void write(vector<string> v)
{
	ofstream file;
	file.open("text.txt");
	for(int i=0; i < v.size(); i++)
	{
		file<<v[i]<<endl;
	}
	file.close();
}

void read(string file_name, vector<string> &v)
{
	ifstream file;
	file.open(file_name);
	string line;
	while (getline(file, line))
	{
		v.push_back(line);
	}
	file.close();
}

int main()
{
    string type;
    cin >> type;
    vector<string> strings;
    
    if (type == "file") 
    {
        string file_name;
        cin >> file_name;
        read(file_name, strings);
    }
    else
    {
        string s;
        
        while (true) {
            cin >> s;
            if (s == "stop") break;
            
            strings.push_back(s);
        }
    }
    
    for (string str : strings) 
    {
        for (auto & c: str) c = toupper_char(c);
        cout << str << endl;
        for (auto & c: str) c = tolower_char(c);
        cout << str << endl;
    }
    
    string concatinated = strings[0];
    for (int i = 1; i < strings.size(); i++) {
        concatinated += strings[i];
    }
    
    cout << concatinated << endl;
    
    write(strings);
    
    return 0;
}
