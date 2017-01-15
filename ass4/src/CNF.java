class CNF {

public String[] clauses;  // the clauses as strings, optional
public int[][] cl; // the clauses as lists of integers
public int C = 0; // count of clauses;
public int V = 0; // count of variables
CNF ( int n, NFA nfa ) {
	C=n*(nfa.T*2+nfa.Q*2+2+nfa.T)+nfa.Q*2-nfa.F.length-nfa.I.length;
	V=n+1;
	int[][]q=new int[nfa.Q][n+1];
	for(int i = 0; i < nfa.Q; i++)
		for(int j = 0; j <= n;j++){
			q[i][j] = V;
			V++;
		}
	
	int [][][]t=new int[n+1][2][nfa.Q];
	for(int j=0;j<nfa.Q;j++)
		for(int i=0;i<nfa.Q;i++)
			for(int a=0;a<2;a++)
			{
				if(nfa.trans[i][a][j]){
					for(int k=1;k<=n;k++){
						t[k][a][j]=V;
						//System.out.println(k+""+a+""+j);
						V++;
					}
					break;
				}
			}
				
	clauses=new String[C];
	cl=new int[C][nfa.T+2];
	int c=0;
	
	for(int k=1;k<=n;k++){
		//1
		for(int j=0;j<nfa.Q;j++)
			for(int a=0;a<2;a++)
				if(t[k][a][j]!=0){
					//System.out.println("t "+t[k][a][j]);
					int id = 0;
					cl[c][id]=-t[k][a][j];
					id++;
					if(a==0)
						cl[c][id]=-k;
					else
						cl[c][id]=k;		
					id++;	
					cl[c][id]=0;
					c++;
					id=0;
					cl[c][id]=-t[k][a][j];
					id++;
					cl[c][id]=q[j][k];
					id++;
					cl[c][id]=0;
					c++;
				}	
		//2
		for(int i=0;i<nfa.Q;i++){
			int id=0;
			cl[c][id]=-q[i][k-1];
			id++;
			for(int j=0;j<nfa.Q;j++)
				for(int a=0;a<2;a++)
					if(nfa.trans[i][a][j]){	
						cl[c][id]=t[k][a][j];
						id++;
					}
			cl[c][id]=0;
			c++;
		}
		//3
		for(int i=0;i<nfa.Q;i++){
			int id=0;
			cl[c][id]=-q[i][k];
			id++;
			for(int a=0;a<2;a++)
				for(int j=0;j<nfa.Q;j++)
					if(nfa.trans[j][a][i]){	
						cl[c][id]=t[k][a][i];
						id++;
						break;
					}
			cl[c][id]=0;
			c++;
		}
		//4
		for(int a=0;a<2;a++){
			int id=0;
			if(a==0)
				cl[c][id]=k;
			else
				cl[c][id]=-k;
			id++;
			for(int i=0;i<nfa.Q;i++)
				if(t[k][a][i]!=0){
					cl[c][id]=t[k][a][i];
					id++;
				}
			c++;
		}	
		//5
		for(int j=0;j<nfa.Q;j++)
			for(int a=0;a<2;a++){				
				int id=0;
				int flag=0;
				for(int i=0;i<nfa.Q;i++)
					if(nfa.trans[i][a][j]){
						if(flag==0){
						cl[c][id]=-t[k][a][j];
						id++;
						cl[c][id]=q[i][k-1];
						c++;
						}else{
							c--;
							id++;
							cl[c][id]=q[i][k-1];
							
						}
						flag=1;
					}	
			}
	}
	
	int flag=0;
	//6
	for(int i=0;i<nfa.Q;i++){
		for(int j=0;j<nfa.I.length;j++)
			if(i==nfa.I[j]){
				flag=1;
				break;
			}
		if (flag!=1){
			cl[c][0]=-q[i][0];
			c++;
		}
		flag=0;
	}
	for(int i=0;i<nfa.Q;i++){
		for(int j=0;j<nfa.F.length;j++)
			if(i==nfa.F[j]){
				flag=1;
				break;
			}
		if (flag!=1){
			cl[c][0]=-q[i][n];
			c++;
		}
		flag=0;
	}
	C=c;
	V--;
    // FOR YOU TO FILL IN
}

public CNF(int row, int col, NFA[] nfa) {
	for(int n=0;n<nfa.length;n++){
		C+=n*(nfa[n].T*2+nfa[n].Q*2+2+nfa[n].T)+nfa[n].Q*2-nfa[n].F.length-nfa[n].I.length;
	}
	V=row*col+1;
	int c=0;
	cl=new int[C][row>col?row:col];
	for(int n=0;n<nfa.length;n++){
		int in_n;
		if(n<row)in_n=col;
		else in_n=row;
		int[][]q=new int[nfa[n].Q][in_n+1];
		for(int i = 0; i < nfa[n].Q; i++)
			for(int j = 0; j <= in_n;j++){
				q[i][j] = V;
				V++;
			}
		int [][][]t=new int[in_n+1][2][nfa[n].Q];
		for(int k=1;k<=in_n;k++){
			for(int i=0;i<nfa[n].Q;i++)
				for(int j=0;j<nfa[n].Q;j++)
					for(int a=0;a<2;a++)
						if(nfa[n].trans[i][a][j]&&t[k][a][j]==0){
						
							t[k][a][j]=V;
							//System.out.println(k+""+a+""+j);
							V++;
							
						}
					
				
			}
		
		
		for(int k=1;k<=in_n;k++){
			//1
			for(int j=0;j<nfa[n].Q;j++)
				for(int a=0;a<2;a++)
					if(t[k][a][j]!=0){
						//System.out.println("t "+t[k][a][j]);
						int id = 0;
						cl[c][id]=-t[k][a][j];
						id++;
						if(a==0){
							if(n<row)cl[c][id]=-(k+n*10);
							else cl[c][id]=-((k-1)*10+n-row+1);
						}else{
							if(n<row)cl[c][id]=k+n*10;
							else cl[c][id]=(k-1)*10+n-row+1;	
						}
						id++;	
						cl[c][id]=0;
						c++;
						id=0;
						cl[c][id]=-t[k][a][j];
						id++;
						cl[c][id]=q[j][k];
						id++;
						cl[c][id]=0;
						c++;
					}	
			//2
			for(int i=0;i<nfa[n].Q;i++){
				int id=0;
				cl[c][id]=-q[i][k-1];
				id++;
				for(int j=0;j<nfa[n].Q;j++)
					for(int a=0;a<2;a++)
						if(nfa[n].trans[i][a][j]){	
							cl[c][id]=t[k][a][j];
							id++;
						}
				cl[c][id]=0;
				c++;
			}
			//3
			for(int i=0;i<nfa[n].Q;i++){
				int id=0;
				cl[c][id]=-q[i][k];
				id++;
				for(int a=0;a<2;a++)
					for(int j=0;j<nfa[n].Q;j++)
						if(nfa[n].trans[j][a][i]){	
							cl[c][id]=t[k][a][i];
							id++;
							break;
						}
				cl[c][id]=0;
				c++;
			}
			//4
			for(int a=0;a<2;a++){
				int id=0;
				if(a==0){
					if(n<row)cl[c][id]=(k+n*10);
					else cl[c][id]=((k-1)*10+n-row+1);
				}else{
					if(n<row)cl[c][id]=-(k+n*10);
					else cl[c][id]=-((k-1)*10+n-row+1);	
				}
				id++;
				for(int i=0;i<nfa[n].Q;i++)
					if(t[k][a][i]!=0){
						cl[c][id]=t[k][a][i];
						id++;
					}
				c++;
			}	
			//5
			
			for(int j=0;j<nfa[n].Q;j++)
				for(int a=0;a<2;a++){
					int id=0;
					int flag=0;
					for(int i=0;i<nfa[n].Q;i++)
						if(nfa[n].trans[i][a][j]){
							if(flag==0){
							cl[c][id]=-t[k][a][j];
							id++;
							cl[c][id]=q[i][k-1];
							c++;}
							else {
								c--;
								id++;
								cl[c][id]=q[i][k-1];
								c++;
							}
							flag=1;
						}
					}				
		}
		
		int flag=0;
		//6
		for(int i=0;i<nfa[n].Q;i++){
			for(int j=0;j<nfa[n].I.length;j++)
				if(i==nfa[n].I[j]){
					flag=1;
					break;
				}
			if (flag!=1){
				cl[c][0]=-q[i][0];
				c++;
			}
			flag=0;
		}
		for(int i=0;i<nfa[n].Q;i++){
			for(int j=0;j<nfa[n].F.length;j++)
				if(i==nfa[n].F[j]){
					flag=1;
					break;
				}
			if (flag!=1){
				cl[c][0]=-q[i][in_n];
				c++;
			}
			flag=0;
		}
		
	}
	C=c;
	V--;
}

public static void main ( String[] args ) {
// This is just for your testing purposes
   int n = Integer.parseInt( args[0] );
   int[] runs = {2};
   NFA nfa = new NFA( runs );
   CNF cnf = new CNF( n, nfa );
   System.out.println( "C = "+cnf.C );
   for (int c=0; c<cnf.C; ++c) {
      for (int i=0; i<cnf.cl[c].length; ++i){
         System.out.print( cnf.cl[c][i]+" " );
         if(cnf.cl[c][i]==0)break;
      }
      System.out.println();
   }
}

}