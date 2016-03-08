includeHeader

userFunctionSolution

template<typename T>
void postProcessing__(T& s) {}

void postProcessing__(string& s) {
    s.erase(s.begin());
    s.erase(s.end() - 1);
}

template<typename T>
void preProcessing__(T& s) {}

void preProcessing__(string& s) {
    s = "\"" + s;
    s += "\"";
}
 
int main(int argc, char* argv[]) {
    //cout << "Hello world" << endl;
    if (argc < 2) return 0;
    string fileName = argv[1];
    //cout << "filename=" << fileName << endl;

    ifstream fin(fileName.c_str());
    string outputFileName = fileName + ".out";
    ofstream fout(outputFileName);
    try{
      
        readAndDeclare
        
        callUserSolution
        
        writeUserOutput            
        
    } catch(...) {
      fin.close();
      fout.close();
    }    
    return 0;

}

