
package practica;
import java.util.*;
import java.io.*;
import java.text.*;

 class Practica {
	 
	static DecimalFormat df=new DecimalFormat("0.00"); 
	static Scanner sc=new Scanner(System.in);
	
	//citirea matricei din fisier
	 static int[][] citire() {
		  int n=0, m=0;
		 int [][]a=null;
		try {
	    Scanner filescan =new Scanner (new FileReader("Teren.in"));
	     while(filescan.hasNext()) {
	    //citirea dimensiunii matriciei
	     n=filescan.nextInt();
	     m=filescan.nextInt();
	     if(n<0 || m<0) throw new InputMismatchException();
	     a= new int[n][m];
	     for(int i=0; i<n; i++)
	    	 for(int j=0; j<m; j++) {
	    		//citirea elementelor matriciei
	    		 a[i][j]=filescan.nextInt();}
         } }
	   catch(FileNotFoundException ex) {System.out.println(" Fisier absent");}
	   catch(InputMismatchException ex) {System.out.println(" Matricea nu poate contine deimensiuni negative");}
		 return a;
	     }
	 
	 //afisarea matricei
static void afisare(int [][] a) 
	 {
	 for(int i=0; i<a.length; i++)
    	 {for(int j=0; j<a[i].length; j++)
    		 System.out.print(a[i][j]+" ");
    	System.out.println();}
	 } 
	 

	//inscrierea in fisierul Teren a matricei modificate
	static void scriere (int a[][])  {
		int n=a.length;
		int  m=a[0].length;
		try {FileWriter fw = new FileWriter("Teren.in"); 
		 fw.write(n+ " "+m+"\n"); //inscrierea dimensiunii noii matrici
		 for(int i=0; i<a.length; i++)
		  {for(int j=0; j<a[i].length; j++)
			   {   fw.write(a[i][j]+" ");}//inscrierea elementelor
			   fw.write("\r\n");}
			   
		  fw.close();} 
		catch (IOException ex) { System.out.println("Eroare de intrare/iesire");}
		}
	 
	
	//inserarea unui  rand(punctul 1)
	public static int[][] insertRow(int[][] m, int pozitia, int[] rand) 

	{ //cream o matrice noua care va stoca cu un rand mai mult decat matricea sursa
	//inseram toate randurile in matricea noua pana la indexul pozitiei noului rand inserat
	    int[][] out = new int[m.length + 1][];
	    for (int i = 0; i < pozitia; i++) {
	        out[i] = m[i];
	    }
	    //inseram randul pe pozitia aleasa 
	    //apoi sunt inerate randurile ramase(aflate dupa indexul pozitiei noului rand inserat)
	    out[pozitia] = rand;
	    for (int i = pozitia + 1; i < out.length; i++) {
	        out[i] = m[i - 1]; 
	    }
	    return out;
	}
	//inserarea unei coloane (punctul 1)
	 public static int[][] addColumn(int[][] a, int[] coloana, int pozitie) {
	       
		//cream o matrice noua care va stoca cu o coloana mai mult decat matrice sursa
		 int[][] result = new int[a.length][a[0].length + 1];
         //se va copia fiecare coloana din matricea sursa incepand cu indexul 0 
		 //pana la indexul pozitiei (unde va fi noua coloana)
	        for (int i = 0; i < a.length; i++) {
	            System.arraycopy(a[i], 0, result[i], 0, pozitie);
	      //inseram coloana pe pozitia aleasa
	            result[i][pozitie] = coloana[i];
	       //copiem coloanele ramase(aflate dupa indexul pozitiei noii coloane inserate)
	            System.arraycopy(a[i], pozitie, result[i], pozitie + 1, a[0].length - pozitie);
	        }

	        return result;
	    }
	 
	 //citirea unui rand
static	 int[] citim_rand(int [][] a)  {
	//numarul elementelor este egal cu nr. coloanelor din matricea in care va fi adaugat randul
	int b[]=new int[a[0].length] ;
	   for(int i=0; i<a[i].length; i++)
	 try{  {System.out.println("b["+i+"]=");
	   b[i]=sc.nextInt();
	   if(b[i]>1 && b[i]<0) throw new InputMismatchException(); }}
catch(InputMismatchException ex) {System.out.println("Randul nou din matrice poate contine doar valorile 0 sau 1"); }
	 
	   return b;
	
}
	 //citirea unei coloane
static int[] citim_coloana(int [][]a) {
	//numarul elementelor este egal cu nr. liniilor din matricea in care va fi adaugata coloana
	int b[]=new int[a.length] ;
	   for( int i=0; i<a.length; i++)
	  try{ {System.out.println("b["+i+"]=");
	   b[i]=sc.nextInt(); if(b[i]>1 || b[i]<0) throw new InputMismatchException(); }}
catch(InputMismatchException ex) {System.out.println("Coloana noua din matrice poate contine doar valorile 0 sau 1"); }

	 
	   return b;
	
}
	//inserarea unui rand/coloane marginal(e)
	static void inserare(int [][]a) {
		 //definim 2 vectori care vor stoca randul/coloana inserata
		int [] r;
		  int []c;		  
		 String g;

		 System.out.println("Ce dorim sa inseram coloana/rand");
		 try { g=sc.next();
		  if(g.contentEquals("rand")) {
	     System.out.println("Pozitia inserarii nord/sud");
	     String k=sc.next();  
	     if(k.contentEquals("nord")) {
		  r=citim_rand(a); a=insertRow(a,0,r);}
		 else  if(k.contentEquals("sud")) {
				  r=citim_rand(a); a=insertRow(a, a.length, r); }
		  }
		  else if(g.contentEquals("coloana")) {
	      System.out.println("Pozitia inserarii vest/est");
		  String k; k=sc.next();  
		  if(k.contentEquals("vest")) {
			  c=citim_coloana(a); a=addColumn(a,c,0); }
		  else if(k.contentEquals("est")) {
		 c=citim_coloana(a); a=addColumn(a,c, a[0].length);   }
	  } 
		  } catch(InputMismatchException ex) {System.out.println("Introducere valoare gresita"); }
		 System.out.println("_______________________________________________________________________" );
		 System.out.println("Afisarea matricei schimbate" );
		 afisare(a); scriere(a);
		 System.out.println("_______________________________________________________________________" );
		 } 
		  
	 //determinarea zonelor unui rand/coloana 
	 static void zone(int [][] a)  {
			
			System.out.println("Determinarea zonelor unui rand/coloana");
			try { String g=sc.next();
			 if(g.contentEquals("rand")) {		  
			System.out.println("Insearati numarul de ordine a randului de la "+1+" pana la "+a.length );
			int r=sc.nextInt();
			System.out.println("Randul ales este");
			for(int i=0; i<a.length; i++)
		    {for(int j=0; j<a[i].length; j++)
			{ if(i==r)
			//afisarea randului a carui nr de ordine coincide cu cel citit de la tastatura
			  System.out.print(a[r-1][j]+" ");} 
			} 
			 System.out.println();
			 System.out.println("Zonele");
			for(int j=0; j<a[0].length; j++)
						 {
				     if(a[r-1][j]==1) System.out.println("zona contine o mina ");
					 else if(a[r-1][j]==0) System.out.println("zona libera");
					 } 
					  
				  }
				  else  if(g.contentEquals("coloana")) {
					  System.out.println("Insearati numarul de ordine a coloanei "+1+" pana la "+a[0].length);
						 int r; r=sc.nextInt();
						 System.out.println("Coloana aleasa este");
						 for (int i=0; i<a.length; i++)
						 {for(int j=0; j<a[i].length; j++)
						 { if(j==r)
							//afisarea coloanei a carei nr de ordine coincide cu cel citit de la tastatura
							 System.out.print(a[i][r-1]+ " ");} 
						 }
						 System.out.println();
						 System.out.println("Zonele");
						 for(int i=0; i<a.length; i++) 
						 {if(a[i][r-1]==1) System.out.println("zona contine o mine");
						 else if(a[i][r-1]==0) System.out.println("zona libera"); }
		
		 } } catch(InputMismatchException ex) {System.out.println("Introducere valoare gresita");}
			catch(ArrayIndexOutOfBoundsException ex) {System.out.println("Nu exista asa index");}
		
			}
	
	
	//punctul 3 
	//numarul randului cu un numar minimal de zone
static void rand_min_mine(int [][] a)	{
	int n=a.length;
	int m=a[0].length;
	int [] row_arr = new int[n];
	int z=0;
    int row_ind=0;

	 for( int i=0; i<n; i++)
	 { int nr=0;
	     for(int j=0; j<m; j++)
		 {if(a[i][j]==1) {nr++;} }//se calculeaza nr. minelor de pe fiecare linie
		row_arr[z++]=nr; //se stocheaza in vector numarul minelor de pe fiecare linie
	 }
	 //presupunem ca prima linie are cel mai mic nr. de mine
	 //se compara cu nr. de mine de pe celelalte linii
	 //daca exista linie cu un nr. mai mic de mine, se salveaza nr. de mine si indexul liniei
	int temp_row=row_arr[0];
	for( int i=0; i<n; i++)
	{ if(temp_row>row_arr[i])
	{   temp_row=row_arr[i];
		row_ind=i;} }
	for( int i=0; i<n; i++)
	{ if(temp_row==row_arr[i])
		{temp_row=row_arr[i];
	row_ind=i;}
	else if(temp_row!=row_arr[i]) continue;
	System.out.println("Cele mai putine mine se afla pe linia "+(row_ind+1));
	}
}
    //punctul 3 
	//numarul coloanei  cu un numar minimal de zone
   static void coloana_min_mine(int [][] a)	{
	int n=a.length;
	int m=a[0].length;
    int [] col_arr = new int[m];
    int y=0;
    for(int j=0; j<m; j++)
    { int nr=0;
		 for( int i=0; i<n; i++)
	 { if(a[i][j]==1) {nr++;} }//se calculea nr de mine de pe fiecare coloana
	 col_arr[y++]=nr;} //se stocheaza in vector numarul minelor de pe fiecare coloana

    //presupunem ca prima coloana are cel mai mic nr. de mine
	 //se compara cu nr. de mine de pe celelalte coloane
	 //daca exista coloana cu un nr. mai mic de mine, se salveaza nr. de mine si indexul coloanei
    int temp_col=col_arr[0];
    int col_ind=0;
   for( int i=0; i<m; i++)
	{ if(temp_col>col_arr[i])
	{temp_col=col_arr[i];
		col_ind=i;}}
    for( int i=0; i<m; i++)
	{ if(temp_col==col_arr[i])
	{temp_col=col_arr[i];
		col_ind=i;}
	else if (temp_col!=col_arr[i]) continue;
	System.out.println("Cele mai putine mine se afla  pe coloana "+(col_ind+1));}}
   
	 //punctul 4
   //Media numerelor de zone minate pe coloanele pare
    static double average(int a[][]) {
	int n=a.length;
	int m=a[0].length;
	int [] nrpar = new int[m];
	 int h=0;
     for(int j=1; j<m; j+=2)
      { int nr=0;
		 for(int i=0; i<n; i++)
	 {if(a[i][j]==1) {nr++;} }//se calculeaza nr de mine de pe coloanele pare
	 nrpar[h++]=nr;  //se stocheaza in vector numarul minelor de pe fiecare coloana para
	 }
	int suma=0, c=0;
	for( int i=0; i<nrpar.length; i++)
	{if(nrpar[i]!=0) 
		{suma=suma+nrpar[i]; //se calculeaza numarul de zone minate
	      c++;}}// se calculeaza nr de coloane pare
	return (double)suma/c; }
	
	//punctul 5
    //lista numerelor de ordine ale liniilor terenului in ordinea ascendenta a numerelor de mine
   static void sortare(int [][] a) {
	int n=a.length;
	int m=a[0].length;
    int [] numar = new int[n];
    int r=0;
    int [] index= new int[n]; 
    int o=0;
    for( int i=0; i<n; i++)
     { int nr=0;
    for(int j=0; j<m; j++)
    {if(a[i][j]==1) {nr++;}}//se calculeaza nr de mine pe fiecare line
     numar[r++]=nr; //se stocheaza in vector numarul minelor de pe fiecare linie
      index[o++]=i+1;}   //se stocheaza in vector indexul  fiecarei linii
    int aux=0; int aix=0;
    //sortarea 
    for( int i=0; i<numar.length-1; i++)
    { int  iMin=i;//iMin-  idexul minim
    for(int v=i+1; v<numar.length; v++) 
    if(numar[v]<numar[iMin])//daca al doilea nr. e mai mic ca primul, acestea se vor interchimba
	 iMin=v;
	 aux=numar[iMin]; numar[iMin]=numar[i]; numar[i]=aux;
	 //interschimbarea indexului
	 aix=index[iMin]; index[iMin]=index[i]; index[i]=aix;  }
     for( int i=0; i<a.length; i++)
     System.out.println(index[i]);}

      //subpunctul 6
     //copierea in fisier liniile care nu au mine
     static void scriere_mine (int [][] a)  {
	try { FileWriter fw = new FileWriter("Mine.txt"); 
	for(int i=0; i<a.length; i++)
	{int nr=0;
	for(int j=0; j<a[i].length; j++)
    if(a[i][j]==0) {nr++;} //nr de mine de pe fiecare line
	int m=a[i].length;
	if(nr==m) //daca intreaga linie este fara mine se va inscrie in fisier
    for(int j=0; j<a[i].length; j++)
	{fw.write(a[i][j]+" ");}
    fw.write("\r\n");}  fw.close();} 
	catch (IOException ex) { System.out.println("Eroare de intrare/iesire");}
		}
	 
//determinarea daca exista macar un vecin (punctul 7)
 static int calcul(int[][] a, int i, int j, int current_count, int n, int m) { 
   	a[i][j] = 0; //elementul se va nota cu 0 pentru a nu fi vizitat de doua ori 
   	//daca exista o coordonata valida, se verifica daca aceasta are vecini
   	//daca extista cel putin un vecin(obiect) se va returna 1
   	if (verificare(i-1,j  , a.length, a[0].length) == 1 && a[i-1][j] == 1) {calcul(a,i-1,j  ,current_count,n,m); current_count = 1;}
   	if (verificare(i+1,j  , a.length, a[0].length) == 1 && a[i+1][j] == 1) {calcul(a,i+1,j ,current_count,n,m); current_count = 1;}
   	if (verificare(i,j-1, a.length, a[0].length) == 1 && a[i][j-1] == 1) {calcul(a,i, j-1,current_count,n,m); current_count = 1;}
   	if (verificare(i,j+1, a.length ,a[0].length) == 1 && a[i][j+1] == 1) {calcul(a,i ,j+1,current_count,n,m); current_count = 1;}
   	if (verificare(i+1,j-1, a.length, a[0].length) == 1 && a[i+1 ][j-1] == 1) {calcul(a,i+1  ,j-1,current_count,n,m); current_count = 1;}
   	if (verificare(i+1,j+1, a.length, a[0].length) == 1 && a[i+1][j+1] == 1) {calcul(a,i+1  ,j+1,current_count,n,m); current_count = 1;}
   	if (verificare(i-1,j-1, a.length, a[0].length) == 1 && a[i-1][j-1] == 1) {calcul(a,i-1  ,j-1,current_count,n,m); current_count = 1;}
   	if (verificare(i-1,j+1, a.length, a[0].length) == 1 && a[i-1][j+1] == 1) {calcul(a,i-1  ,j+1,current_count,n,m); current_count = 1;}
   	return current_count;}
 
   //verificarea daca coordonatele nu depasesc limitele matricei (punctul 7)
   static int verificare(int i, int j, int n, int m) {
   	if (i >= 0 && i <n && j >= 0 && j < m) 
   	{return 1;}
   	else {return 0;} }
   
   //subpunctul 7
 //determinarea numarului de obiecte   
   static int nr_obiecte(int [][] a) { 
    int n=a.length;
    int  m=a[0].length;
	int  count=0;
	int  current_count;
    for(int i=0; i<n; i++) {
	for(int j=0; j<m; j++) {
	   if (a[i][j] == 1) {
	      current_count = 0;  
	  	  count = count + calcul(a,i,j, current_count, n,m); }
	  	  }
	  	}	
	   return count;
   }
	 //meniul
	static void menu()  {
		Scanner sc=new Scanner(System.in);
		int [][] a;
		a=citire();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("           --------------------------------------------------");
		System.out.println("                       --------------------------");
		System.out.println("                                   Menu");
		System.out.println("                       --------------------------");
		System.out.println("           --------------------------------------------------");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println(" 1.Citirea matricei din fisier si afisarea acesteea ");
		System.out.println(" 2.Inscrierea in planul terenului un nou rand/colana marginala");
		System.out.println(" 3.Determinarea zonelor unui rand/unei coloane");
		System.out.println(" 4.Determinarea numarului liniei/cloanei cu un nr. minimal de zone");
		System.out.println(" 5.Determinarea mediei numerelor de zone minate de pe coloanele pare");
		System.out.println(" 6.Afisarea listei numerelor de ordine ale liniilor terenului in ordine ascendenta a numarului total de mine");
		System.out.println(" 7.Copierea liniilor care nu contin mine in alt fisier");
		System.out.println(" 8.Determinarea numarului de obiecte din matrice");
		System.out.println(" 9.Problama");
		System.out.println(" 0.Exit");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Alegerea dvs.");
		int v=0;
		try {
		v=sc.nextInt();}
		catch(InputMismatchException ex) { System.out.println("Valoare gresita"); menu();}
		switch(v) {
		case 0: break;
		case 1:System.out.println("_______________________________________________________________________" );
	    System.out.println("Matricea este:");afisare(a) ;
	    System.out.println("_______________________________________________________________________" ); break;
		case 2:System.out.println("_______________________________________________________________________" );
		inserare(a);break;
		case 3: System.out.println("_______________________________________________________________________" );
		zone(a); System.out.println("_______________________________________________________________________" );
		break;
		case 4:System.out.println("_______________________________________________________________________" );
		coloana_min_mine(a); 
		System.out.println("_______________________________________________________________________" );
		rand_min_mine(a);
		System.out.println("_______________________________________________________________________" );break;
		case 5:System.out.println("_______________________________________________________________________" );
		double l; l=average(a); System.out.println("Media este "+ df.format(l));
		System.out.println("_______________________________________________________________________" ); break;
		case 6: System.out.println("_______________________________________________________________________" );
		System.out.println("Lista numerelor de ordine a liniilor sortate:"); sortare(a); 
		System.out.println("_______________________________________________________________________" ); break;
		case 7:System.out.println("_______________________________________________________________________" );
		scriere_mine(a); System.out.println("Datele au fost inscrise"); 
		System.out.println("_______________________________________________________________________" ); break;
		case 8:System.out.println("_______________________________________________________________________" );
		int count=nr_obiecte(a); System.out.println("Numarul de obiecte din matrice e "+ count); 
		System.out.println("_______________________________________________________________________" );break;
		case 9: 
			CelMaiScurDrum.print(a);
			System.out.println("_______________________________________________________________________" );break;
		default: System.out.println("Ati introdus valoarea gresita");}
		System.out.println("Continuare true/false");
		boolean f = true;
		try {f=sc.nextBoolean();
		}catch(InputMismatchException ex) { System.out.println("Valoare gresita");}
		catch(NoSuchElementException ex) { System.out.println("Nu exista asa elemnt");}
		if(f) menu();
		sc.close();
		}		
	
	 public static void main(String[] args) 
	 { menu();}}
 

     class CelMaiScurDrum {
	 
	    static class Cell  {
	        int i;
	        int j;
	        int dist; //va memora distanta de la pozitia curenta la sursa
	        Cell prev; //va memora ultimul element vizitat
	        
	    //constructor parametrizat
	        Cell(int i, int j, int dist, Cell prev) {
	            this.i = i;
	            this.j = j;
	            this.dist = dist;
	            this.prev = prev;
	        }
	        //returneaza un string a obiectului specificat, in cazul nostru coordonatele elementelor din coada
	        public String toString(){
	        	return "("+(i+1)+ ","+(j+1)+")";}
	    }
	   
	    //afiseaza cel mai scurt drum
	    public static void print(int[][] a) {
		   if (a[0][0] ==1 || a[a.length-1][a[0].length-1] ==1)  return; //daca nu exista sursa sau destinatie iese din program
		   
	     Cell[][] cells = new Cell[a.length][a[0].length];  
	     for (int i = 0; i < cells.length; i++) {
	           for (int j = 0; j < cells[0].length; j++) {
	                if (a[i][j] != 1) { 
	                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null); //initializarea 
	                }
	            }
	        }
	 
	        LinkedList<Cell> queue = new LinkedList<>();
	        Cell src = cells[0][0]; //sursa
	        src.dist = 0;
	        queue.add(src); //adaugam sursa in coada
	        Cell dest = null; //va stoca destinatia daca va fi gasita
	        Cell curent; //pointer catre elementul curent
	        while ((curent = queue.poll()) != null) { 
	            if (curent.i==a.length-1 && curent.j == a[0].length-1) {
	                dest = curent; //daca am ajuns la destinatie orpim cautarea
	            }
	            visit(cells, queue, curent.i - 1, curent.j, curent);
	            visit(cells, queue, curent.i + 1, curent.j, curent);
	            visit(cells, queue, curent.i, curent.j - 1, curent);
	            visit(cells, queue, curent.i, curent.j + 1, curent);
	        }
	 
	        if (dest == null) {
	            return;
	        } else {
	            LinkedList<Cell> path = new LinkedList<>();
	            curent = dest; //recitim drumul anterior incepand cu destinatia 
	            do {
	                path.addFirst(curent); //salvam coordonatele drumului
	            } while ((curent = curent.prev) != null);
	            System.out.println(path);//afisarea
	        }
	    }
	 
	    static void visit(Cell[][] cells, LinkedList<Cell> queue, int i, int j, Cell parent) {
	        int dist = parent.dist + 1; //incrementam distanta
	        if (i < 0 || i >= cells.length || j < 0 || j >= cells[0].length || cells[i][j] == null) {
	            return; //daca coordonatele depasesc limitele matricei, iesire din functie
	        }
	        Cell curent = cells[i][j];
	        if (dist < curent.dist) {
	        	curent.dist = dist;
	        	curent.prev = parent; 
	        	queue.add(curent); //adaugam elementul valid in coada
	        }
	    }
 }
 
