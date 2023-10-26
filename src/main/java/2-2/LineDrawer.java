import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LineDrawer extends JPanel {

    private List<int[]> lines;
    private List<String> legends; // List to store legends
    private int linesPerLegend;

    public LineDrawer(List<int[]> lines, List<String> legends, int linesPerLegend) {
        this.lines = lines;
        this.legends = legends;
        this.linesPerLegend = linesPerLegend;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < lines.size(); i++) {
            int[] line = lines.get(i);
            int y1 = getHeight() - line[1];
            int y2 = getHeight() - line[3];
            g.drawLine(line[0], y1, line[2], y2);
            // Add legends after every X lines
            if ((i + 1) % linesPerLegend == 0) {
                int legendIndex = (i + 1) / linesPerLegend - 1;
                if (legendIndex < legends.size()) {
                    String legend = legends.get(legendIndex);
                    g.drawString(legend, line[2] + 10, y2);
                }
            }
        }
    }

    public static void drawLines(int[] x, long[][] y, List<String> legends, int linesPerLegend){
        List<int[]> lines = new ArrayList<>();

        int x1;
        int y1;
        int x2;
        int y2;

        int maxX = MathHelpFun.max(x);
        long maxY = MathHelpFun.max(y);

        int FigureXaxis = 800;
        int FigureYaxis = 600;


        for (int j = 0; j < y.length; j++) { // #lines
            for (int i = 0; i < x.length-1; i++) { // #dots
                x1 = x[i]*(FigureXaxis-150)/maxX;
                y1 = (int) ((int) y[j][i]*(FigureYaxis-80)/maxY);
                x2 = x[i + 1]*(FigureXaxis-150)/maxX;
                y2 = (int) ((int) y[j][i + 1]*(FigureYaxis-80)/maxY);
                lines.add(new int[]{x1, y1, x2, y2});
            }
        }
        JFrame frame = new JFrame("Line Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FigureXaxis, FigureYaxis);

        LineDrawer lineDrawer = new LineDrawer(lines, legends, linesPerLegend);
        frame.add(lineDrawer);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
//        int[] x = {100, 200, 300, 400};
//        long[][] y = {{100, 200, 100, 200}, {300, 350, 400, 450}, {70, 90, 70, 90}};
//
//        System.out.println(y.length);
//        List<String> legends = new ArrayList<>();
//        legends.add("Legend 1");
//        legends.add("Legend 2");
//        legends.add("Legend 3");
//        drawLines(x,y, legends, 3);

        int[] x = new int[]{5000, 10000, 50000, 100000, 200000};
        long[][] times = new long[3][5];
        times[0] = new long[]{0, 3, 46, 163, 774};
        times[1] = new long[]{213, 1182, 1039, 2583, 5705};
        times[2] = new long[]{266, 260, 1408, 2026, 5042};

//        int[] x = new int[]{50, 100, 500};
//        long[][] times = new long[1][3];
//        times[0] = new long[]{0, 3, 46};
////        times[1] = new long[]{213, 1182, 1039};
////        times[2] = new long[]{266, 260, 1408};

        String[] mystr = new String[]{"Insertion", "No repeatOrder", "No aux"};
        List<String> legends = new ArrayList<>();
        legends.add(mystr[0]);
        legends.add(mystr[1]);
        legends.add(mystr[2]);
        drawLines(x,times, legends, 4);
    }

}
