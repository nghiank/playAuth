fout << variableName.size() << " " ;
for(int i = 0; i < variableName.size(); ++i) {
   preProcessing__(variableName[i]); 
   fout << variableName[i] << " \n"[i==(variableName.size()-1)];
}