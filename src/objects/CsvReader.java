package objects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    private String trainPath;
    private String testPath;

    private double[][] xTrain;
    private double[] yTrain;

    private double[][] xTest;


    public CsvReader()   {
        this.trainPath = "C:\\Users\\robin\\Google Drive\\Year 4\\Period 4\\Data analysis\\Kaggle Competition\\dkeda\\train.csv";
        this.testPath = "C:\\Users\\robin\\Google Drive\\Year 4\\Period 4\\Data analysis\\Kaggle Competition\\dkeda\\test.csv";
        read();
    }

    public double[][] getXTrain(){
        return this.xTrain;
    }

    public double[] getYTrain(){
        return this.yTrain;
    }

    public double[][] getxTest(){
        return this.xTest;
    }


    public void read()  {
        String trainFile = this.trainPath;
        String testFile = this.testPath;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(trainFile));
            int count = 0;
            String[] entry;
            int entryLength = 0;
            while ((line = br.readLine()) != null) {

                if(count <= 4)  {
                    entry = line.split(cvsSplitBy);
                    entryLength = entry.length;

                    for (int k = 0; k < entry.length; k++) {
                        System.out.print(entry[k] + " ");
                    }
                    System.out.println();
                }



                count++;
            }
            //System.out.print("Count: " + count);

            count--;
            this.xTrain = new double[count][entryLength-1];
            this.yTrain = new double[count];

            int i = 0;
            BufferedReader br2 = new BufferedReader(new FileReader(trainFile));
            System.out.println();
            while ((line = br2.readLine()) != null) {

                // Season, Month, hour, holiday, weekday, workingday, weekday, weather, temp, atemp, hum, windspeed, cnt

                entry = line.split(cvsSplitBy);
                if(i < 1) {

                    for (int k = 0; k < entry.length; k++) {
                        System.out.print(entry[k] + " ");
                    }
                    System.out.println();
                }

                if(i >= 1) {
                    //System.out.println("Length: " + entry.length);
                    for (int j = 0; j < entry.length - 1; j++) {
                        //System.out.println("i: " + i + "\tj: " + j);
                        this.xTrain[i-1][j] = Double.parseDouble(entry[j]);
                    }
                    this.yTrain[i-1] = Double.parseDouble(entry[entry.length - 1]);
                }

                i++;
            }

            br = new BufferedReader(new FileReader(testFile));
            count = 0;
            while ((line = br.readLine()) != null) {

                if(count == 1)  {
                    entry = line.split(cvsSplitBy);
                    entryLength = entry.length;
                }
                count++;
            }

            count--;

            this.xTest = new double[count][entryLength];
            BufferedReader br3 = new BufferedReader(new FileReader(testFile));
            i = 0;
            while ((line = br3.readLine()) != null) {

                // Season, Month, hour, joliday, weekday, workingday, weekday, weather, temp, atemp, hum, windspeed, cnt
                entry = line.split(cvsSplitBy);
                if(i >= 1) {
                    for (int j = 0; j < entry.length; j++) {
                        this.xTest[i-1][j] = Double.parseDouble(entry[j]);
                    }
                }

                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




    public static void main(String[] args) {
        CsvReader read = new CsvReader();
        double[][] xTrain = read.getXTrain();
        double[] yTrain = read.getYTrain();
        double[][] xTest = read.getxTest();

        for(int i = 0; i < 5; i++)  {
            for(int j = 0; j < xTest[i].length; j++) {
                System.out.print(xTest[i][j] + " ");
            }
            //System.out.print("\tResult: " + yTrain[i]);
            System.out.println();
        }

        System.out.println();
        System.out.println();


        for(int i = xTest.length - 6; i < xTest.length; i++)  {
            for(int j = 0; j < xTest[i].length; j++) {
                System.out.print(xTest[i][j] + " ");
            }
            //System.out.print("\tResult: " + yTrain[i]);
            System.out.println();
        }



    }
}
