


import java.io.*;
import java.util.ArrayList;



public class prs_ex {

    private static final String source = "D:\\Java\\52\\";

    public static void main(String[] args) throws IOException {

        BufferedReader file = new BufferedReader(new FileReader(source + "ex.txt"));
        ArrayList<BufferedWriter> list = new ArrayList<BufferedWriter>();

        String[] writers = file.readLine().split(";");
        for (String wr : writers) {
            list.add(new BufferedWriter(new FileWriter(source + wr + ".txt")));
        }
        while (file.ready()) {

            String[] str2 = file.readLine().split(";");
            for (int i = 0; i < list.size(); i++) {
                try {
                    str2[i] = str2[i].replace(",", ".");
                    list.get(i).write(str2[i] + "\r\n");

                } catch (ArrayIndexOutOfBoundsException e) {
                    list.get(i).write("0\r\n");
                }
            }
        }
        /********Close all streams*********/
        file.close();
        for (BufferedWriter aList : list) {
            aList.close();
        }
        /**********************************/

        dus(writers[2]); // ДУС_Y
        dus(writers[4]);

        parseGray(writers[14], writers[15], writers[16], writers[17], writers[0]); //GRK


    }

    public static String calc(String first, String second) {
        double st = 0;
        double ml = 0;
        double val = 0;
        try {
            if (!first.equals("")) {

                st = Double.parseDouble(first.split(",")[0]);
            }
            if (!second.equals("")) {
                ml = Double.parseDouble(second.split(",")[0]);
            }

            val = ((st * 256d + ml) - 32768d) / 80;

        } catch (Exception e) {
            System.err.println("Can,t parse");
        }
        return String.valueOf(val);
    }

    public static void parseGray(String file1, String file2, String file3, String file4, String time) throws IOException {

        BufferedReader reader_1 = new BufferedReader(new FileReader(source + file1 + ".txt"));
        BufferedReader reader_2 = new BufferedReader(new FileReader(source + file2 + ".txt"));
        BufferedReader reader_3 = new BufferedReader(new FileReader(source + file3 + ".txt"));
        BufferedReader reader_4 = new BufferedReader(new FileReader(source + file4 + ".txt"));
        BufferedReader reader_time = new BufferedReader(new FileReader(source + time + ".txt"));

        BufferedWriter writer_grk = new BufferedWriter(new FileWriter(source + "norm_GRK.txt"));
        BufferedWriter writer_frk = new BufferedWriter(new FileWriter(source + "GRK_frk.txt"));


        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> list3 = new ArrayList<String>();
        ArrayList<String> list4 = new ArrayList<String>();
        ArrayList<String> list_time = new ArrayList<String>();

        while (reader_1.ready()) {
            list1.add(reader_1.readLine());
        }
        reader_1.close();

        while (reader_2.ready()) {
            list2.add(reader_2.readLine());
        }
        reader_2.close();

        while (reader_3.ready()) {
            list3.add(reader_3.readLine());
        }
        reader_3.close();

        while (reader_4.ready()) {
            list4.add(reader_4.readLine());
        }
        reader_4.close();

        while (reader_time.ready()) {
            list_time.add(reader_time.readLine());
        }
        reader_time.close();

        for (int i = 0; i < list1.size(); i++) {
            int gray = 0;
            try {
                int grk1 = (int) Double.parseDouble(list1.get(i));
                int grk2 = (int) Double.parseDouble(list2.get(i));
                int grk3 = (int) Double.parseDouble(list3.get(i));
                int grk4 = (int) Double.parseDouble(list4.get(i));
                gray = (grk1 << 3) | (grk2 << 2) | (grk3 << 1) | grk4;
                String fi = gray2bin(gray);
                writer_grk.write(fi + "\r\n");
            } catch (NumberFormatException e) {
                writer_grk.write("0" + "\r\n");
               // e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                writer_grk.write("0" + "\r\n");
              //  e.printStackTrace();
            }

            try {

            }catch (NumberFormatException e){
                writer_frk.write("0" + "\r\n");
                e.printStackTrace();
            }catch (ArrayIndexOutOfBoundsException e){
                writer_grk.write("0" + "\r\n");
                e.printStackTrace();
            }
        }
        writer_grk.close();
        writer_grk.close();
    }


    public static String gray2bin(int x) {
        switch (x) {
            case 15:
                return "326.25";

            case 7:
                return "348.75";

            case 3:
                return "11.25";

            case 11:
                return "33.75";

            case 9:
                return "56.25";

            case 1:
                return "78.75";

            case 5:
                return "101.5";

            case 13:
                return "123.75";

            case 12:
                return "146.25";

            case 4:
                return "168.75";

            case 0:
                return "191.25";

            case 8:
                return "213.75";

            case 10:
                return "236.25";

            case 2:
                return "258.75";

            case 6:
                return "281.25";

            case 14:
                return "303.75";

            default:
                return "0";

        }
    }

    public static void dus(String fileName) throws IOException {
        BufferedReader reader_dus = new BufferedReader(new FileReader(source + fileName + ".txt"));
        BufferedWriter writer_dus = new BufferedWriter(new FileWriter(source + "New - " + fileName + ".txt"));
        while (reader_dus.ready()) {
            String str1 = reader_dus.readLine();
            String str2 = reader_dus.readLine();
            writer_dus.write(calc(str1, str2) + "\r\n");
            writer_dus.write(calc(str1, str2) + "\r\n");
        }
        reader_dus.close();
        writer_dus.close();
    }


}
