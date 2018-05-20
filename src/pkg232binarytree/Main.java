/*
 * Author: Logan Caraway
 * Date Created: 5/16/2018
 * Purpose: It's a binary tree.
 * Notes: Use the number keys + enter to navigate the menu
 */
package pkg232binarytree;

import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    //--------------------------------------------------//
    private static class IncorrectFileFormatException extends Exception {
        public IncorrectFileFormatException(String s) {
            super(s);
        }
    }
    //--------------------------------------------------//
    public static void main(String[] args) throws IncorrectFileFormatException {
        BinaryTree<Integer> tree = new BinaryTree<>();
        String[] split_line;
        Scanner inp = null;
        
        //attempt to find and set the scanner to the input file
        try {
            inp = new Scanner(new File("input.txt"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, new String("Could not find input.txt"));
            System.exit(1);
        }
        
        //while the input file has more data
        while (inp.hasNext()) {
            //take next line and split it by commas
            split_line = inp.nextLine().split(",");
            for (String key : split_line) {
                try {
                    tree.add(Integer.parseInt(key));
                    tree.printPreorderToConsole();
                } catch (NumberFormatException e) {throw new IncorrectFileFormatException("Incorrect file format.\nChange the file and try again.");}
            }
        }
        System.out.println();
        System.out.println();
        menu(tree);
    }
    
    public static void menu(BinaryTree tree) {
        //Scanner keyboard = new Scanner(System.in);
        String choice;
        //menu
        while (true) {
            choice = JOptionPane.showInputDialog("Main Menu: <1> add key, <2> delete key, <3> to print, <4> search, <0> to quit");
            switch(choice.charAt(0)) {
                case ('1'): try {
                                tree.add(Integer.parseInt(JOptionPane.showInputDialog("Enter key to be added to tree:")));
                                tree.printPreorderToConsole();
                            } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, new String("Invalid option"));}
                            break;
                case ('2'): try {
                                tree.delete(Integer.parseInt(JOptionPane.showInputDialog("Enter key to be deleted from the tree:")));
                                tree.printPreorderToConsole();
                            } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, new String("Invalid option"));}
                            break;
                case ('3'): choice = JOptionPane.showInputDialog("Print Options: <1> Inorder, <2> Preorder, <3> Postorder, <0> Main Menu");
                            switch (choice.charAt(0)) {
                                case ('1'): tree.printInorderToConsole();
                                            break;
                                case ('2'): tree.printPreorderToConsole();
                                            break;
                                case ('3'): tree.printPostorderToConsole();
                                            break;
                                case ('0'): break;
                                default: JOptionPane.showMessageDialog(null, new String(choice.charAt(0)+" not recognized, returning to main menu"));
                            }
                            break;
                case ('4'): try {
                                boolean found = tree.search(Integer.parseInt(JOptionPane.showInputDialog("Enter key for search: ")));
                                if (found) JOptionPane.showMessageDialog(null, new String("The tree contains the value"));
                                else JOptionPane.showMessageDialog(null, new String("The tree does not contain the value"));
                            } catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, new String("Invalid option"));}
                            break;
                case ('0'): System.exit(0);
                            break;
                default: JOptionPane.showMessageDialog(null, new String(choice.charAt(0)+" not recognized, all options are integers"));
            }
        }
    }
}
