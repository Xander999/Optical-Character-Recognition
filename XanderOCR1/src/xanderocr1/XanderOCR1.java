

package xanderocr1;
import java.awt.Graphics2D;
import java.io.*;
/**
 *
 * @author Xander
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
  import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class XanderOCR1{
    public static void resize(String inputImagePath,String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

   
  public static void main(String args[])throws IOException{
   BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
   System.out.println("--------------Welcome-----------------");
   int tt=1;
   int v[]=new int[16];
       for(int i=0;i<16;i++) v[0]=0;
       
       //System.out.print(activat.sig(6));
       //Practice6.main(new String[]{"a","b"});
       
   while(tt==1){
   System.out.println("Enter 1 to train the neural\nEnter 2 to detect a image file\nEnter any other to exit.");
   int choice=Integer.parseInt(br.readLine());
   if(choice==1){
       System.out.println("Trainig begins :");
       
   }
   else if(choice==2){
       System.out.println("Enter the name of file in InputNEU Folder");
       String st=br.readLine();
       
       String input = "E:/photo/InputNEU/"+st;
      // System.out.println("File is read already");
      Mat src = Imgcodecs.imread(input);
      //Creating the empty destination matrix
      Mat dst = new Mat();
      //Converting the image to gray sacle and saving it in the dst matrix
      Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
      //Extracting data from the transformed image (dst)
      Mat re= dst.clone();
           
              
     //System.out.println("After Denoising :");
      //Photo.fastNlMeansDenoising(dst, dst);
      //Photo.fastNlMeansDenoising(dst, dst);
      // Denoising Procedure...  
      for(int i=0;i<dst.rows();i++){
          for(int j=0;j<dst.cols();j++){
      double a[]=dst.get(i, j);
      if(a[0]<110){
          //System.out.print(" ");
          a[0]=1;
          re.put(i, j, a);
      }
      else{
          //System.out.print("1");
          a[0]=0;
          re.put(i, j, a);
          }}
          //System.out.println();
      }     
           
      System.out.println("Original Size Of the Image:"+re.rows()+"x"+re.cols());
      /*
      for(int i=0;i<re.rows();i++){
          for(int j=0;j<re.cols();j++){
              double r[]=re.get(i, j);
              if(r[0]==1)
                System.out.print(" ");
              else
                System.out.print("1");  
          }
          System.out.println();
      }
      */
      int left=re.cols(),right=0,top=re.rows(),down=0,p=0,word=0,no=0;
      
      for(int j=0;j<re.cols();j++){ p=0;
          for(int i=0;i<re.rows();i++){
              double m[]=re.get(i, j);
              if(m[0]==1){
                  if(p==0){ word++;}
                  p++;  
                  left=Math.min(j, left);
                  right=Math.max(j, right);
                  top=Math.min(i, top);
                  down=Math.max(i, down);
              }
          } 
          if(p==0 && word>0 && left<right && top<down){ no++;
              System.out.println("WORD NO:"+no+" top:"+top+" down:"+down+" right:"+right+" left:"+left);
              Mat bc=new Mat();
              
              bc=dst.submat(top, down, left, right);
           Imgcodecs imageCodecs = new Imgcodecs(); 
           imageCodecs.imwrite("E:/photo/InputNEU/CREATE/CHAR"+no+".jpg",bc);
            /*    //DISPLAYING THE BINARY IMAGE
               for(int k=top;k<=down;k++){
                  for(int l=left;l<=right;l++){
                      double r[]=re.get(k, l);
                      if(r[0]==1)
                          System.out.print("1");
                      else
                          System.out.print(" ");
                  }
                  System.out.println();
              }     */
              left=re.cols();
              right=0;
              top=re.rows();
              down=0;
          }
      }
      System.out.println("No of characters scanned :"+no);
       //-----------------RESIZE IMAGE BEGINS----------------
       //Xander considers image of width=400 and height=8002
               
       for(int i=1;i<=no;i++){
       String in="E:/photo/InputNEU/CREATE/CHAR"+i+".jpg";
       String out="E:/photo/InputNEU/CREATE2/CHAR"+i+".jpg";
       resize(in,out,400,800);
       }
       System.out.println("All image gets resized");
       
       //Identification----
          String jk="";
       for(int k=1;k<=no;k++){
          String ip="E:/photo/InputNEU/CREATE2/CHAR"+k+".jpg";
          //System.out.println("jjjjjjjjjjjjjjjj");
       Mat src1=Imgcodecs.imread(ip);
       Mat dst1=new Mat();
       Imgproc.cvtColor(src1, dst1, Imgproc.COLOR_BGR2GRAY);
       Mat pe=dst1.clone();
           for(int i=0;i<dst1.rows();i++){
              for(int j=0;j<dst1.cols();j++){
                    double a[]=dst1.get(i, j);      
                            if(a[0]<110){          
                                     a[0]=1;
                                     pe.put(i, j, a);
                                        }
                             else{
                                  a[0]=0;
                                  pe.put(i, j, a);
          
      }
          }
          
           }
           
           //Calculate region ratios
           int x=dst1.rows()/2;
           int y=dst1.cols()/2;
           double aa[][]=new double[2][4];
           for(int i=0;i<2;i++){
               for(int j=0;j<4;j++){
                   aa[i][j]=0;
               }
           }  
           for(int i=0;i<pe.rows();i++){
               for(int j=0;j<pe.cols();j++){
                   double b[]=pe.get(i, j);
                   if(b[0]==1){
                      if(i<=x&&j<=y)      aa[0][0]++;
                      else if(i>x&&j<=y)  aa[0][1]++;
                      else if(i<=x&&j>y)  aa[0][2]++;
                      else if(i>x&&j>y)   aa[0][3]++;
                   }
                   else{
                      if(i<=x&&j<=y)      aa[1][0]++;
                      else if(i>x&&j<=y)  aa[1][1]++;
                      else if(i<=x&&j>y)  aa[1][2]++;
                      else if(i>x&&j>y)   aa[1][3]++;
                   }
               }}
           for(int j=0;j<4;j++){
                   if(aa[1][j]==0)  aa[1][j]=0.5;
                   if(aa[0][j]==0)  aa[0][j]=0.5;}
                   double r1=(double)aa[1][0]/aa[0][0];
                   double r2=(double)aa[1][1]/aa[0][1];
                   double r3=(double)aa[1][2]/aa[0][2];
                   double r4=(double)aa[1][3]/aa[0][3];
                   //p=Math.round(p);
                   //System.out.print(p+"    ");
               
               //System.out.println();
             
           
           double a1=0, black1=0;
           for(int i=0;i<pe.rows();i++){
               for(int j=0;j<pe.cols();j++){
                   double m[]=pe.get(i, j);
                   double n[]=pe.get(i, j);
                   if(m[0]==n[0])   a1++;
                   
                   if(m[0]==1 && n[0]==1)   black1++;
                   
               }
           }
           int max=0;
           double vb=0;
        for(int jj=1;jj<17;jj++){
           String ax="CHAR"+String.valueOf(jj);
           //System.out.print(ax+"::");2
           String output="E:/photo/test1/"+ax+".jpg";
             //System.out.println("kkkkkkkkkkkkkkkkkkk");
             Mat src2 = Imgcodecs.imread(output);
             Mat dst2=new Mat();
             Imgproc.cvtColor(src2,dst2,Imgproc.COLOR_RGB2GRAY);
             re=dst2.clone();
     
           for(int i=0;i<dst2.rows();i++){
              for(int j=0;j<dst2.cols();j++){
                    double a[]=dst2.get(i, j);      
                            if(a[0]<110){          
                                     a[0]=1;
                                     re.put(i, j, a);
                                        }
                             else{
                                  a[0]=0;
                                  re.put(i, j, a);
          
      }}}
           double a=0, black=0;
           for(int i=0;i<dst2.rows();i++){
               for(int j=0;j<dst2.cols();j++){
                   double m[]=re.get(i, j);
                   double n[]=pe.get(i, j);
                   if(m[0]==n[0])   a++;
                   
                   if(m[0]==1 && n[0]==1)   black++;
                   
               }
           }
           
           //System.out.println("For image "+jj+" :Similarity :"+((a/a1)*100)+"%  Black Difference :"+((black/black1)*100)+"%");
           double similar=(a/a1)*100;
           double blackdiff=(black/black1)*100;
           
           if(similar>vb){
               vb=similar;
               max=jj;
               v[max-1]++;
               
           }}
        double anm;
        if(max<5){
            anm=neural.Neura(r1, r2, r3, r4, 0.7);
            System.out.print("A");
            jk=jk+"A";
        }
        else if(max>4 && max<9){ 
            anm=neural.Neura(r1, r2, r3, r4, 0.5);
            System.out.print("B");  
            jk=jk+"B";
        }
        else if(max>8 && max<13){ 
            anm=neural.Neura(r1, r2, r3, r4, 0.3);
            System.out.print("C");
            jk=jk+"C";
        }
        else if(max>12 && max<17){ 
            anm=neural.Neura(r1, r2, r3, r4, 0.9);
            System.out.print("D");
            jk=jk+"D";
        }
                
       }
       System.out.println();
       System.out.println(jk);
       BufferedWriter out=new BufferedWriter(new FileWriter("E:/photo/myprinted.txt"));
       out.write(jk);
       out.newLine();
       out.flush();
       out.close();
       
       ProcessBuilder pg=new ProcessBuilder("Notepad.exe","E:/photo/myprinted.txt");
       pg.start();
       
   }
   else{
   tt=0;  //exist    
   }
   
   choice=0;
  }
   
   for(int i=0;i<16;i++) System.out.print(v[i]+" ");
  }
   }