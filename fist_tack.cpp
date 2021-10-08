#include <iostream>

using namespace std;

int main()
{
    double a, b;
    char operation;
    double value;
    
    while (true) {
        cin >> a >> operation >> b;
        
        switch (operation) 
        {
            case '+':
                value = a + b;
                break;
            case '-':
                value = a - b;
                break;
            case '*':
                value = a * b;
                break;
            case '/':
                if (b == 0) 
                {
                    cout << "Zero-div not supported!" << endl;
                    return 0;
                } 
                value = a / b;
                break;
            default:
                cout << "Undefined function." << endl;
        }
        cout << value << endl;
        
    }
    return 0;
}
