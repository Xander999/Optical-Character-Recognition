
package xanderocr1;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;

class Node{					//defines the node structures
	double val ;	//  contains the real value of the node
                  double actval;	// contains the activating value of the node
	int typ;		// typ=  1 for inputLayer   2 for hidden layer  3 for output layer
                  double weight[];

		Node(int n){         			// n-->> number of outputs from each node.
		Random r=new Random();
		val=r.nextInt()%0.99;
		weight=new double[n];
		for(int i=0;i<n;i++){
		double m=r.nextDouble();
		System.out.println(m);
		weight[i]=m;
			}}
	}


class activat{

public static double sig(double x){   			 //function of tangential  implemented 
double  y=(1/(1+Math.exp(-x)));
return y;
}

public static double dsig(double x){			//derivative function of tangential function
double  y=Math.exp(-x)/(Math.pow((1+Math.exp(-x)),2));
return y;
}
}

class neural{
   public static double Neura(double x1,double x2,double x3,double x4, double target)throws IOException{
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

/*System.out.println("Enter the file name :");
String fil=br.readLine();
Scanner in = new Scanner (new File(fil));
*/

//System.out.println("Enter the number of hidden Layer :");
//int hf=Integer.parseInt(br.readLine());
int hf=25;

Node [] inputLayer=new Node[4];
Node [][]hiddenLayer=new Node[5][hf];
Node [] outputLayer=new Node[1];
Node  Final=new Node(0);

for(int i=0;i<4;i++){		//initialization of input layer
inputLayer[i]=new Node(5);
}

		//initialization of hidden layer
for(int ll=0;ll<hf;ll++){
for(int i=0;i<5;i++){
hiddenLayer[i][ll]=new Node(5);
if(ll==hf-1){
hiddenLayer[i][ll]=new Node(1);
}
}
}
outputLayer[0]=new Node(0);		//initialization of output layer

//---------------------------------Storage of Weights----------------------------------------------------------
/*
String fg="InL\n";
for(int i=0;i<4;i++){
for(int j=0;j<5;j++){
fg=fg+String.valueOf(inputLayer[i].weight[j])+" ";
}
fg=fg+"\n";
}
for(int k=0;k<hf-1;k++){
fg=fg+"HiL"+String.valueOf(k+1)+"\n";
for(int j=0;j<5;j++){
for(int i=0;i<5;i++){
fg=fg+String.valueOf(hiddenLayer[j][k].weight[i])+" ";
}
}
fg=fg+"\n";
}
fg=fg+"Hil"+String.valueOf(hf)+"\n";
for(int i=0;i<5;i++){
fg=fg+String.valueOf(hiddenLayer[i][hf-1].weight[0])+" ";
}
BufferedWriter out=new BufferedWriter(new FileWriter("P4weights.txt"));
out.write(fg);
out.newLine();
out.flush();
out.close();
*/
//-----------------------------------------Loading Of Weights---------------------------------

//System.out.println("Do u want to load weights  Press 1 to yes");
int ll=1;
if(ll==1){
String file="H:/P4weights.txt";
Scanner in = new Scanner (new File(file));


String st=in.nextLine();
st=in.nextLine();
Scanner s = new Scanner(st).useDelimiter("\\s");
st=in.nextLine();
Scanner s1=new Scanner(st).useDelimiter("\\s");
st=in.nextLine();
Scanner s2=new Scanner(st).useDelimiter("\\s");
st=in.nextLine();
Scanner s3=new Scanner(st).useDelimiter("\\s");
for(int i=0;i<5;i++){
double a=s.nextDouble();
double b=s1.nextDouble();
double c=s2.nextDouble();
double d=s3.nextDouble();
inputLayer[0].weight[i]=a;
inputLayer[1].weight[i]=b;
inputLayer[2].weight[i]=c;
inputLayer[3].weight[i]=d;
}

for(int k=0;k<hf-1;k++){
st=in.nextLine();
st=in.nextLine();
Scanner s4=new Scanner(st).useDelimiter("\\s");
for(int i=0;i<5;i++){
for(int j=0;j<5;j++){
hiddenLayer[i][k].weight[j]=s4.nextDouble();
}
}
}

st=in.nextLine();
st=in.nextLine();
Scanner s5=new Scanner(st).useDelimiter("\\s");
for(int i=0;i<5;i++){
hiddenLayer[i][hf-1].weight[0]=s5.nextDouble();
}
System.out.println("------Weights loaded successfully---------");
}

/*-------------------------Display Of Weights---(Initially)-----------------------------------------------------------------------------
System.out.println("Weights of Input Layer Weights are :\n");
for(int i=0;i<2;i++)
System.out.println(inputLayer[i].weight[0]+"   "+activat.sig(inputLayer[i].weight[0])+"\n"+inputLayer[i].weight[1]+"   "+activat.sig(inputLayer[i].weight[1])+"\n"+inputLayer[i].weight[2]+"   "+activat.sig(inputLayer[i].weight[0])+"\n\n");

System.out.println("Weights of Hidden Layer1 are:\n");
for(int j=0;j<3;j++)
System.out.println(hiddenLayer[j][0].weight[0]+"   "+activat.sig(hiddenLayer[j][0].weight[0])+"\n"+hiddenLayer[j][0].weight[1]+"   "+activat.sig(hiddenLayer[j][0].weight[1])+"\n"+hiddenLayer[j][0].weight[2]+"   "+activat.sig(hiddenLayer[j][0].weight[2])+"\n\n");

System.out.println("Weights of Hidden Layer2 are:\n");
for(int j=0;j<3;j++)
System.out.println(hiddenLayer[j][1].weight[0]+"   "+activat.sig(hiddenLayer[j][1].weight[0])+"\n\n");

-------------------------Display Of Weights-----------------------------------------------------------------------------*/

System.out.println("Providing Inputs----"+x1+" "+x2+" "+x3+" "+x4);
double a=x1;
double b=x2;
double c=x3;
double d=x4;
if(a==1)	inputLayer[0].val=0.998;
else if(a==0)	inputLayer[0].val=0.01;

if(b==1)  inputLayer[1].val=0.998;
else if(b==0)  inputLayer[1].val=0.01;

if(c==1)	inputLayer[2].val=0.998;
else if(c==0)	inputLayer[2].val=0.01;

if(d==1)	inputLayer[3].val=0.998;
else if(d==0)	inputLayer[3].val=0.01;

inputLayer[0].actval=activat.sig(inputLayer[0].val);
inputLayer[1].actval=activat.sig(inputLayer[1].val);
inputLayer[2].actval=activat.sig(inputLayer[2].val);
inputLayer[3].actval=activat.sig(inputLayer[3].val);

//System.out.println("Enter The Target Value :");
double tval=target;   //target value
double cval=0;			                  //calculated value
if(tval==0)	cval=1;			                 
int bkk=1;
while(Math.abs(tval-cval)>0.08){

//.........................................................................................Revisitation of value of Hidden Layers.......................................................................

//----------------------revision of val of hidden Layers---------------------
for(int i=0;i<5;i++){
for(int j=0;j<5;j++){
hiddenLayer[j][i].val=0;
}
}
//-----------------------revision of val of output Layer--------------------------------------
outputLayer[0].val=0;

//---------------------------------------------------------------------------------Forward Propagation---------------------------------------------------------------------
//---------------------------------  input Layer------>>>hidden Layer 1   ----------------------------------------------------------------
for(int j=0;j<5;j++){
for(int i=0;i<4;i++){
	hiddenLayer[j][0].val=hiddenLayer[j][0].val+(inputLayer[i].weight[j]*inputLayer[i].val);
}
hiddenLayer[j][0].actval=activat.sig(hiddenLayer[j][0].val);
//System.out.println(hiddenLayer[j][0].actval);
}
//--------------------------------   hiddenLayer n-------->>>hiddenLayer m  ------------------------------------------------------------------
for(int k=1;k<hf;k++){

for(int j=0;j<5;j++){
for(int i=0;i<5;i++){
hiddenLayer[j][k].val=hiddenLayer[j][k].val+(hiddenLayer[i][k-1].weight[j]*hiddenLayer[i][k-1].actval);
}
hiddenLayer[j][k].actval=activat.sig(hiddenLayer[j][k].val);
//System.out.println(hiddenLayer[j][k].actval);
}
}

//---------------------------------   hidden Layer----------->>>Output Layer   ----------------------------------------------------------------------
for(int i=0;i<5;i++){
outputLayer[0].val=outputLayer[0].val+(hiddenLayer[i][hf-1].actval*hiddenLayer[i][hf-1].weight[0]);   
}
double val=outputLayer[0].val;
outputLayer[0].actval=activat.sig(outputLayer[0].val);
cval=Math.abs(outputLayer[0].actval);
System.out.println("Final Calculated Output :"+outputLayer[0].actval);
if(cval>0.8 && cval<=1 && tval==0.9){  //D
System.out.println("D");
break;
}

if(cval>0.6 && cval<=0.8 && tval==0.7){  //A
System.out.println("A");
break;
}

if(cval>0.4 && cval<=0.6 && tval==0.5){  //B
System.out.println("B");
break;
}

if(cval>0.25 && cval<=0.4 && tval==0.3){ //C
System.out.println("C");
break;
}
//System.out.println("Press to continue for Back Propagation :");
//String aio=br.readLine();

//------------------------------------Back Propagation---------------------------------------------------
double t=tval-cval;
double delsum=activat.dsig(val)*t;
if(Double.isInfinite(delsum))   delsum=0.79;
else if(Double.isNaN(delsum))   delsum=0.12;
double delhidsum=0;

System.out.println("\n New Weights for Input Layer :");
for(int i=0;i<4;i++){
for(int j=0;j<5;j++){
delhidsum=(delsum/hiddenLayer[j][0].weight[j])*activat.dsig(hiddenLayer[j][0].val);
double dw=delhidsum/inputLayer[i].actval;
if(Double.isNaN(dw))  dw=0.1;
System.out.println("Old  :"+inputLayer[i].weight[j]+"   delhidsum="+delhidsum+"     dw="+dw+"      actval="+inputLayer[i].actval);
inputLayer[i].weight[j]=inputLayer[i].weight[j]+dw;
System.out.println("New  :"+inputLayer[i].weight[j]);
}
}

for(int k=0;k<hf-1;k++){
System.out.println("\n New Weights for Hidden "+(k+1)+" Layer :");
for(int i=0;i<5;i++){
for(int j=0;j<5;j++){
delhidsum=(delsum/hiddenLayer[i][k+1].weight[0])*activat.dsig(hiddenLayer[i][k+1].val);
double dw=delhidsum/hiddenLayer[i][k].actval;
if(Double.isNaN(dw))  dw=0.001;
System.out.println("Old  :"+hiddenLayer[i][k].weight[j]);
hiddenLayer[i][k].weight[j]=hiddenLayer[i][k].weight[j]+dw;
System.out.println("New  :"+hiddenLayer[i][k].weight[j]);
}
}
}

System.out.println("\n New Weights for Hidden "+(hf)+" Layer:");
for(int j=0;j<5;j++){
double dw=delsum/hiddenLayer[j][hf-1].actval;
if(Double.isNaN(dw))  dw=0.1;
System.out.println(hiddenLayer[j][hf-1].weight[0]+"   +   "+dw);
hiddenLayer[j][hf-1].weight[0]=hiddenLayer[j][hf-1].weight[0]+dw;
System.out.println("="+hiddenLayer[j][hf-1].weight[0]+"\n");
}	

//System.out.println("Press 1 to continue :");
//bkk=Integer.parseInt(br.readLine());

//---------------------------------Storage of Weights----------------------------------------
String fg1="InL:\n";
for(int i=0;i<4;i++){
for(int j=0;j<5;j++){
fg1=fg1+String.valueOf(inputLayer[i].weight[j])+" ";
}
fg1=fg1+"\n";
}
for(int k=0;k<hf-1;k++){
fg1=fg1+"HiL"+String.valueOf(k+1)+"\n";
for(int j=0;j<5;j++){
for(int i=0;i<5;i++){
fg1=fg1+String.valueOf(hiddenLayer[j][k].weight[i])+" ";
}
}
fg1=fg1+"\n";
}
fg1=fg1+"Hil"+String.valueOf(hf)+"\n";
for(int i=0;i<5;i++){
fg1=fg1+String.valueOf(hiddenLayer[i][hf-1].weight[0])+" ";
}

BufferedWriter out1=new BufferedWriter(new FileWriter("H:/P4weights.txt"));
out1.write(fg1);
out1.newLine();
out1.flush();
out1.close();

}
 return cval;
   }  
}

public class Practice6{  
public static void main(String args[])throws IOException{
     neural.Neura(0.8, 0.2, 0.33, 0.6, 0.5);
}
}