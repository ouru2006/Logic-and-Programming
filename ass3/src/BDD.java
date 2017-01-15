import java.util.*;
class BDD {
private int n;
private int s;
private int []v ;
private int []l ;
private int []h ; 
// YOUR OWN VARIABLES, CLASSES, ETC.

   BDD ( Scanner in ) {
	   n=in.nextInt();
	   s=in.nextInt(); //number of nodes.
	   v = new int [s];
	   l = new int [s];
	   h = new int [s];
	   for(int k=s-1;k>=0;k--){
		   v[k]=in.nextInt();
		   l[k]=in.nextInt();
		   h[k]=in.nextInt();
	   }
   // READS IN A BDD
   }

   long countBDD () {
	   int []c=new int[s];
	   c[0]=0;
	   c[1]=1;
	   for(int k=2;k<s;k++){
		   int L=l[k];
		   int H=h[k];  
		   c[k]=(int) (Math.pow(2,v[L]-v[k]-1)*c[L]+Math.pow(2, v[H]-v[k]-1)*c[H]);
	   }
	   return (long) (Math.pow(2,v[s-1]-1)*c[s-1]);
   // FOR YOU TO CODE
   // Counts the number of solutions
   }

   void List(int []x,int j,int p){
	   if(v[p]>j){
		   x[j]=0;
		   List(x,j+1,p);
		   
		   x[j]=1;
		   List(x,j+1,p);
	   }
	   else if(p==1){
		   
		   for(int i=1;i<=n;i++)
			   System.out.print(x[i]);
		   System.out.println();
	   }
	   else{
		   x[j]=0;
		   if(l[p]!=0)
			   List(x,j+1,l[p]);
		   x[j]=1;
		   if(h[p]!=0)
			   List(x,j+1,h[p]);
	   }
   }
   
   void listBDD () {
	   int []x=new int[n+1];
	   List(x,1,s-1);		   
   // FOR YOU TO CODE
   // Outputs all solutions, one per line, in format of example below
   }
   void countPoly(int[]a,int []x,int j,int p){
	   if(v[p]>j){
		   x[j]=0;
		   countPoly(a,x,j+1,p);
		   
		   x[j]=1;
		   countPoly(a,x,j+1,p);
	   }
	   else if(p==1){
		   int count=0;
		   for(int i=1;i<=n;i++)
			   count+=x[i];
		   a[count]++;
	   }
	   else{
		   x[j]=0;
		   if(l[p]!=0)
			   countPoly(a,x,j+1,l[p]);
		   x[j]=1;
		   if(h[p]!=0)
			   countPoly(a,x,j+1,h[p]);
	   }
   }
   int[] polyBDD () {
	   int []x=new int[n+1];
	   int []a=new int[n+1];
	   for(int i=0;i<=n;i++)
		   a[i]=0;
	   countPoly(a,x,1,s-1);
	   return a;
   // FOR YOU TO CODE
   // Outputs the coefficients of the generating function (a polynomial)
   // E.g., (19) in the text.
   }

   int[] maxBDD ( int[] w ) {
	   int []W=new int[n+2];
	   int []x=new int[n+1];
	   W[n+1]=0;
	   for(int j=n;j>=1;j--){
		  W[j]=W[j+1]+(w[j]>0?w[j]:0);
	   }
	   int []m= new int[s];
	   int []t= new int[s];
	   m[1]=0;
	   for(int k=2;k<s;k++){
		   int V=v[k];
		   int L=l[k];
		   int H=h[k];
		   t[k]=0;
		   if(L!=0){
			   m[k]=m[L]+W[V+1]-W[v[L]];
		   }
		   //int M=0;
		   if(H!=0){
			 int  M=m[H]+W[V+1]-W[v[H]]+w[V];
			   if(L==0 || M>m[k]) {
				   m[k]=M;
				   t[k]=1;
			   }
		 }		   
	   }
	   int j=0;
	   int k=s-1;
	   while(j<n){
		   while (j<v[k]-1){
			   j=j+1;
			   x[j]=(w[j]>0?1:0);
			   
			   }
		   	
		   if(k>1){
			   j=j+1;
			   x[j]=t[k];
			   k=(t[k]==0?l[k]:h[k]);
			   //System.out.println(x[j]);
		   }
	   }
	   x[0]=0;
	   for(int i=1;i<=n;i++){
		   x[0]=x[0]+x[i]*w[i];
	   }
	   return x;
   // FOR YOU TO CODE
   // Maximizes a linear function, put the max value at index location 0.
   }

   // YOU CAN OMIT THIS MAIN
   // But illustrates how we might test your code
   public static void main (String[] args) {
      Scanner in = new Scanner( System.in );
      BDD bdd = new BDD( in );

      System.out.println( "count = "+bdd.countBDD() );

      int[] w = {0,1,-2,-3,4};
      int[] x = bdd.maxBDD( w );
      System.out.print( "max = "+x[0]+" soln = " );
      for (int i=1; i<=bdd.n; ++i) 
    	  System.out.print( x[i]+" " );

      System.out.println( "\nlisting: " );
      bdd.listBDD();

      System.out.print( "polynomial: " );
      int[] a = bdd.polyBDD();
      for (int i=0; i<=bdd.n; ++i) 
    	  System.out.print( a[i]+" " );
      System.out.println();
   }

}