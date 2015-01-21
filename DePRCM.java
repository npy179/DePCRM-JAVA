import java.io.*;
import java.lang.*;


public class DePRCM{

    public static void main(String[] args){

        File folder = new File("/home/npy/CelegansData");
        File[] fileList = folder.listFiles();

        for (File file : fileList){
            if (file.isFile()){
                String filename = file.getName();
                File fl = new File(filename);

                String ofilename = filename.replace("GFF3","BED");
                File ofl = new File(ofilename);

                PeakExtension(fl,ofl);
            }
            else {

            };
        }

    }

    public static void PeakExtension(File file,File ofile){
       final int ExtPeakLen = 3000;


        try{
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            FileOutputStream fos = new FileOutputStream(ofile);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            String line = null;
            String title1 = br.readLine();String titlenew = "Bed format";bw.write(titlenew);bw.newLine();
            String title2 = br.readLine();bw.write(title2);bw.newLine();
            while((line = br.readLine())!=null){


                String delim = "\t";
                String[] tokens = line.split(delim);
                double StartPoint = Double.parseDouble(tokens[3]);
                double EndPoint = Double.parseDouble(tokens[4]);

                if((EndPoint-StartPoint)<5000){
                    double MidPoint = Math.floor((StartPoint+EndPoint)/2);

                    double EstartPoint = MidPoint - 1499;
                    double EendPoint = MidPoint + 1501;

                    tokens[3] = Double.toString(EstartPoint);
                    tokens[4] = Double.toString(EendPoint);


                    String Exline = tokens[0]+"\t"+tokens[1]+"\t"+tokens[2]+"\t"+tokens[3]+"\t"+tokens[4]+"\t"+tokens[5]+"\n";
                    System.out.println(Exline);
                    bw.write(Exline);


                }
                else{

                }

            }
            br.close();
            bw.close();


        }catch(IOException e){
             e.printStackTrace();
        }
    }
}
