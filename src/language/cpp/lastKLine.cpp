public void printLastKLine(string file){
	int size = K, pos,end;
	string last[size];
	int ind = 0;
	wile(file.good()){
		getline(file,last[ind % K]);
		++ind;
	}
	if(ind < K){
		pos = 0;
		size = ind;
	}else{
		pos = ((ind-1) % K + 1) % K;
		end = (ind-1) % K;
	}
	
	for(i=0;i<size;++i){
	// no printf("%s", string), because it needs null "\0" terminator
		cout<<last[(pos+i) % K]<<endl; 
	}
}