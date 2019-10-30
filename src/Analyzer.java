import java.io.*;
import java.util.*;

public class Analyzer {

    /**
     * this Iterm class is to store the information needed.
     */
    public static class Item {
        private String date;
        private int value;
        private String measure;
        private String border;
        private int average;

        public Item(String date, int value, String measure, String border) {
            this.date = date;
            this.value = value;
            this.measure = measure;
            this.border = border;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        public String getBorder() {
            return border;
        }

        public void setBorder(String border) {
            this.border = border;
        }

        public int getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }
    }


    public static void main(String[] args) throws IOException {
        try {

            //map to combine and store raw data in the first loop
            Map<String, Integer> map = new HashMap<>();
            //list of item
            List<Item> list = new ArrayList<>();
            //args[0] is the input file name
            System.out.println(args.length);
            BufferedReader reader = new BufferedReader(new FileReader(args.length == 0 ? "input/Border_Crossing_Entry_Data.csv" : args[0]));
            //the first line is the title line, skip
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String items[] = line.split(",");
                String key = items[4] + "_" + items[5] + "_" + items[3];
                map.put(key, map.getOrDefault(key, 0) + Integer.valueOf(items[6]));
            }

            //add items into list
            for (Map.Entry<String, Integer> pair: map.entrySet()) {
                String[] dateMeasureBorder = pair.getKey().split("_");
                int value = pair.getValue();
                //date, value,  measure, border,
                list.add(new Item(dateMeasureBorder[0], value, dateMeasureBorder[1], dateMeasureBorder[2]));
            }

            //sort the list by border, measure, date, value
            Collections.sort(list, (a,b) ->
                    a.getBorder().equals(b.getBorder()) ?
                            a.getMeasure().equals(b.getMeasure()) ?
                                    a.getDate().equals(b.getDate()) ?
                                            b.getValue() - a.getValue() :  b.getDate().compareTo(a.getDate()) : b.getMeasure().compareTo(a.getMeasure()) : b.getBorder().compareTo(a.getBorder()));

            // set average
            int sum = 0;
            int cnt = 0;
            list.get(list.size() - 1).setAverage(0);
            for (int i = list.size() - 2; i >= 0; --i) {
                Item cur = list.get(i);
                Item pre = list.get(i + 1);
                if (cur.getBorder().equals(pre.getBorder())
                        && cur.getMeasure().equals(pre.getMeasure())) {
                    cur.setAverage(sum / cnt);
                    cnt++;
                    sum += cur.getValue();
                } else {
                    cur.setAverage(0);
                    cnt = 1;
                    sum += cur.getValue();
                }
            }

            //sort against requirement
            Collections.sort(list, (a,b) ->
                    a.getDate().equals(b.getDate()) ?
                            a.getValue() == b.getValue() ?
                                    a.getMeasure().equals(b.getMeasure()) ?
                                            b.getBorder().compareTo(a.getBorder()) :  b.getMeasure().compareTo(a.getMeasure()) : b.getValue() - a.getValue() : b.getDate().compareTo(a.getDate()));

            //write data into output file
            try {
                //new a report.csv
                //new File("../../test.txt");

                File report = new File(args.length == 0 || args.length == 1 ? "output/report.csv" : args[1]);
                // new BufferedWriter
                BufferedWriter bw = new BufferedWriter(new FileWriter(report,true));
                //write title line
                bw.write("Border,Date,Measure,Value,Average");
                bw.newLine();

                // write data into report.csv
                for (Item item: list) {
                    bw.write(item.getBorder() + "," + item.getDate() + "," + item.getMeasure() + "," + item.getValue() + "," + item.getAverage());
                    bw.newLine();
                }

                bw.close();

            } catch (FileNotFoundException e) {
                // File object exception catch
                e.printStackTrace();
            } catch (IOException e) {
                // BufferedWriter close exception catch
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

