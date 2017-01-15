import java.util.*;



class NFA {

int Q; // number of states
int T; // number of transitions
boolean[][][] trans; // the transitions
int I[]; // initial states
int F[]; // final (=output) states

NFA( int[] runs ) {
   // FOR YOU TO FILL IN
	T=0;
	I=new int[1];
	F=new int[1];
	I[0]=0;
	for(int i=0;i<runs.length;i++){
		Q+=runs[i]+1;
		T+=runs[i]+2;	
	}
	F[0]=Q-1;
	trans = new boolean[Q][2][Q];
	int temp=0;
	int s=0;
	for(int i=0;i<runs.length;i++){
		trans[s][0][s] = true;
		s+=runs[i]+1;	
		for(int j=temp;j<s-1;j++)
			trans[j][1][j+1] = true;
		temp=s;
		if (s<Q)
			trans[s-1][0][s] = true;
	}
	trans[Q-1][0][Q-1]=true;
	
}

NFA( Scanner in ) {
   Q = in.nextInt();
   T = in.nextInt();
   int s = in.nextInt();
   I = new int[s];
   for (int i=0; i<s; ++i) I[i] = in.nextInt();
   s = in.nextInt();
   F = new int[s];
   for (int i=0; i<s; ++i) F[i] = in.nextInt();
   trans = new boolean[Q][2][Q];
   for (int i=0; i<T; ++i) 
      trans[in.nextInt()][in.nextInt()][in.nextInt()] = true;
}

public String toString() {
	String s="";
	s="Q = "+Q+"\n";
	s+="T = "+T+"\n";
	s+="F =";
	for(int i=0;i<I.length;i++)
		s+=" "+I[i];
	s+="\n";
	s+="O =";
	for(int i=0;i<F.length;i++)
		s+=" "+F[i];	
	for (int i=0; i<Q; i++)
		for(int k=0;k<Q;k++)
			for(int j=0;j<2;j++)
				if(trans[i][j][k])
					s+="\n("+i+","+j+","+k+")";
	return s;
  // FOR YOU TO FILL IN
}

public static void main ( String[] args ) {
// This is just for your testing purposes
   int[] runs = {2};
   NFA nfa = new NFA( runs );
   System.out.println( nfa );
   NFA nfas = new NFA( new Scanner( System.in ) );
   System.out.println( nfas );
}

}