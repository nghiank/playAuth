if (variableName.empty()) {
	fout << "0 0" << endl;
} else {
	fout << variableName.size() << " " << variableName[0].size() << endl;	
}

for(int i = 0; i < variableName.size(); ++i) {
	for(int j = 0; j < variableName[i].size(); ++j) {
		preProcessing__(variableName[i][j]);
		fout << variableName[i][j] << " \n"[j==(variableName[i].size()-1)]; 
	}
	fout << endl;
}