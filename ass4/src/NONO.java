import java.util.*;


class NONO {
	int R;
	int C;
	int vars=0;
	String row[];
	String col[];
	int [][]nono;
	NFA nfa[];
	public NONO(Scanner in) {
		R=in.nextInt();
		C=in.nextInt();
		nfa=new NFA[R+C];
		row=new String[R];
		col=new String[C];
		//System.out.println(in.next());
		in.nextLine();
		for(int i=0;i<R;i++)
			row[i]=in.nextLine();
		for(int i=0;i<C;i++)
			col[i]=in.nextLine(); 
		for(int i=0;i<row.length;i++){
			String str[] = row[i].split(" ");
			int runs[] = new int[str.length-1];  
			for(int j=1;j<str.length;j++){
				    runs[j-1]=Integer.parseInt(str[j]);
			}
			nfa[i] = new NFA( runs );
		}
		
		for(int i=0;i<col.length;i++){
			String str[] = col[i].split(" ");
			int runs[] = new int[str.length-1];  
			for(int j=1;j<str.length;j++){
				    runs[j-1]=Integer.parseInt(str[j]);
			}
			
			nfa[R+i] = new NFA( runs );
			
		}
	}


	public static void main ( String[] args ) {
		// This is just for your testing purposes
		   NONO nono = new NONO( new Scanner( System.in ) );
		   CNF cnf=new CNF(nono.R,nono.C,nono.nfa);
		   System.out.println("p cnf "+cnf.V+" "+cnf.C);
		   for (int c=0; c<cnf.C; ++c) {
			      for (int m=0; m<cnf.cl[c].length; ++m){
			         System.out.print( cnf.cl[c][m]+" " );
			         if(cnf.cl[c][m]==0)break;
			      }
			      System.out.println();
			   }
		   

		}

}
