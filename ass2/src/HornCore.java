import java.util.*;  // For scanner

// Any additional classes can go here.
@SuppressWarnings("unchecked")
public class HornCore {
	int p;
	LinkedList<Integer>[]LAST;
	int[] TRUTH;
	LinkedList<Integer>S=new LinkedList<Integer>();
	boolean UNSAT;
// Variables, methods of your own can be added.

	HornCore (Scanner in) { // FOR YOU TO CODE.
		in.next();
		in.next();
		int v=in.nextInt();
		int c=in.nextInt();
		
		int []COUNT=new int[c+1];
		int []CONCLUSION=new int[c+1];
		TRUTH=new int[v+1];
		for(int i=0;i<TRUTH.length;i++) TRUTH[i]=0;
		LAST=new LinkedList[v+1];
		for (int i = 0; i <= v; i++) 
            LAST[i]=new LinkedList<Integer>();
		int k=0;
		while(in.hasNextInt()){		
			int temp=in.nextInt();
			if(temp<0){
				k++;				
				LAST[-temp].push(c);
				}
			else if(temp>0) CONCLUSION[c]=temp;
			else {
				if (k == 0 && TRUTH[CONCLUSION[c]] == 0){
					TRUTH[CONCLUSION[c]] = 1;
					S.add(CONCLUSION[c]);	
				}
				COUNT[c] = k;k=0;c--;
			}
					
		} 
		
		while (!S.isEmpty()){ 
		   p = S.pop();
		   
		   LinkedList<Integer>h = LAST[p];
		   while (!h.isEmpty()) {
		      c = h.pop();
		      if(COUNT[c]==1 && CONCLUSION[c]==0)	{
		    	  UNSAT=true;
		    	  return;
		      }
		      COUNT[c]--;
		      if (COUNT[c] == 0	&& CONCLUSION[c]!=0){
		         p = CONCLUSION[c];
		         if (TRUTH[p] == 0) {
		            TRUTH[p] = 1; 
		            S.add(p); 
		         }
		      }
		    	  
		   }
		}
	}

	public String getCore() {
		if(UNSAT)return "UNSAT";
		boolean empty=true;
		String out="";
		for(int i=0;i<TRUTH.length;i++){
			if(TRUTH[i]==1){
				out+=(i+" ");
				empty=false;
			}
		}
		if(empty)return "EMPTY";
		else return out;
		
	}

// MAIN CAN BE OMITTED.
	public static void main ( String[] none ) {
		Scanner in = new Scanner( System.in );
		HornCore hc1 = new HornCore( in );
		System.out.println( "Core = "+hc1.getCore() );
		HornCore hc2 = new HornCore( in );
		System.out.println( "Core = "+hc2.getCore() );
	}
}