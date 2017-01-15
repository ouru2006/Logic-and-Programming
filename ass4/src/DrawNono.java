import java.util.Scanner;


public class DrawNono {
	static int R;
	static int C;
	String [][]x;
	
	
	public DrawNono(Scanner in) {
		String s=in.next();
		while(!s.equals("SATISFIABLE")){
			s=in.next();
		}

		x=new String[R][C];
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++){

				while(!in.hasNextInt()){
					in.next();
				}
				if(in.nextInt()<0)
					x[i][j]=".";
				else x[i][j]="#";
			}
			
		
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		String s="";
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				s+=x[i][j];
			}
			s+="\n";
		}
		return s;
	  // FOR YOU TO FILL IN
	}
	
	public static void main ( String[] args ) {
		// This is just for your testing purposes
		
		R=Integer.parseInt(args[0]);
		C=Integer.parseInt(args[1]);
		Scanner in = new Scanner( System.in );
		DrawNono dnono=new DrawNono(in);
		
		System.out.println(dnono);
			   
		   
		
	}

}