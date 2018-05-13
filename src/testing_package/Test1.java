package testing_package;

import grid.Graph;
import grid.GridConfCreator;
import grid.square.MatrixGraph;
import rules.*;

import java.awt.*;

import javax.swing.JFrame;

public class Test1 {
    public static void main(String [] args) {
    	MatrixGraph graph = new MatrixGraph(3, 3, 1);
    	for(int i=1; i<=graph.getNumNodes(); i++) {
    		for(int j=0; j<=graph.getCell(i).getNumNeighbors(); j++)
    			System.out.print(graph.getCell(i).getKthNeighbor(j)+" ");
    		System.out.println();
    	}
        /*BaseExpressionNode1 A = new BaseExpressionNode1(2, 3, Color.BLUE);
        BaseExpressionNode2 B = new BaseExpressionNode2(1,  Color.RED);
        BaseExpressionNode2 C = new BaseExpressionNode2(0,  Color.BLUE);
        BaseExpressionNode1 D = new BaseExpressionNode1(1, 1, Color.RED);
        NotNode not1 = new NotNode(A);
        AndNode and1 = new AndNode(not1, B);
        OrNode or1 = new OrNode(C, D);
        AndNode root = new AndNode(and1, or1);

        Graph graph = new Graph();
        ListCell c1 = new ListCell(1, Color.BLUE, 2, 3, 4);
        ListCell c2 = new ListCell(2, Color.RED,1, 3);
        ListCell c3 = new ListCell(3, Color.BLUE, 1, 2);
        ListCell c4 = new ListCell(4, Color.RED, 1);
        graph.addCell(c1);
        graph.addCell(c2);
        graph.addCell(c3);
        graph.addCell(c4);

        System.out.println(root.evaluate(graph, 1));
        System.out.println(root.evaluate(graph, 2));
        System.out.println(root.evaluate(graph, 3));
        System.out.println(root.evaluate(graph, 4));

        BaseExpressionNode1 Ap = new BaseExpressionNode1(1, 1, Color.BLUE);
        BaseExpressionNode1 Bp = new BaseExpressionNode1(1, 1, Color.RED);
        BaseExpressionNode2 Cp = new BaseExpressionNode2(0, Color.RED);
        OrNode orp = new OrNode(Bp, Cp);
        AndNode andp = new AndNode(Ap, orp);

        System.out.println();
        System.out.println(andp.evaluate(graph, 1));
        System.out.println(andp.evaluate(graph, 2));
        System.out.println(andp.evaluate(graph, 3));
        System.out.println(andp.evaluate(graph, 4));*/
    	
    	/*JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	GridConfCreator gcc = new GridConfCreator();
    	frame.add(gcc);
    	frame.setBounds(10, 10, 300, 300);
    	frame.setVisible(true);
    	System.out.println(gcc.getGridConfiguration());*/
    }
}
