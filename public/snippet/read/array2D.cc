//////////// Declare variableName ///////////////
int m_variableName,n_variableName;
variableType variableName;
fin >> m_variableName >> n_variableName ;
variableName.resize(m_variableName);
for(int i = 0; i < m_variableName; ++i) {
	variableName[i].resize(n_variableName);
}

for(int i = 0; i < m_variableName; ++i) {
   for(int j = 0; j < n_variableName; ++j) {
       fin >> variableName[i][j];
	   postProcessing__(variableName[i][j]);       
   }
}