//////////// Declare variableName ///////////////
int sz_variableName;
variableType variableName; 
fin >> sz_variableName;
variableName.resize(sz_variableName);
for(int i = 0; i < sz_variableName; ++i) {
   fin >> variableName[i];   
   postProcessing__(variableName[i]);
}