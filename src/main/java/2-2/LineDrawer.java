import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LineDrawer extends JPanel {

    private List<int[]> lines;

    public LineDrawer(List<int[]> lines) {
        this.lines = lines;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int[] line : lines) { // # dots
            g.drawLine(line[0], line[1], line[2], line[3]);
        }


    }

    public static void drawLines(int[] x, long[][] y){
        List<int[]> lines = new ArrayList<>();

        int x1;
        int y1;
        int x2;
        int y2;

        for (int i = 0; i < x.length - 1; i++) { // #dots
            for (int j = 0; j < y.length; j++) { // #lines
                x1 = x[i];
                y1 = (int) y[j][i];
                x2 = x[i + 1];
                y2 = (int) y[j][i + 1];
                lines.add(new int[]{x1, y1, x2, y2});
            }
        }
        JFrame frame = new JFrame("Line Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        LineDrawer lineDrawer = new LineDrawer(lines);
        frame.add(lineDrawer);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        int[] x = {100, 200, 300, 400};
        long[][] y = {{50, 75, 150, 175}, {100, 200, 125, 225}};

        drawLines(x,y);
    }
}
